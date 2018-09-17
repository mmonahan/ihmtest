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
            throw new AdvertiserFormatException(newAdvertiser);
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
            throw new AdvertiserFormatException(updatedAdvertiser);
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

    @GetMapping("/api/advertiser/{name}/checkCredit")
    public Boolean checkCredit(@PathVariable String name, @RequestParam(value = "amount", defaultValue = "0.01") String amount) {
        Advertiser foundAdvertiser = repository.getAdvertiser(name);
        if (null == foundAdvertiser) {
            throw new AdvertiserNotFoundException(name);
        }

        try {
            Double required = Double.valueOf(amount);
            return foundAdvertiser.getCredit() >= required;
        } catch (NumberFormatException ex) {
            throw new CreditFormatException(amount);
        }
    }

    @PostMapping("/api/advertiser/{name}/deductCredit")
    public Advertiser deductCredit(@PathVariable String name, @RequestBody Double amount) {
        Advertiser foundAdvertiser = repository.getAdvertiser(name);
        if (null == foundAdvertiser) {
            throw new AdvertiserNotFoundException(name);
        }

        repository.updateCredit(name, foundAdvertiser.getCredit() - amount);
        return repository.getAdvertiser(name);
    }
}
