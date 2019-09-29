package com.igormeira.comics.model;

import java.util.Objects;

public class Address {

    private String zipcode;
    private String street;
    private int number;
    private String complement;
    private String country;
    private String state;
    private String city;

    public Address(String zipcode, String street, int number, String complement, String country, String state, String city) {
        this.zipcode = zipcode;
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.country = country;
        this.state = state;
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return number == address.number &&
                zipcode.equals(address.zipcode) &&
                street.equals(address.street) &&
                complement.equals(address.complement) &&
                country.equals(address.country) &&
                state.equals(address.state) &&
                city.equals(address.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zipcode, street, number, complement, country, state, city);
    }
}
