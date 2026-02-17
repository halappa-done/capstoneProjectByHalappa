package com.example.demo.serviceImpl;

import com.example.demo.dto.LaptopDto;
import com.example.demo.dto.ProductDto;
import com.example.demo.entity.Laptop;
import com.example.demo.repository.LaptopRepository;
import com.example.demo.service.LaptopService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LaptopServiceImpl implements LaptopService {
    private LaptopRepository repository;
    private ModelMapper modelMapper;

    public LaptopServiceImpl(LaptopRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<LaptopDto> getAllLaptops() {
        List<Laptop> allLaptops = repository.findAll();

        return allLaptops
                .stream()
                .map(pr -> modelMapper.map(pr, LaptopDto.class))
                .toList();
    }

    @Override
    public LaptopDto saveLaptop(LaptopDto laptopDto) {

        Laptop laptop = modelMapper.map(laptopDto, Laptop.class);
        Laptop save = repository.save(laptop);
        return modelMapper.map(save, LaptopDto.class);
    }

    @Override
    public LaptopDto getLaptopById(String id) {
        Laptop laptop = repository.findById(id).orElseThrow();//add msg
        return modelMapper.map(laptop, LaptopDto.class);
    }

    @Override
    public LaptopDto updateLaptop(String id, LaptopDto laptopDto) {
        LaptopDto laptopDt = getLaptopById(id);

        laptopDt.setModel(laptopDto.getModel());
        laptopDt.setPrice(laptopDto.getPrice());

        Laptop laptop = modelMapper.map(laptopDt, Laptop.class);
        Laptop savedLaptop = repository.save(laptop);

        return modelMapper.map(savedLaptop, LaptopDto.class);
    }

    @Override
    public void deleteLaptop(String id) {
        repository.deleteById(id);
    }
}
