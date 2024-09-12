package za.ac.cput.factory;

import za.ac.cput.entity.Role;
import za.ac.cput.entity.enums.RoleType;

public class RoleFactory {

    // Factory method to create a Role with all details
    public static Role buildRole(long roleId, RoleType roleType) {
        if (roleId < 0 || roleType == null) {
            return null;
        }
        return new Role.Builder()
                .roleId(roleId)
                .roleType(roleType)
                .build();
    }

    // Factory method to create a Role without an ID
    public static Role buildRoleWithoutId(RoleType roleType) {
        if (roleType == null) {
            return null;
        }
        return new Role.Builder()
                .roleType(roleType)
                .build();
    }

}
