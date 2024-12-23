package com.knowit.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knowit.entities.CarModel;
import com.knowit.repository.CarModelRepo;

@Service
public class CarModelService {
    @Autowired
    CarModelRepo cmrepo;

    public List<CarModel> getAllCarModels() {
        return cmrepo.findAll();
    }
}
