package com.iheart.advertiser;

import com.iheart.advertiser.controller.AdvertiserAlreadyExistsException;
import com.iheart.advertiser.controller.AdvertiserBadlyFormattedException;
import com.iheart.advertiser.controller.AdvertiserNotFoundException;
import com.iheart.advertiser.model.Advertiser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AdvertiserExceptionCaseTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testNewAdvertiserBadName() {
        Advertiser newAdvertiser = new Advertiser(null, "Jerald Zipp", 1400.0);

        ResponseEntity<String> result = restTemplate.postForEntity("/api/advertiser", newAdvertiser, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertEquals(AdvertiserBadlyFormattedException.format(newAdvertiser), result.getBody());
    }

    @Test
    public void testNewAdvertiserAlreadyExists() {
        Advertiser newAdvertiser = new Advertiser("WowAdLand", "Camie Ranallo", 450.0);

        restTemplate.postForObject("/api/advertiser", newAdvertiser, Advertiser.class);
        ResponseEntity<String> result = restTemplate.postForEntity("/api/advertiser", newAdvertiser, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertEquals(AdvertiserAlreadyExistsException.format(newAdvertiser.getName()), result.getBody());
    }

    @Test
    public void testGetAdvertiserNotFound() {
        ResponseEntity<String> result = restTemplate.getForEntity("/api/advertiser/NonExistingCo", String.class);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertEquals(AdvertiserNotFoundException.format("NonExistingCo"), result.getBody());
    }

    @Test
    public void testUpdateAdvertiserBadName() {
        String advertiserName = "NoMoreBadUpdates";
        Advertiser newAdvertiser = new Advertiser(advertiserName, "Leo Laforest", 1030.0);
        Advertiser updateAdvertiser = new Advertiser(null, "Viola Gifford", 548.0);

        restTemplate.postForObject("/api/advertiser", newAdvertiser, Advertiser.class);
        HttpEntity<Advertiser> entity = new HttpEntity<>(updateAdvertiser, new HttpHeaders());
        ResponseEntity<String> result = restTemplate.exchange(
                "/api/advertiser/" + advertiserName, HttpMethod.PUT, entity, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertEquals(AdvertiserBadlyFormattedException.format(updateAdvertiser), result.getBody());
    }

    @Test
    public void testUpdateAdvertiserDoesNotAlreadyExist() {
        String advertiserName = "NotThereYet";
        Advertiser updateAdvertiser = new Advertiser("UpdateIsImpossible", "Viola Gifford", 8.0);

        HttpEntity<Advertiser> entity = new HttpEntity<>(updateAdvertiser, new HttpHeaders());
        ResponseEntity<String> result = restTemplate.exchange(
                "/api/advertiser/" + advertiserName, HttpMethod.PUT, entity, String.class);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertEquals(AdvertiserNotFoundException.format(advertiserName), result.getBody());
    }
}
