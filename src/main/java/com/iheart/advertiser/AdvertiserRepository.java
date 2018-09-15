package com.iheart.advertiser;

import com.iheart.advertiser.model.Advertiser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdvertiserRepository {

    @Select("SELECT * FROM advertiser")
    List<Advertiser> getAllAdvertisers();
}
