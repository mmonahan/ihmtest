package com.iheart.advertiser.controller;

import com.iheart.advertiser.AdvertiserRepository;
import com.iheart.advertiser.model.Advertiser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AdvertiserController {

    @Autowired
    AdvertiserRepository repository;

    @GetMapping("/api/advertiser")
    public List<Advertiser> getAdvertisers() {
        return repository.getAllAdvertisers();
    }
}
