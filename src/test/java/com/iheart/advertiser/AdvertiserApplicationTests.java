package com.iheart.advertiser;

import com.iheart.advertiser.model.Advertiser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdvertiserApplicationTests {

    @Autowired
    private AdvertiserRepository repository;

    @Test
    public void contextLoads() {
    }

    @Test
    public void getAllAdvertisers() {
        List<Advertiser> advertiserList = repository.getAllAdvertisers();
        assertNotNull(advertiserList);
        assertFalse(advertiserList.isEmpty());
    }

}
