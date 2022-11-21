package use_cases;


import entities.*;

import java.util.Hashtable;
import java.util.List;

public class RegularUserManager extends UserManagerInteractor{

    public RegularUserManager(User regularUser){
        super(regularUser);
    }

    public User upgrade(){
        if (!showVIPStatus()) {
            Upgradable upgrade_user = (Upgradable) user;
            Hashtable<String, Object> info = upgrade_user.upgrade();
            // TODO: Need to delete the regularUser with the same accountName first then create the VIPUser
            UserFactory factory  = new UserFactory();
            return factory.create(
                    (String) info.get("password"),
                    (String) info.get("accountName"),
                    (Profile) info.get("profile"),
                    true,
                    (List<String>)  info.get("liked"),
                    (List<String>) info.get("likedMe"),
                    (Hashtable<Integer, List<Object>>) info.get("review"));
        }
        throw new IllegalArgumentException("User cannot be upgraded");
    }
}
