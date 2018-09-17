package com.iheart.advertiser.controller;

public class CreditFormatException extends RuntimeException {

    public CreditFormatException(String message) {
        super(format(message));
    }

    public static String format(String credit) {
        return "Could not format credit amount: " + credit;
    }
}
