package za.ac.cput.entity;
import za.ac.cput.entity.enums.RoleType;

import java.util.Objects;

public class Role {
    private long roleId;
    private RoleType roleType;

    protected Role() {}

    private Role(Builder builder) {
        this.roleId = builder.roleId;
        this.roleType = builder.roleType;

    }
    public long getRoleId() {
        return roleId;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return roleId == role.roleId && roleType == role.roleType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, roleType);
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleType=" + roleType +
                '}';
    }
    public static class Builder{
        private long roleId;
        private RoleType roleType;

        public Builder roleId(long roleId) {
            this.roleId = roleId;
            return this;
        }
        public Builder roleType(RoleType roleType) {
            this.roleType = roleType;
            return this;
        }
        public Builder copy(Role role) {
            this.roleId = role.getRoleId();
            this.roleType = role.getRoleType();
            return this;
        }
        public Role build() {
            return new Role(this);
        }
    }
}
