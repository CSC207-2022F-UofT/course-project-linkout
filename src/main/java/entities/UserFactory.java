package entities;

import java.util.Hashtable;
import java.util.List;

public interface UserFactory {
    public User create(String password, String accountName, Profile profile, boolean isVip,
                       List<String> liked, List<String> likedme, Hashtable<Integer, List<Object>> reviews);

}
