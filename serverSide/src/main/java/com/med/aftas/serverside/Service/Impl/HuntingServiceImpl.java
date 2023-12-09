package com.med.aftas.serverside.Service.Impl;

import com.med.aftas.serverside.Dto.HuntingDto;
import com.med.aftas.serverside.Exception.ResourceNotFoundException;
import com.med.aftas.serverside.Model.Hunting;
import com.med.aftas.serverside.Repository.HuntingRepository;
import com.med.aftas.serverside.Service.HuntingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HuntingServiceImpl implements HuntingService {
    @Autowired
    private HuntingRepository huntingRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public HuntingDto save(HuntingDto huntingDto) {
        Hunting hunting = modelMapper.map(huntingDto, Hunting.class);
        return modelMapper.map(huntingRepository.save(hunting), HuntingDto.class);
    }

    @Override
    public void delete(Integer id) {
        huntingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hunting Not found with this: " + id));
        huntingRepository.deleteById(id);
    }

    @Override
    public HuntingDto update(Integer id, HuntingDto huntingDto) {
        Hunting existingHunting = huntingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hunting Not found with this: " + id));
        return modelMapper.map(huntingRepository.save(existingHunting), HuntingDto.class);
    }

    @Override
    public HuntingDto findOne(Integer id) {
        return huntingRepository.findById(id)
                .map(hunting -> modelMapper.map(hunting, HuntingDto.class)).orElseThrow(() -> new ResourceNotFoundException("Hunting Not found with this: " + id));
    }

    @Override
    public List<HuntingDto> findAll() {
        return huntingRepository.findAll().stream().map(hunting -> modelMapper.map(hunting, HuntingDto.class)).collect(Collectors.toList());
    }

    @Override
    public Page<HuntingDto> findWithPagination(Pageable pageable) {
        Page<Hunting> huntingsPage = huntingRepository.findAll(pageable);
        return huntingsPage.map(hunting -> modelMapper.map(hunting, HuntingDto.class));
    }
}
