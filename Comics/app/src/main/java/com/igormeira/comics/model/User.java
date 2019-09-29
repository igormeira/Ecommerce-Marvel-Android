package com.igormeira.comics.model;

import java.util.Date;
import java.util.Objects;

public class User {

    private String name;
    private String email;
    private Date bithdate;
    private Address address;
    private String phone;

    public User(String name, String email, Date bithdate, Address address, String phone) {
        this.name = name;
        this.email = email;
        this.bithdate = bithdate;
        this.address = address;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBithdate() {
        return bithdate;
    }

    public void setBithdate(Date bithdate) {
        this.bithdate = bithdate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

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
