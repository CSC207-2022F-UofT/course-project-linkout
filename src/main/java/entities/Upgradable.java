package entities;

import java.util.Hashtable;

public interface Upgradable {

    /**
     * Any class implement this interface can have the function to upgrade.
     */
    public Hashtable<String, Object> upgrade();
}
