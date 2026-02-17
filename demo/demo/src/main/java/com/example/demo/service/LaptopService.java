package com.example.demo.service;

import com.example.demo.dto.LaptopDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LaptopService {
     List<LaptopDto> getAllLaptops();
     LaptopDto saveLaptop(LaptopDto laptopDto);
     LaptopDto getLaptopById(String id);
     LaptopDto updateLaptop(String id, LaptopDto laptopDto);
     void deleteLaptop(String id);

}
