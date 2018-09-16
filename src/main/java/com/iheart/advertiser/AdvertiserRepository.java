package com.iheart.advertiser;

import com.iheart.advertiser.model.Advertiser;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AdvertiserRepository {

    @Select("SELECT * FROM advertiser")
    List<Advertiser> getAllAdvertisers();

    @Insert("INSERT INTO advertiser(name, contact, credit) VALUES (#{name}, #{contact}, #{credit})")
    void addAdvertiser(Advertiser newAdvertiser);

    @Select("SELECT * FROM advertiser WHERE name = #{name}")
    Advertiser getAdvertiser(String name);

    @Update("UPDATE advertiser SET contact = #{contact}, credit = #{credit} WHERE name=#{name}")
    void updateAdvertiser(Advertiser updatedAdvertiser);

    @Delete("DELETE FROM advertiser WHERE name = #{name}")
    void deleteAdvertiser(String name);
}
