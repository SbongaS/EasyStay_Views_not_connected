package za.ac.cput.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    @CreationTimestamp
    private LocalDateTime createdDate;
    private String firstName;
    private String lastName;
    @Column(unique = true, nullable = false)
    private String userName;
    private String password;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "role_role_id", nullable = false)
    private Role role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Receptionist receptionist;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Manager manager;

    protected User() {}

    private User(Builder builder) {
        this.userId = builder.userId;
        this.createdDate = builder.createdDate;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.userName = builder.userName;
        this.password = builder.password;
        this.role = builder.role;
    }

    public long getUserId() {
        return userId;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId && Objects.equals(createdDate, user.createdDate) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(userName, user.userName) && Objects.equals(password, user.password) && Objects.equals(role, user.role) && Objects.equals(receptionist, user.receptionist) && Objects.equals(manager, user.manager);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, createdDate, firstName, lastName, userName, password, role, receptionist, manager);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", createdDate=" + createdDate +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }

    public static class Builder {
        private long userId;
        private LocalDateTime createdDate;
        private String firstName;
        private String lastName;
        private String userName;
        private String password;
        private Role role;

        public Builder setUserId(long userId) {
            this.userId = userId;
            return this;
        }
        public Builder setCreatedDate(LocalDateTime createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }
        public Builder setUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }
        public Builder setRole(Role role) {
            this.role = role;
            return this;
        }


        public Builder copy(User user) {
            this.userId = user.getUserId();
            this.createdDate = user.getCreatedDate();
            this.firstName = user.getFirstName();
            this.lastName = user.getLastName();
            this.userName = user.getUserName();
            this.password = user.getPassword();
            this.role = user.getRole();
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
