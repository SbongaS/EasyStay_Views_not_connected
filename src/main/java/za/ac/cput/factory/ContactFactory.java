package za.ac.cput.factory;

import za.ac.cput.entity.Contact;

public class ContactFactory {

    // Factory method to create a Contact with full details including contactId
    public static Contact buildContact(long contactId, String phoneNumber, String email) {
        if (phoneNumber == null || email == null) {
            return null;
        }
        return new Contact.Builder()
                .setContactId(contactId)
                .setPhoneNumber(phoneNumber)
                .setEmail(email)
                .build();
    }

    // Factory method to create a Contact without a contactId
    public static Contact buildContactWithoutId(String phoneNumber, String email) {
        if (phoneNumber == null || email == null) {
            return null;
        }
        return new Contact.Builder()
                .setPhoneNumber(phoneNumber)
                .setEmail(email)
                .build();
    }
}
