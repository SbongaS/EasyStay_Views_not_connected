package za.ac.cput.factory;

import za.ac.cput.entity.Role;
import za.ac.cput.entity.User;
import za.ac.cput.util.Helper;

import java.time.LocalDateTime;

public class UserFactory {

    // Factory method to create a User with all details
    public static User buildUser(long userId, LocalDateTime createdDate,String firstName, String lastName, String userName, String password,
                                 Role role) {
        if (userId < 0 || createdDate == null || Helper.isNullorEmpty(firstName) ||
                        Helper.isNullorEmpty(lastName) || Helper.isNullorEmpty(userName)|| role == null) {
            return null;
        }
        return new User.Builder()
                .setUserId(userId)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setUserName(userName)
                .setPassword(password)
                .setRole(role)
                .build();
    }

    // Factory method to create a User without an ID
    public static User buildUserWithoutId(String firstName, String lastName, String userName, String password,
                                          Role role) {
        if (Helper.isValidEmail(userName) ||Helper.isNullorEmpty(firstName)||Helper.isNullorEmpty(lastName) || password == null || role == null) {
            return null;
        }
        return new User.Builder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setUserName(userName)
                .setPassword(password)
                .setRole(role)
                .build();
    }
}
