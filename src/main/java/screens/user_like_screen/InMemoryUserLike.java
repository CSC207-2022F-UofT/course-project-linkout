package screens.user_like_screen;

import entities.Profile;
import entities.RegularUser;
import entities.User;
import use_cases.user_action_use_case.UserActDsGateway;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// This class is for Testing UserLike use case.

public class InMemoryUserLike implements UserActDsGateway {

    //Map account Name to User
    private final Map<String, User> registeredUsers = new HashMap<>();

    //map account name to liked users' account name
    private final Map<String, ArrayList<String>> userToLiked = new HashMap<>();

    public InMemoryUserLike(){
        Profile profile = new Profile();
        RegularUser czz1 = new RegularUser("123456", "czz1", profile);
        RegularUser czz2 = new RegularUser("123456", "czz2", profile);
        this.registeredUsers.put("czz1", czz1);
        this.registeredUsers.put("czz2", czz2);
        this.userToLiked.put("czz1", new ArrayList<>());
        this.userToLiked.put("czz2", new ArrayList<>());
    }
    @Override
    public void setLike(String accName, String targetName){
        userToLiked.get(accName).add(targetName);
    }

    @Override
    public boolean isLiked(String actionerName, String targetName) {
        return userToLiked.get(actionerName).contains(targetName);
    }

    @Override
    public User findUser(String accName) {
        return registeredUsers.get(accName);
    }
}
