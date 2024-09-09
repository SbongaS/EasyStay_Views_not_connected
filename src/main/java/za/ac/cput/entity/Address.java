package za.ac.cput.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long addressId;
    private String streetAddress;
    private String suburb;  // Fixed typo
    private String city;
    private String postalCode;
    private String country;

    protected Address() {}

    private Address(Builder builder) {
        this.addressId = builder.addressId;
        this.streetAddress = builder.streetAddress;
        this.suburb = builder.suburb;  // Fixed typo
        this.city = builder.city;
        this.postalCode = builder.postalCode;
        this.country = builder.country;
    }

    public long getAddressId() {
        return addressId;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getSuburb() {
        return suburb;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return addressId == address.addressId && Objects.equals(streetAddress, address.streetAddress) && Objects.equals(suburb, address.suburb) && Objects.equals(city, address.city) && Objects.equals(postalCode, address.postalCode) && Objects.equals(country, address.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressId, streetAddress, suburb, city, postalCode, country);
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressId=" + addressId +
                ", streetAddress='" + streetAddress + '\'' +
                ", suburb='" + suburb + '\'' +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

    public static class Builder {
        private long addressId;
        private String streetAddress;
        private String suburb;  // Fixed typo
        private String city;
        private String postalCode;
        private String country;

        public Builder setAddressId(long addressId) {
            this.addressId = addressId;
            return this;
        }
        public Builder setStreetAddress(String streetAddress) {
            this.streetAddress = streetAddress;
            return this;
        }

        public Builder setSuburb(String suburb) {  // Fixed typo
            this.suburb = suburb;
            return this;
        }

        public Builder setCity(String city) {
            this.city = city;
            return this;
        }

        public Builder setPostalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public Builder setCountry(String country) {
            this.country = country;
            return this;
        }

        public Builder copy(Address address) {
            this.addressId = address.addressId;
            this.streetAddress = address.streetAddress;
            this.suburb = address.suburb;  // Fixed typo
            this.city = address.city;
            this.postalCode = address.postalCode;
            this.country = address.country;
            return this;
        }

        public Address build() {
            return new Address(this);
        }
    }
}
