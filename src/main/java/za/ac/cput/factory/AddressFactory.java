package za.ac.cput.factory;

import za.ac.cput.entity.Address;
import za.ac.cput.util.Helper;

public class AddressFactory {

    // Factory method to create an Address with full details
    public static Address buildAddress(Long addressId, String streetAddress, String suburb,
                                       String city, String postalCode, String country) {
        if (addressId == null || addressId < 0 || Helper.isNullorEmpty(streetAddress) || Helper.isNullorEmpty(suburb) ||
                Helper.isNullorEmpty(city) || Helper.isNullorEmpty(postalCode) || Helper.isNullorEmpty(country)) {
            return null;
        }
        return new Address.Builder()
                .setAddressId(addressId)
                .setStreetAddress(streetAddress)
                .setSuburb(suburb)
                .setCity(city)
                .setPostalCode(postalCode)
                .setCountry(country)
                .build();
    }

    // Factory method to create an Address without an addressId
    public static Address buildAddressWithoutId(String streetAddress, String suburb,
                                                String city, String postalCode, String country) {
        if (Helper.isNullorEmpty(streetAddress) || Helper.isNullorEmpty(suburb) ||
                Helper.isNullorEmpty(city) || Helper.isNullorEmpty(postalCode) || Helper.isNullorEmpty(country)) {
            return null;
        }
        return new Address.Builder()
                .setStreetAddress(streetAddress)
                .setSuburb(suburb)
                .setCity(city)
                .setPostalCode(postalCode)
                .setCountry(country)
                .build();
    }
}
