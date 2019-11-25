package com.jasonharris.customerlookup.api;

import java.util.Objects;

public class Customer {
    private static final int UNSET_ID = -1;
    private final int id;
    private final String firstName;
    private final String surname;
    private final String phoneNumber;

    public Customer() {
        this(UNSET_ID,"","","");
    }

    public Customer(int id, String firstName, String surname, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getSurname() {
        return surname;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return firstName.equals(customer.firstName) &&
                surname.equals(customer.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, surname);
    }
}
