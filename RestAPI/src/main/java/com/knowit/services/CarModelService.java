package com.knowit.services;

import com.knowit.entities.CarModel;
import com.knowit.repository.CarModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarModelService {

    @Autowired
    private CarModelRepository carModelRepository;

    public List<CarModel> getAllCarModels() {
        return carModelRepository.findAll();
    }
}
