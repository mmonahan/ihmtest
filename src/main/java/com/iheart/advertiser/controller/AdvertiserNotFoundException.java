package com.iheart.advertiser.controller;

public class AdvertiserNotFoundException extends RuntimeException {

    public AdvertiserNotFoundException(String advertiserName) {
        super(format(advertiserName));
    }

    public static String format(String advertiserName) {
        return "Could not find Advertiser: " + advertiserName;
    }
}
