package com.med.aftas.serverside.Service.Impl;

import com.med.aftas.serverside.Dto.FishDto;
import com.med.aftas.serverside.Dto.RespDto.FishRespDto;
import com.med.aftas.serverside.Exception.ResourceNotFoundException;
import com.med.aftas.serverside.Model.Fish;
import com.med.aftas.serverside.Repository.FishRepository;
import com.med.aftas.serverside.Service.FishService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FishServiceImpl implements FishService {
    @Autowired
    private FishRepository fishRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public FishRespDto save(FishDto fishDto) {
        Fish fish = modelMapper.map(fishDto, Fish.class);
        return modelMapper.map(fishRepository.save(fish), FishRespDto.class);
    }

    @Override
    public void delete(String id) {
        fishRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Fish Not found with this: " + id));
        fishRepository.deleteById(id);
    }

    @Override
    public FishRespDto update(String id, FishDto fishDto) {
        Fish existingFish = fishRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Fish Not found with this: " + id));
        return modelMapper.map(fishRepository.save(existingFish), FishRespDto.class);
    }

    @Override
    public FishRespDto findOne(String id) {
        return fishRepository.findById(id)
                .map(fish -> modelMapper.map(fish, FishRespDto.class)).orElseThrow(() -> new ResourceNotFoundException("Fish Not found with this: " + id));
    }

    @Override
    public List<FishRespDto> findAll() {
        return fishRepository.findAll().stream().map(fish -> modelMapper.map(fish, FishRespDto.class)).collect(Collectors.toList());
    }

    @Override
    public Page<FishRespDto> findWithPagination(Pageable pageable) {
        Page<Fish> fishsPage = fishRepository.findAll(pageable);
        return fishsPage.map(fish -> modelMapper.map(fish, FishRespDto.class));
    }
}
