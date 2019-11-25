package com.jasonharris.customerlookup.api;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return firstName.equals(customer.firstName) &&
                surname.equals(customer.surname) &&
                address.equals(customer.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, surname);
    }
}
