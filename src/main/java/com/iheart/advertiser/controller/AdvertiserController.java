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

        if (null == newAdvertiser.getName()) {
            throw new AdvertiserBadlyFormattedException(newAdvertiser);
        }

        if (null != repository.getAdvertiser(newAdvertiser.getName())) {
            throw new AdvertiserAlreadyExistsException(newAdvertiser.getName());
        }

        repository.addAdvertiser(newAdvertiser);
        return repository.getAdvertiser(newAdvertiser.getName());
    }

    @GetMapping("/api/advertiser/{name}")
    public Advertiser getAdvertiser(@PathVariable String name) {
        Advertiser foundAdvertiser = repository.getAdvertiser(name);
        if (null == foundAdvertiser) {
            throw new AdvertiserNotFoundException(name);
        }
        return foundAdvertiser;
    }

    @PutMapping("/api/advertiser/{name}")
    public Advertiser updateAdvertiser(@PathVariable String name, @RequestBody Advertiser updatedAdvertiser) {

        if (null == updatedAdvertiser.getName()) {
            throw new AdvertiserBadlyFormattedException(updatedAdvertiser);
        }

        if (null == repository.getAdvertiser(name)) {
            throw new AdvertiserNotFoundException(name);
        }

        repository.updateAdvertiser(name, updatedAdvertiser);
        return repository.getAdvertiser(updatedAdvertiser.getName());
    }

    @DeleteMapping("/api/advertiser/{name}")
    public void deleteAdvertiser(@PathVariable String name) {
        repository.deleteAdvertiser(name);
    }

    //TODO: GET hasEnoughCredit
    //TODO: POST deductCredit
}
