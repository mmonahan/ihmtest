package com.iheart.advertiser.controller;

import com.iheart.advertiser.AdvertiserRepository;
import com.iheart.advertiser.model.Advertiser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdvertiserController {

    @Autowired
    AdvertiserRepository repository;

    @GetMapping("/api/advertiser")
    public List<Advertiser> getAdvertisers() {
        return repository.getAllAdvertisers();
    }

    @PostMapping("/api/advertiser")
    public Advertiser newAdvertiser(@RequestBody Advertiser newAdvertiser) {
        repository.addAdvertiser(newAdvertiser);
        return repository.getAdvertiser(newAdvertiser.getName());
    }

    @GetMapping("/api/advertiser/{name}")
    public Advertiser getAdvertiser(@PathVariable String name) {
        return repository.getAdvertiser(name);
    }

    @PutMapping("/api/advertiser/{name}")
    public Advertiser updateAdvertiser(@RequestBody Advertiser updatedAdvertiser) {
        repository.updateAdvertiser(updatedAdvertiser);
        return repository.getAdvertiser(updatedAdvertiser.getName());
    }

    @DeleteMapping("/api/advertiser/{name}")
    public void deleteAdvertiser(@PathVariable String name) {
        repository.deleteAdvertiser(name);
    }

    //TODO: GET hasEnoughCredit
    //TODO: POST deductCredit
}
