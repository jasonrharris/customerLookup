package com.jasonharris.customerlookup.api;

public class Address {
    private final String addressLine1;
    private final String addressLine2;
    private final String town;
    private final String postCode;

    public Address(String addressLine1, String addressLine2, String town, String postCode) {
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.town = town;
        this.postCode = postCode;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public String getTown() {
        return town;
    }

    public String getPostCode() {
        return postCode;
    }
}
