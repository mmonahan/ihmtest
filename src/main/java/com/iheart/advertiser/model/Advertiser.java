package com.iheart.advertiser.model;

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
}
