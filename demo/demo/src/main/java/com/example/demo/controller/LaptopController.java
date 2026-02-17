package com.example.demo.controller;

import com.example.demo.dto.LaptopDto;
import com.example.demo.service.LaptopService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/laptops")
@Validated
public class LaptopController {

    private LaptopService service;

    public LaptopController(LaptopService service) {
        this.service = service;
    }
    @GetMapping("/")
    public ResponseEntity<List<LaptopDto>> getAllLaptops(){
        List<LaptopDto> allLaptops = this.service.getAllLaptops();
        return ResponseEntity.ok().body(allLaptops);

    }
    @PostMapping("/")
    public ResponseEntity<LaptopDto> saveLaptop(@RequestBody @Valid LaptopDto laptopDto){
        LaptopDto laptopDto1 = this.service.saveLaptop(laptopDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(laptopDto1);
    }
    @GetMapping("/{id}")
    public ResponseEntity<LaptopDto> getLaptopById(@PathVariable String id){
        LaptopDto laptop = this.service.getLaptopById(id);
        return ResponseEntity.ok().body(laptop);
    }
    @PutMapping("/{id}")
    public ResponseEntity<LaptopDto> updateLaptop(@PathVariable String id, @RequestBody LaptopDto laptopDto){
        LaptopDto laptopDto1 = this.service.updateLaptop(id, laptopDto);
        return ResponseEntity.ok().body(laptopDto1);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLaptop(@PathVariable String id){
        this.service.deleteLaptop(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
