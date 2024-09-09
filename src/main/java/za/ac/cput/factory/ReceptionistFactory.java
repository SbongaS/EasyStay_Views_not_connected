package za.ac.cput.factory;

import za.ac.cput.entity.Receptionist;
import za.ac.cput.entity.User;

public class ReceptionistFactory {

    // Factory method to create a Receptionist with all details
    public static Receptionist buildReceptionist(long receptionistId, User user) {
        if (receptionistId < 0 || user == null ) {
            return null;
        }
        return new Receptionist.Builder()
                .setReceptionistId(receptionistId)
                .setUser(user)
                .build();
    }

    // Factory method to create a Receptionist without an ID
    public static Receptionist buildReceptionistWithoutId(User user) {
        if (user == null ) {
            return null;
        }
        return new Receptionist.Builder()
                .setUser(user)
                .build();
    }
}
