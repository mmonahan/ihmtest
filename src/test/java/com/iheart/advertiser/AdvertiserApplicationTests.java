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
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AdvertiserApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testAllAdvertisers() {
        ResponseEntity<List<Advertiser>> response =
                restTemplate.exchange("/api/advertiser", HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<Advertiser>>(){});

        assertNotNull(response.getBody());
        assertFalse(response.getBody().isEmpty());
    }

    @Test
    public void testNewAdvertiser() {
        Advertiser newAdvertiser = new Advertiser("SellingTime", "Yen Remigio", 1000.0);
        Advertiser result = restTemplate.postForObject("/api/advertiser", newAdvertiser, Advertiser.class);

        assertEquals(newAdvertiser, result);
    }

    @Test
    public void testGetAdvertiser() {
        Advertiser result = restTemplate.getForObject("/api/advertiser/AdvertCo", Advertiser.class);
        assertEquals(new Advertiser("AdvertCo","Emina Alhambra", 100.0), result);
    }

    @Test
    public void testUpdateAdvertiser() {
        String advertiserName = "UpdateYourImage";
        Advertiser newAdvertiser = new Advertiser(advertiserName, "Luciano Hurla", 1000.0);
        String updateName = "UpdateName";
        Advertiser updateAdvertiser = new Advertiser(updateName, "Update Contact", 777.0);

        restTemplate.postForObject("/api/advertiser", newAdvertiser, Advertiser.class);
        restTemplate.put("/api/advertiser/" + advertiserName, updateAdvertiser);
        Advertiser result = restTemplate.getForObject("/api/advertiser/" + updateName, Advertiser.class);

        assertEquals(updateAdvertiser, result);
    }

    @Test
    public void testDeleteAdvertiser() {
        String advertiserName = "DeleteYourWoes";
        Advertiser newAdvertiser = new Advertiser(advertiserName, "", 0.0);

        restTemplate.postForObject("/api/advertiser", newAdvertiser, Advertiser.class);
        restTemplate.delete("/api/advertiser/" + advertiserName);
        ResponseEntity<String> result = restTemplate.getForEntity("/api/advertiser/" + advertiserName, String.class);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertEquals(AdvertiserNotFoundException.format(advertiserName), result.getBody());
    }
}
