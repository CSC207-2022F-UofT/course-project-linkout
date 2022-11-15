package entities;

import java.util.ArrayList;

public class RegularUser extends User implements Upgradable{

    private boolean isVIP = false;


    /**
     * Create a new Regular User with its profile. Regular User can be upgraded to VipUser.
     *
     * @param profile the profile associated with this regular user.
     */

    public RegularUser(String password, String accountName, Profile profile){
        super(password, accountName, profile);
    }

    @Override
    public VipUser upgrade() {
        this.isVIP = true;
        Profile profile = this.displayProfile();
        String password = this.getPassword();
        String accountName = this.getAccountName();
        return new VipUser(password, accountName, profile, true);
    }
}
