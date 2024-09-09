package za.ac.cput.entity;

import java.util.Objects;

public class Manager {
    private long managerId;
    private User user;

    protected Manager() {}

    private Manager(Builder builder) {
        this.managerId = builder.managerId;
        this.user = builder.user;
    }

    public long getManagerId() {
        return managerId;
    }

    public User getUser() {
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Manager manager = (Manager) o;
        return managerId == manager.managerId && Objects.equals(user, manager.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(managerId, user);
    }

    @Override
    public String toString() {
        return "Manager{" +
                "managerId=" + managerId +
                ", user=" + user +
                '}';
    }

    public static class Builder{
        private long managerId;
        private User user;

        public Builder setManagerId(long managerId) {
            this.managerId = managerId;
            return this;
        }
        public Builder setUser(User user) {
            this.user = user;
            return this;
        }

        public Builder copy(Manager manager){
            this.managerId = manager.getManagerId();
            this.user = manager.getUser();
            return this;
        }

        public Manager build(){
            return new Manager(this);
        }
    }
}
