package com.iheart.advertiser.model;

import lombok.Data;

@Data
public class Advertiser {

    private String name;
    private String contact;
    private double credit;

    public Advertiser(String name, String contact, double credit) {
        this.name = name;
        this.contact = contact;
        this.credit = credit;
    }
}
