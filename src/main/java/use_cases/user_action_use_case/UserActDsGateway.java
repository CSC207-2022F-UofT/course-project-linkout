package use_cases.user_action_use_case;

import entities.User;

import java.io.IOException;

// use case layer

public interface UserActDsGateway {

    /** Save the user's like information into database
     * @param accName The account name of the user who wants to like another user
     * @param targetName The account name of the target user
     * @throws IOException if failed in searching in database, throw IOException.
     */
    void setLike(String accName, String targetName) throws IOException;


    /**
     * @param actionerName The account name of the user who wants to like another user
     * @param targetName The account name of the target user
     * @return  Return True if user actioner liked user target
     * @throws IOException if failed in searching in database, throw IOException.
     */
    boolean isLiked(String actionerName, String targetName) throws IOException;

    /**
     * @param accName a valid account name
     * @return a user instance
     */
    User findUser(String accName);


}
