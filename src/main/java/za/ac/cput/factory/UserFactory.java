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
                .setCreatedDate(createdDate)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setUserName(userName)
                .setPassword(password)
                .setRole(role)
                .build();
    }

    // Factory method to create a User without an ID
    public static User buildUserWithoutId(LocalDateTime createdDate,String firstName, String lastName, String userName, String password,
                                          Role role) {
        if (createdDate == null || userName == null || password == null || role == null) {
            return null;
        }
        return new User.Builder()
                .setCreatedDate(createdDate)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setUserName(userName)
                .setPassword(password)
                .setRole(role)
                .build();
    }
}
