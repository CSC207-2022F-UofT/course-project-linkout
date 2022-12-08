# 2022 Fall CSC207H1 Group 127 Project: LinkedOut

A versatile modern dating application for all your relationship needs! The interface is based on Java Swing.

**Notes**:
- This app can only accommodate MacOS system!!!
- The Apache Excel POI package and a Python interpreter are required to run this program. The link to the Apache package is under src/"Package required for accessing database".

## Group members:
Joe ?
Clara Hu, Tristal Li, Ryan Shi, Michelle Xu, Alex Yin, Yifei Zhang.

## Project outline

### Entities

- The central entity is the account. Its childen are Admin and Profile, which in turn has children User, RegularUser, and VipUser.

### Use cases / functionality

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

#### Review usecase

>1. When two users liked each other, they can write reviews for each other
>2. A review object contains: int rating, String comment, String writer (writer’s name), String receiver (receiver’s name), int id
>3. Create a review: input writer’s name, receiver’s name, comment and rating
>4. Delete a review: input the review’s id
>5. Hide a review (VIP): input the review’s id (to be implemented later)

##### Classes

>ReviewController\
>ReviewRequestModel
ReviewInputBoundary\
ReviewInteratcor\
ReviewGateway
ReviewGatewayImplementation
ReviewOutputBoundary\
ReviewPresenter\
ReviewResponseModel

##### How the code works

>The add review’s flow goes like this: when the ReviewController takes in the input information from the screen, it then
> constructs a ReviewRequestModel and puts all input information into the request model, it then calls ReviewInputBoundary
> to add the review to the system. The ReviewInteratcor which implements ReviewInputBoundary will construct the review
> object, call the ReviewGateway to save the review to the database, and call ReviewOutputBoundary to report the success
> of adding the review, and the ReviewPresenter which implements the ReviewOutputBoundary will execute the implemented
> method and return a ReviewResponseModel.

>The delete review’s flow is basically the same. It’s just now a request model is not needed since the user only need to
> input the review’s id to delete it.

#### Search Use Case

>Everyone has their personal preference in terms of what type of <the one> they are interested in. The search use case provides the users with the functionality of search according to thier preference.\
  
>Search Function Workflow\
  (1) User log in successfully and automatically being promoted to the main page;\
  (2) User could type in the features they are looking in the field provide;\
  (3) Press search button;\
  (4) The app will generate a list of users that satisfied the following condition:\
      (a) matches with the features; \
      (b) max of 20 matched users being displayed (for the purpose of overwhelming results that will exhasuted user's eyes);\
      (c) similar to recommend, the list of matched users have not been seen by the user(who press the search button) beofre.\
  (5) After the result being generated, the user could go through the basic info one by one (line by line) and navigate any function of the app they woudl   like to use (e.g. like the user, write review for the user, report the user, check profile, find similar)
 
##### Search Classes
>SearchController\
>SearchRequestModel\
SearchInputBoundary\
SearchInteratcor\
SearchDSGateway\
SearchGateway\
SearchOutputBoundary\
SearchPresenter\
SearchResponseModel

###### How the code works

> The SearchController takes in the input information(keywords entered + username) from the screen, it then
> constructs a SearchRequestModel and puts all input information into the request model, it then calls SearchInputBoundary
> to search the keywords. The SearchInteratcor which implements SearchInputBoundary will construct the searchGateway
> to search from the database for the users that matched the keywords entered, and call SearchoutputBoundary to prepare the success
> search result, and the SearchPresenter which implements the SearchOutputBoundary will execute the implemented
> method and return a SearchResponseModel.

### Design patterns

We have implemented the Factory design pattern for the User entity.

Design patterns which would be useful if added:

### Testing

Our testing code coverage is displayed in the following screenshot:

### Looking forward

We surmise that we can extend our project by [].
