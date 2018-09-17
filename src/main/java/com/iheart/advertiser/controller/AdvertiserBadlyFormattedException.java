package com.iheart.advertiser.controller;

import com.iheart.advertiser.model.Advertiser;

public class AdvertiserBadlyFormattedException extends RuntimeException {

    public AdvertiserBadlyFormattedException(Advertiser newAdvertiser) {
        super(format(newAdvertiser));
    }

    public static String format(Advertiser newAdvertiser) {
        return "Poorly formatted Advertiser: " + newAdvertiser;
    }
}
