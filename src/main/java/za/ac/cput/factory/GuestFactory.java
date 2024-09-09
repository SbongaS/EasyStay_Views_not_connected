package za.ac.cput.factory;

import za.ac.cput.entity.Address;
import za.ac.cput.entity.Contact;
import za.ac.cput.entity.Guest;

import java.time.LocalDate;

public class GuestFactory {

    // Factory method to create a Guest with full details including guestId
    public static Guest buildGuest(long guestId, String guestFirstName, String guestLastName,
                                   LocalDate guestDateOfBirth, String guestGender, Address address, Contact contact) {
        if (guestFirstName == null || guestLastName == null || guestDateOfBirth == null || guestGender == null ||
                address == null || contact == null) {
            return null;
        }
        return new Guest.Builder()
                .setGuestId(guestId)
                .setGuestFirstName(guestFirstName)
                .setGuestLastName(guestLastName)
                .setGuestDateOfBirth(guestDateOfBirth)
                .setGuestGender(guestGender)
                .setAddress(address)
                .setContact(contact)
                .build();
    }

    // Factory method to create a Guest without a guestId
    public static Guest buildGuestWithoutId(String guestFirstName, String guestLastName,
                                            LocalDate guestDateOfBirth, String guestGender, Address address, Contact contact) {
        if (guestFirstName == null || guestLastName == null || guestDateOfBirth == null || guestGender == null ||
                address == null || contact == null) {
            return null;
        }
        return new Guest.Builder()
                .setGuestFirstName(guestFirstName)
                .setGuestLastName(guestLastName)
                .setGuestDateOfBirth(guestDateOfBirth)
                .setGuestGender(guestGender)
                .setAddress(address)
                .setContact(contact)
                .build();
    }
}
