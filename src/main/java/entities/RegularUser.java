package entities;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class RegularUser extends User implements Upgradable{

    private boolean isVIP = false;


    /**
     * Create a new Regular User with its profile. Regular User can be upgraded to VipUser.
     *
     * @param profile the profile associated with this regular user.
     */

    public RegularUser(String password, String accountName, Profile profile,
                       List<String> liked, List<String> likedme, Hashtable<Integer, List<Object>> reviews){
        super(password, accountName, profile, liked, likedme, reviews);
    }

    @Override
    public Hashtable<String, Object> upgrade() {
        this.isVIP = true;
        Profile profile = this.displayProfile();
        String password = this.getPassword();
        String accountName = this.getAccountName();
        List<String> liked = this.showLiked();
        List<String> likedMe = this.showLikedMe();
        Hashtable<Integer, List<Object>> review = this.getReviews();

        Hashtable<String, Object> lstInfo = new Hashtable<>();
        lstInfo.put("profile", profile);
        lstInfo.put("password", password);
        lstInfo.put("accountName", accountName);
        lstInfo.put("liked", liked);
        lstInfo.put("likedMe", likedMe);
        lstInfo.put("review", review);
        return lstInfo;
    }
}
