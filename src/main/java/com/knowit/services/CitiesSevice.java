package com.knowit.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knowit.entities.City;
import com.knowit.repository.CityRepository;

@Service
public class CitiesSevice {

    @Autowired
    CityRepository crepo;

    public List<City> getAllCities() {
        return crepo.findAll();
    }

    public List<Map<String, Object>> getCities() {
        List<Map<String, Object>> cityList = new ArrayList<>();
        List<City> cities = crepo.findAll(); // Fetching all cities from the repository

        for (City city : cities) {
            Map<String, Object> cityData = new HashMap<>();
            cityData.put("cityId", city.getCityId());
            cityData.put("cityName", city.getCityName());
            cityList.add(cityData);
        }

        return cityList; // Returning list of maps containing cityId and cityName
    }
}
