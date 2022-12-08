import numpy as np
import pandas as pd
from sklearn.neighbors import NearestNeighbors
from scipy.sparse import csr_matrix
import random
import click

class RecommendModel(object):

    def __init__(self, workingdir):
        self.workingdir = workingdir
        self.profiles = pd.read_excel(workingdir+"/profiles.xls")
        self.likes = pd.read_excel(workingdir+"/likes.xls")
        self.reviews = pd.read_excel(workingdir+"/reviews.xls")

    def popular_users(self, username, num=40):
        if username not in self.profiles['username'].unique():
            return

        user_profile = self.profiles[self.profiles["username"] == username]
        popular_ls = self.likes["userviewed"].value_counts().index.tolist()
        seen = self.likes.loc[self.likes["username"] == username, "userviewed"].values
        selected = []
        while (len(selected) < num) and (len(popular_ls) > 0):
            if popular_ls[0] not in seen:
                selected.append(popular_ls.pop(0))
            else:
                popular_ls.pop(0)
        pop_data = self.profiles[self.profiles["username"].isin(selected)]

        if (user_profile["Sexuality"].tolist()[0] == "straight" and user_profile["Gender"].tolist()[0] == "f") or \
                (user_profile["Sexuality"].tolist()[0] == "gay" and user_profile["Gender"].tolist()[0] == "m"):
            pop_data = pop_data[pop_data["Gender"] == "m"]
        elif (user_profile["Sexuality"].tolist()[0] == "straight" and user_profile["Gender"].tolist()[0] == "m") or \
                (user_profile["Sexuality"].tolist()[0] == "gay" and user_profile["Gender"].tolist()[0] == "f"):
            pop_data = pop_data[pop_data["Gender"] == "f"]

        pop_data.to_excel(self.workingdir+"/popular.xls",
                          index=False)

    ## Data cleaning

    def create_matrix(self):
        N = len(self.likes['username'].astype(str).unique())
        M = len(self.likes['userviewed'].astype(str).unique())

        # Map Ids to indices
        user_mapper = dict(zip(np.unique(self.likes['username'].astype(str)), list(range(N))))
        userviewed_mapper = dict(zip(np.unique(self.likes['userviewed'].astype(str)), list(range(M))))

        # Map indices to IDs
        user_inv_mapper = dict(zip(list(range(N)), np.unique(self.likes['username'].astype(str))))
        userviewed_inv_mapper = dict(zip(list(range(M)), np.unique(self.likes['userviewed'].astype(str))))

        user_index = [user_mapper[i] for i in self.likes['username'].astype(str)]
        userviewed_index = [userviewed_mapper[i] for i in self.likes['userviewed'].astype(str)]

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

    def similar_user(self, userid, userviewed_id, k=20):
        '''
        Recommend similar users, if k > maximum possible recommend number, recommend every users left
        :param userid: recommend for user with username = userid
        :param userviewedid: recommend according to userviewedid
        :param k: maximum number of users to recommend
        '''

        if userid not in self.profiles['username'].unique():
            return

        kmax = len(self.profiles['username'].unique()) - len(self.likes.loc[self.likes["username"] == userid, "userviewed"].unique())
        user_profile = self.profiles[self.profiles["username"] == userid]
        if kmax == 0:
            recm_data = pd.DataFrame(columns=self.profiles.columns)
            recm_data.to_excel(self.workingdir + "/similar.xls",
                               index=False)
            return
        else:
            similar_ids = self.find_similar_users(userviewed_id=userviewed_id, k=k)
            selected = [i for i in similar_ids if i not in self.likes.loc[self.likes["username"] == userid, "userviewed"].values]
            recm_data = self.profiles[self.profiles["username"].isin(selected)]

        if (user_profile["Sexuality"].tolist()[0] == "straight" and user_profile["Gender"].tolist()[0] == "f") or \
                (user_profile["Sexuality"].tolist()[0] == "gay" and user_profile["Gender"].tolist()[0] == "m"):
            recm_data = recm_data[recm_data["Gender"] == "m"]
        elif (user_profile["Sexuality"].tolist()[0] == "straight" and user_profile["Gender"].tolist()[0] == "m") or \
                (user_profile["Sexuality"].tolist()[0] == "gay" and user_profile["Gender"].tolist()[0] == "f"):
            recm_data = recm_data[recm_data["Gender"] == "f"]

        recm_data.to_excel(self.workingdir + "/similar.xls",
                          index=False)

    def recommend(self, userid, k=10, num=40):
        '''
        Recommend according to the highly rated users and liked users (optimally k << num)
        :param userid: recommend for user with username = userid
        :param k: number of users to recommend according to each record
        :param num: total number of users to recommend
        '''
        if userid not in self.profiles['username'].unique():
            return

        user_info = self.likes[self.likes["username"] == userid]
        user_profile = self.profiles[self.profiles["username"] == userid]
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


        if (user_profile["Sexuality"].tolist()[0] == "straight" and user_profile["Gender"].tolist()[0] == "f") or \
                (user_profile["Sexuality"].tolist()[0] == "gay" and user_profile["Gender"].tolist()[0] == "m"):
            to_recommend = to_recommend[to_recommend["Gender"] == "m"]
        elif (user_profile["Sexuality"].tolist()[0] == "straight" and user_profile["Gender"].tolist()[0] == "m") or \
                (user_profile["Sexuality"].tolist()[0] == "gay" and user_profile["Gender"].tolist()[0] == "f"):
            to_recommend = to_recommend[to_recommend["Gender"] == "f"]

        to_recommend.to_excel(self.workingdir + "/recommend.xls", index=False)
        based_on.to_excel(self.workingdir + "/recommend_base.xls", index=False)


@click.group()
@click.pass_context
@click.argument('workingdir', type=click.Path(exists=True))
def cli(ctx, workingdir):
    ctx.obj = RecommendModel(workingdir)

@cli.command()
@click.pass_context
@click.option('--username', required=True)
@click.option('--maxusers', default=40)
def popular(ctx, username, maxusers):
    ctx.obj.popular_users(username=username, num=maxusers)
    click.echo("updated popular.xls")

@cli.command()
@click.pass_context
@click.option('--username', required=True)
@click.option('--userviewed', required=True)
@click.option('--maxusers', default=20)
def similar(ctx, username, userviewed, maxusers):
    ctx.obj.similar_user(userid=username, userviewed_id=userviewed, k=maxusers)
    click.echo("updated similar.xls")

@cli.command()
@click.pass_context
@click.option('--username', required=True)
@click.option('--k', default=10)
@click.option('--maxusers', default=40)
def recommend(ctx, username, k, maxusers):
    ctx.obj.recommend(userid = username, k=k, num=maxusers)
    click.echo("updated recommend.xls and recommend_base.xls")


if __name__ == "__main__":
    cli()





























