package com.igormeira.comics.model;

import java.util.Objects;

/**
 * Classe modelo para Address.
 */
public class Address {

    private String zipcode;
    private String street;
    private int number;
    private String complement;
    private String country;
    private String state;
    private String city;

    /**
     * Construtor da calsse Address.
     *
     * @param zipcode
     * @param street
     * @param number
     * @param complement
     * @param country
     * @param state
     * @param city
     */
    public Address(String zipcode, String street, int number, String complement, String country, String state, String city) {
        this.zipcode = zipcode;
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.country = country;
        this.state = state;
        this.city = city;
    }

    /**
     * Recupera o cep (zipcode).
     *
     * @return String
     */
    public String getZipcode() {
        return zipcode;
    }

    /**
     * Modifica o cep (zipcode).
     *
     * @param zipcode
     */
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    /**
     * Recupera a rua (street).
     *
     * @return String
     */
    public String getStreet() {
        return street;
    }

    /**
     * Modifica a rua (street).
     *
     * @param street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Recupera o número (number).
     *
     * @return int
     */
    public int getNumber() {
        return number;
    }

    /**
     * Modifica o número (number).
     *
     * @param number
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Recupera o complemento do endereço (complement).
     *
     * @return String
     */
    public String getComplement() {
        return complement;
    }

    /**
     * Modifica o complemento do endereço (complement).
     *
     * @param complement
     */
    public void setComplement(String complement) {
        this.complement = complement;
    }

    /**
     * Recupera o país (country).
     *
     * @return String
     */
    public String getCountry() {
        return country;
    }

    /**
     * Modifica o país (country).
     *
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Recupera o estado (state).
     *
     * @return String
     */
    public String getState() {
        return state;
    }

    /**
     * Modifica o estado (state).
     *
     * @param state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Recupera a cidade (city).
     *
     * @return String
     */
    public String getCity() {
        return city;
    }

    /**
     * Modifica a cidade (city).
     *
     * @param city
     */
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
