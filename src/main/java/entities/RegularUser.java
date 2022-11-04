package entities;

import java.util.ArrayList;

public class RegularUser extends User implements Upgradable{

    private boolean isVIP = false;

    public RegularUser(Profile profile){
        super(profile);
    }

    @Override
    public void upgrade() {
        this.isVIP = true;
    }
}
