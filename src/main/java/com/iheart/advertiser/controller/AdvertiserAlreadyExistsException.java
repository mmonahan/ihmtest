package com.iheart.advertiser.controller;

public class AdvertiserAlreadyExistsException extends RuntimeException {

    public AdvertiserAlreadyExistsException(String advertiserName) {
        super(format(advertiserName));
    }

    public static String format(String advertiserName) {
        return "Advertiser already exists: " + advertiserName;
    }
}
