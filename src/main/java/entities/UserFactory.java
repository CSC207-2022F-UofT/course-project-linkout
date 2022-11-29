package entities;

import java.util.Hashtable;
import java.util.List;

public class UserFactory {
    public User create(String password, String accountName, Profile profile, boolean isVIP,
                       java.util.List<String> liked, List<String> likedme, Hashtable<Integer, List<Object>> reviews){
        return new VipUser(password, accountName, profile, isVIP, liked, likedme, reviews);
    }

    public User create(String password, String accountName, Profile profile,
                       java.util.List<String> liked, List<String> likedme, Hashtable<Integer, List<Object>> reviews){
        return new RegularUser(password, accountName, profile, liked, likedme, reviews);
    }

}
