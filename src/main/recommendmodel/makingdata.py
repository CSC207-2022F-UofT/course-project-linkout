import numpy as np
import pandas as pd
import random
import click
import xlwt


@click.command()
def forgedata():
    profiles_orig = pd.read_csv("/Users/tristalli/Desktop/CSC207/project material/okcupid_profiles.csv")
    likes_orig = pd.read_csv("/Users/tristalli/Desktop/CSC207/project material/ratings.csv")
    random.seed(1000)

    profiles = profiles_orig[["age","sex", "location", "orientation", "height", "body_type", "essay0"]]
    profiles["height"] = round(profiles["height"] * 2.54)
    profiles = profiles.rename(columns = {"age":"Age", "sex":"Gender", "location":"Location", "orientation": "Sexuality",
                                          "height":"Height", "essay0": "SelfDescription"})
    profiles["Weight"] = None
    profiles.loc[(profiles["body_type"].isin(["a little extra", "athletic", "full figured"])), "Weight"] = round(profiles["Height"]/2.5)
    profiles.loc[(profiles["body_type"].isin(["average", "curvy", "jacked", "fit"])), "Weight"] = round(profiles["Height"]/3)
    profiles.loc[(profiles["body_type"].isin(["skinny", "thin", "used up"])), "Weight"] = round(profiles["Height"]/3.5)
    profiles.loc[(profiles["body_type"].isin(["overweight"])), "Weight"] = round(profiles["Height"]/2)
    profiles = profiles.drop("body_type", axis = 1)
    profiles["Hobbies"] = [random.choice(["Basketball", "Badminton", "Tennis", "Table Tennis", "Jogging", "Photography", "Painting",
                                         "Travelling", "Football", "Piano", "Violin", "Flute"]) for i in range(len(profiles))]
    profiles["username"] = ["acc"+str(i) for i in range(len(profiles))]
    profiles["password"] = "abcd"
    profiles["isVIP"] = [random.choice([True, False]) for i in range(len(profiles))]
    profiles["ContactInformation"] = [1234567890] * len(profiles)



    likes = likes_orig.drop("timestamp", axis=1)
    likes = likes.rename(columns = {"userId":"username", "movieId":"userviewed"})
    likes = likes.loc[likes["username"] < 59946]
    likes["username"] = "acc" + likes["username"].astype(str)
    likes = likes.loc[likes["userviewed"] < 59946]
    likes["userviewed"] = "acc" + likes["userviewed"].astype(str)
    likes["like"] = np.select(
        [
            likes['rating']==5,
            likes['rating']<5
        ],
        [
            1,
            0
        ],
        default=None
    )
    likes = likes.loc[0:50000,]
    likes = likes.drop('rating', axis= 1)
    reviewedind = random.sample(likes.index[likes.like == 1].tolist(), 5000)
    likes["rating"] = None
    likes.loc[reviewedind,"rating"] = [random.choice([1,2,3,4,5]) for i in range(len(reviewedind))]
    likes["comment"] = None
    likes.loc[reviewedind,"comment"] = ["good guy number "+str(i) for i in range(len(reviewedind))]
    likes["reviewId"] = None
    likes.loc[reviewedind,"reviewId"] = list(range(len(reviewedind)))
    reviews = likes.loc[reviewedind, ["reviewId", "rating", "comment"]]
    likes = likes.drop(['rating', "comment"], axis= 1)

    #profiles.to_csv("/Users/tristalli/Desktop/CSC207/course-project-linkout/src/main/data/profiles.csv", index=False)
    profiles.to_excel("/Users/tristalli/Desktop/CSC207/course-project-linkout/src/main/data/profiles.xls", index=False)

    #likes.to_csv("/Users/tristalli/Desktop/CSC207/course-project-linkout/src/main/data/likes.csv", index=False)
    likes.to_excel("/Users/tristalli/Desktop/CSC207/course-project-linkout/src/main/data/likes.xls", index=False)

    #reviews.to_csv("/Users/tristalli/Desktop/CSC207/course-project-linkout/src/main/data/reviews.csv", index=False)
    reviews.to_excel("/Users/tristalli/Desktop/CSC207/course-project-linkout/src/main/data/reviews.xls", index=False)


if __name__=="__main__":
    forgedata()

