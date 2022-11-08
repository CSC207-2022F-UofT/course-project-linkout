package entities;

import java.util.ArrayList;

public class RegularUser extends User implements Upgradable{

    private boolean isVIP = false;


    /**
     * Create a new Regular User with its profile. Regular User can be upgraded to VipUser.
     *
     * @param profile the profile associated with this regular user.
     */

    public RegularUser(Profile profile){
        super(profile);
    }

    @Override
    public VipUser upgrade() {
        this.isVIP = true;
        Profile profile = this.displayProfile();
        return new VipUser(profile, true);
    }
}
