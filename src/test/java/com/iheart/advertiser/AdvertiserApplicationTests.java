package com.iheart.advertiser;

import com.iheart.advertiser.model.Advertiser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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

}
