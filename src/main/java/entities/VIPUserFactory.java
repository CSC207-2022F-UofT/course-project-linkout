package entities;

import java.util.Hashtable;
import java.util.List;

public class VIPUserFactory implements UserFactory {

    public User create(String password, String accountName, Profile profile, boolean isVip,
                       List<String> liked, List<String> likedme, Hashtable<Integer, List<Object>> reviews){
        return new VipUser(password, accountName, profile, true, liked, likedme, reviews);
    }
}
