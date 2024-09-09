package za.ac.cput.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Receptionist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long receptionistId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User user;

    protected Receptionist() {}

    public Receptionist(Builder builder) {
        this.receptionistId = builder.receptionistId;
        this.user = builder.user;
    }

    public long getReceptionistId() {
        return receptionistId;
    }

    public User getUser() {
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Receptionist that = (Receptionist) o;
        return receptionistId == that.receptionistId && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(receptionistId, user);
    }

    @Override
    public String toString() {
        return "Receptionist{" +
                "receptionistId=" + receptionistId +
                ", user=" + user +
                '}';
    }

    public static class Builder{
        private long receptionistId;
        private User user;

        public Builder setReceptionistId(long receptionistId) {
            this.receptionistId = receptionistId;
            return this;
        }

        public Builder setUser(User user) {
            this.user = user;
            return this;
        }

        public Builder copy(Receptionist receptionist) {
            this.receptionistId = receptionist.receptionistId;
            this.user = receptionist.user;
            return this;
        }
        public Receptionist build() {
            return new Receptionist(this);
        }

    }
}
