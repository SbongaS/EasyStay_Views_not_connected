package za.ac.cput.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long guestId;
    private String guestFirstName;
    private String guestLastName;
    private LocalDate guestDateOfBirth;
    private String guestGender;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "addressId", referencedColumnName = "addressId")
    private Address address;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "contactId", referencedColumnName = "contactId")
    private Contact contact;

    protected Guest() {}

    private Guest(Builder builder) {
        this.guestId = builder.guestId;
        this.guestFirstName = builder.guestFirstName;
        this.guestLastName = builder.guestLastName;
        this.guestDateOfBirth = builder.guestDateOfBirth;
        this.guestGender = builder.guestGender;
        this.address = builder.address;
        this.contact = builder.contact;
    }

    public Long getGuestId() {
        return guestId;
    }

    public String getGuestFirstName() {
        return guestFirstName;
    }

    public String getGuestLastName() {
        return guestLastName;
    }

    public LocalDate getGuestDateOfBirth() {
        return guestDateOfBirth;
    }

    public String getGuestGender() {
        return guestGender;
    }

    public Address getAddress() {
        return address;
    }

    public Contact getContact() {
        return contact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guest guest = (Guest) o;
        return guestId == guest.guestId && Objects.equals(guestFirstName, guest.guestFirstName) && Objects.equals(guestLastName, guest.guestLastName) && Objects.equals(guestDateOfBirth, guest.guestDateOfBirth) && Objects.equals(guestGender, guest.guestGender) && Objects.equals(address, guest.address) && Objects.equals(contact, guest.contact);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guestId, guestFirstName, guestLastName, guestDateOfBirth, guestGender, address, contact);
    }

    @Override
    public String toString() {
        return "Guest{" +
                "guestId=" + guestId +
                ", guestFirstName='" + guestFirstName + '\'' +
                ", guestLastName='" + guestLastName + '\'' +
                ", guestDateOfBirth='" + guestDateOfBirth + '\'' +
                ", guestGender='" + guestGender + '\'' +
                ", address=" + address +
                ", contact=" + contact +
                '}';
    }

    public static class Builder {
        private long guestId;
        private String guestFirstName;
        private String guestLastName;
        private LocalDate guestDateOfBirth;
        private String guestGender;
        private Address address;
        private Contact contact;

        public Builder setGuestId(Long guestId) {
            this.guestId = guestId;
            return this;
        }

        public Builder setGuestFirstName(String guestFirstName) {
            this.guestFirstName = guestFirstName;
            return this;
        }
        public Builder setGuestLastName(String guestLastName) {
            this.guestLastName = guestLastName;
            return this;
        }
        public Builder setGuestDateOfBirth(LocalDate guestDateOfBirth) {
            this.guestDateOfBirth = guestDateOfBirth;
            return this;
        }
        public Builder setGuestGender(String guestGender) {
            this.guestGender = guestGender;
            return this;
        }
        public Builder setAddress(Address address) {
            this.address = address;
            return this;
        }
        public Builder setContact(Contact contact) {
            this.contact = contact;
            return this;
        }

        public Builder copy(Guest guest) {
            this.guestId = guest.guestId;
            this.guestFirstName = guest.guestFirstName;
            this.guestLastName = guest.guestLastName;
            this.guestDateOfBirth = guest.guestDateOfBirth;

            return this;
        }
        public Guest build() {
            return new Guest(this);
        }
    }
}