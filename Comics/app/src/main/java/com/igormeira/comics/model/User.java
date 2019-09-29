package com.igormeira.comics.model;

import java.util.Date;
import java.util.Objects;

/**
 * Classe modelo para User.
 */
public class User {

    private String name;
    private String email;
    private Date bithdate;
    private Address address;
    private String phone;

    /**
     * Construtor da classe.
     *
     * @param name
     * @param email
     * @param bithdate
     * @param address
     * @param phone
     */
    public User(String name, String email, Date bithdate, Address address, String phone) {
        this.name = name;
        this.email = email;
        this.bithdate = bithdate;
        this.address = address;
        this.phone = phone;
    }

    /**
     * Recupera o nome (name).
     *
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Modifica o nome (name).
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Recupera o email.
     *
     * @return String
     */
    public String getEmail() {
        return email;
    }

    /**
     * Modifica o emial.
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Repureca a data de nascimento (bithdate)
     *
     * @return Date
     */
    public Date getBithdate() {
        return bithdate;
    }

    /**
     * Modifica a data de nascimento (birthdate)
     *
     * @param bithdate
     */
    public void setBithdate(Date bithdate) {
        this.bithdate = bithdate;
    }

    /**
     * Recupera o endereço (address)
     *
     * @return Address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Modifica o endereço (address)
     *
     * @param address
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Recupera o telefone (phone)
     *
     * @return String
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Modifica o telefone (phone)
     *
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return name.equals(user.name) &&
                email.equals(user.email) &&
                bithdate.equals(user.bithdate) &&
                address.equals(user.address) &&
                phone.equals(user.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, bithdate, address, phone);
    }
}
