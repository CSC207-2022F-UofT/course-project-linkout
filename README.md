# 2022 Fall CSC207H1 Group 127 Project: LinkedOut

A versatile modern dating application for all your relationship needs! The interface is based on Java Swing.\
**Caution**: This app can only accommodate MacOS system!!!

## Group members:

## Project outline:

### Entities:

### Use cases / functionality:

#### Recommend Use Case

For a dating app, it is important to recommend users with other users that they may be interested in. 
This use case implements the functionality of recommending other users to the given user. 

For example, you enter the main page and pressed the recommend button, the app can automatically 
recommend a group of other users that you may be interested in and has never seen before
based on our data about you and other users. Based on your gender and sexuality information, the app
will only recommend you those with the gender you usually date with. 

There are two forms of recommendation: **"similar"** and **"recommend"**

##### Similar

When user A is interested in finding similar users to user B, he or she can hit "Similar" button. The app
will then call a K-Nearest-Neighbours model. The model will consider the "liking patterns" (which group of 
users "liked" them) for user B, and then find 20 users who are liked by a similar group of users. 
Finally, the app will display those similar users with the desired gender.

##### Recommend

We have two kinds of recommendation algorithms designed for two kinds of users

1. **New users (never "Liked" any other users before):** We automatically recommend a group of popular users 
(mostly seen by others) of the desired gender to them and display the list on the main screen. 

2. **Old users (has "Liked" other users before):** We use Collaborative Filtering based on K-Nearest-Neighbours 
to find out the users they may like. For recommendation to user A, the algorithm will first automatically pick several 
users with the highest ratings from A. If A hasn't reviewed anyone before, the app will randomly pick 
some users A has "liked" as the basis of recommendation. Based on our database, the K-Nearest-Neighbours model 
will pick 10 users with similar "liking patterns" (liked by similar group of users) for each selected basis user, 
filter out the users who have already been seen, and combine to a maximum of 40 users to recommend. After that, the 
app will display the users with the desired gender. 

### Design patterns:

We have implemented the Factory design pattern for the User entity.

Design patterns which would be useful if added:

### Testing:
