package za.ac.cput.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long contactId;
    @Column
    private String phoneNumber;
    @Column
    private String email;

    protected Contact() {}

    private Contact(Builder builder) {
        this.contactId = builder.contactId;
        this.phoneNumber = builder.phoneNumber;
        this.email = builder.email;
    }

    public long getContactId() {
        return contactId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return contactId == contact.contactId && Objects.equals(phoneNumber, contact.phoneNumber) && Objects.equals(email, contact.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contactId, phoneNumber, email);
    }

    @Override
    public String toString() {
        return "Contact{" +
                "ContactId=" + contactId +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public static class Builder {
        private long contactId;
        private String phoneNumber;
        private String email;

        public Builder setContactId(long contactId) {
            this.contactId = contactId;
            return this;
        }
        public Builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder copy(Contact contact) {
            this.contactId = contact.contactId;
            this.phoneNumber = contact.phoneNumber;
            this.email = contact.email;
            return this;
        }

        public Contact build() {
            return new Contact(this);
        }
    }
}