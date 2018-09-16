package com.iheart.advertiser.model;

import java.util.Objects;

public class Advertiser {

    private String name;
    private String contact;
    private double credit;

    public Advertiser(String name, String contact, double credit) {
        this.name = name;
        this.contact = contact;
        this.credit = credit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Advertiser that = (Advertiser) o;
        return Double.compare(that.credit, credit) == 0 &&
                Objects.equals(name, that.name) &&
                Objects.equals(contact, that.contact);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, contact, credit);
    }
}
