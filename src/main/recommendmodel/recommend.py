import numpy as np
import pandas as pd
from sklearn.neighbors import NearestNeighbors
from scipy.sparse import csr_matrix
import random
import click

class RecommendModel:

    def __init__(self, workingdir):
        self.workingdir = workingdir
        self.profiles = pd.read_excel(workingdir+"/profiles.xls")
        self.likes = pd.read_excel(workingdir+"/likes.xls")
        self.reviews = pd.read_excel(workingdir+"/reviews.xls")

    def popular_users(self):
        selected = self.likes["userviewed"].value_counts()[:10].index.tolist()
        pop_data = self.profiles[self.profiles["username"].isin(selected)]
        pop_data.to_excel(self.workingdir+"/popular.xls",
                          index=False)

    ## Data cleaning

    def create_matrix(self):
        N = len(self.likes['username'].unique())
        M = len(self.likes['userviewed'].unique())

        # Map Ids to indices
        user_mapper = dict(zip(np.unique(self.likes['username']), list(range(N))))
        userviewed_mapper = dict(zip(np.unique(self.likes['userviewed']), list(range(M))))

        # Map indices to IDs
        user_inv_mapper = dict(zip(list(range(N)), np.unique(self.likes['username'])))
        userviewed_inv_mapper = dict(zip(list(range(M)), np.unique(self.likes['userviewed'])))

        user_index = [user_mapper[i] for i in self.likes['username']]
        userviewed_index = [userviewed_mapper[i] for i in self.likes['userviewed']]

        X = csr_matrix((self.likes["like"], (userviewed_index, user_index)), shape=(M, N))

        return X, user_mapper, userviewed_mapper, user_inv_mapper, userviewed_inv_mapper

    ## Modeling

    def find_similar_users(self, userviewed_id, k, metric='cosine', show_distance=False):
        neighbour_ids = []
        X, user_mapper, userviewed_mapper, user_inv_mapper, userviewed_inv_mapper = self.create_matrix()
        userviewed_ind = userviewed_mapper[userviewed_id]
        userviewed_vec = X[userviewed_ind]
        k += 1
        kNN = NearestNeighbors(n_neighbors=k, algorithm="brute", metric=metric)
        kNN.fit(X)
        userviewed_vec = userviewed_vec.reshape(1, -1)
        neighbour = kNN.kneighbors(userviewed_vec, return_distance=show_distance)
        for i in range(0,k):
            n = neighbour.item(i)
            neighbour_ids.append(userviewed_inv_mapper[n])
        neighbour_ids.pop(0)
        return neighbour_ids

    ## Prediction

    def similar_user(self, userid, userviewed_id, k=10):
        '''
        Recommend similar users, if k > maximum possible recommend number, recommend every users left
        :param userid: recommend for user with username = userid
        :param userviewedid: recommend according to userviewedid
        :param k: maximum number of users to recommend
        '''
        kmax = len(self.profiles['username'].unique()) - len(self.likes.loc[self.likes["username"] == userid, "userviewed"].unique())
        if kmax == 0:
            recm_data = pd.DataFrame(columns=self.profiles.columns)
        else:
            similar_ids = self.find_similar_users(self.likes, userviewed_id=userviewed_id, k=k)
            selected = [i for i in similar_ids if i not in self.likes.loc[self.likes["username"] == userid, "userviewed"].values]
            recm_data = self.profiles[self.profiles["username"].isin(selected)]
        recm_data.to_excel(self.workingdir + "/similar.xls",
                          index=False)

    def recommend(self, userid, k=5, num=20):
        '''
        Recommend according to the highly rated users and liked users (optimally k << num)
        :param userid: recommend for user with username = userid
        :param k: number of users to recommend according to each record
        :param num: total number of users to recommend
        '''
        user_info = self.likes[self.likes["username"] == userid]
        if len(user_info) == 0:
            raise ValueError("Non-existed user")
        kmax = len(self.profiles['username'].unique()) - len(user_info["username"].unique())

        likes_reviews = pd.merge(self.likes, self.reviews, how="left", on="reviewId")
        reviewed_info = likes_reviews[(likes_reviews["username"] == userid) & (likes_reviews["reviewId"].notnull())]
        if len(reviewed_info) != 0:
            rate_max = reviewed_info["rating"].max()
            # Recommend based on users with ratings greater than maximum rating-0.5
            like_info = likes_reviews[(likes_reviews["username"] == userid) & (likes_reviews["rating"] >= rate_max - 0.5)]
            if (len(like_info) - 5) * k < num:
                other_liked = likes_reviews[(likes_reviews["username"] == userid) & (likes_reviews["like"] == 1)]
                try:
                    like_info = pd.concat([like_info, other_liked.sample(n=(int(np.ceil(num/k) - len(like_info)) + 5))])
                except ValueError:
                    like_info = pd.concat([like_info, other_liked])
        else:
            like_info = likes_reviews[(likes_reviews["username"] == userid) & (likes_reviews["like"] == 1)]


        if len(like_info) == 0:
            selected = self.likes["userviewed"].value_counts()[:num].index.tolist()
            to_recommend = self.profiles[self.profiles["username"].isin(selected)]
            users_base = []
        elif kmax == 0:
            to_recommend = pd.DataFrame(columns=self.profiles.columns)
            users_base = []
        else:
            if k > kmax or num > kmax:
                pption = num / kmax
                num = kmax
                k = int(np.ceil(num/pption))
            users_base = []
            users_recommend = []
            inf_prevender = 0
            base_candidate = like_info['userviewed'].values.tolist()
            while len(users_recommend) < num and inf_prevender <= 20 and len(base_candidate) > 0:
                based_id = random.choice(base_candidate)
                base_candidate.remove(based_id)
                similar_ids = self.find_similar_users(userviewed_id=based_id, k=k)
                selected = [i for i in similar_ids if
                            (i not in user_info["userviewed"].values) and (i not in users_recommend)]
                if len(selected) == 0:
                    inf_prevender += 1
                    continue
                for i in range(len(selected)):
                    if len(users_recommend) < num:
                        users_recommend.append(selected[i])
                    else:
                        break
                users_base.append(based_id)
            to_recommend = self.profiles[self.profiles["username"].isin(users_recommend)]
        based_on = self.profiles[self.profiles["username"].isin(users_base)]

        to_recommend.to_excel(self.workingdir + "/recommend.xls", index=False)
        based_on.to_excel(self.workingdir + "/recommend_base.xls", index=False)


@click.group()
def cli():
    pass

@click.command()
@click.argument('workingdir', type=click.Path(exists=True))
def popular(workingdir):
    db = recommend(workingdir)
    db.popular_users()

@click.command()
@click.argument('workingdir', type=click.Path(exists=True))
@click.argument('username')
@click.argument('userviewed')
@click.argument('maxusers')
def similar(workingdir, username, userviewed, maxusers=10):
    db = recommend(workingdir)
    db.similar_user(userid=username, userviewed_id=userviewed, k=maxusers)

@click.command()
@click.argument('workingdir', type=click.Path(exists=True))
@click.argument('username')
@click.argument('numNN')
@click.argument('maxusers')
def recommend(workingdir, username, numNN=5, maxusers=20):
    db = recommend(workingdir)
    db.recommend(userid = username, k=numNN, num=maxusers)

cli.add_command(popular)
cli.add_command(similar)
cli.add_command(recommend)

if __name__ == "__main__":
    cli()





























