package com.jasonharris.customerlookup.api;

public class Customer {
    private final String firstName;
    private final String surname;
    private final String phoneNumber;
    private final Address address;

    public Customer(String firstName, String surname, String phoneNumber, Address address) {
        this.firstName = firstName;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public String getSurname() {
        return surname;
    }
}
