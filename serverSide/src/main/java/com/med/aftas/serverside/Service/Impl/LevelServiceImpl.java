package com.med.aftas.serverside.Service.Impl;

import com.med.aftas.serverside.Dto.LevelDto;
import com.med.aftas.serverside.Exception.ResourceNotFoundException;
import com.med.aftas.serverside.Model.Level;
import com.med.aftas.serverside.Repository.LevelRepository;
import com.med.aftas.serverside.Service.LevelService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LevelServiceImpl implements LevelService {
    @Autowired
    private LevelRepository levelRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public LevelDto save(LevelDto levelDto) {
        Level level = modelMapper.map(levelDto, Level.class);
        return modelMapper.map(levelRepository.save(level), LevelDto.class);
    }

    @Override
    public void delete(Integer id) {
        levelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Level Not found with this: " + id));
        levelRepository.deleteById(id);
    }

    @Override
    public LevelDto update(Integer id, LevelDto levelDto) {
        Level existingLevel = levelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Level Not found with this: " + id));
        return modelMapper.map(levelRepository.save(existingLevel), LevelDto.class);
    }

    @Override
    public LevelDto findOne(Integer id) {
        return levelRepository.findById(id)
                .map(level -> modelMapper.map(level, LevelDto.class)).orElseThrow(() -> new ResourceNotFoundException("Level Not found with this: " + id));
    }

    @Override
    public List<LevelDto> findAll() {
        return levelRepository.findAll().stream().map(level -> modelMapper.map(level, LevelDto.class)).collect(Collectors.toList());
    }

    @Override
    public Page<LevelDto> findWithPagination(Pageable pageable) {
        Page<Level> levelsPage = levelRepository.findAll(pageable);
        return levelsPage.map(level -> modelMapper.map(level, LevelDto.class));
    }
}
