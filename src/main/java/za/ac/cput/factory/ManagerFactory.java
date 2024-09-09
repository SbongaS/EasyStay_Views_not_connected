package za.ac.cput.factory;

import za.ac.cput.entity.Manager;
import za.ac.cput.entity.User;

public class ManagerFactory {

    // Factory method to create a Manager with full details including managerId
    public static Manager buildManager(long managerId, User user) {
        if (managerId <= 0 || user == null ) {
            return null;
        }
        return new Manager.Builder()
                .setManagerId(managerId)
                .setUser(user)
                .build();
    }

    // Factory method to create a Manager without a managerId
    public static Manager buildManagerWithoutId( User user) {
        if (user == null ) {
            return null;
        }
        return new Manager.Builder()
                .setUser(user)
                .build();
    }
}

