package com.med.aftas.serverside.service.impl;

import com.med.aftas.serverside.dto.LevelDto;
import com.med.aftas.serverside.exception.PointsValidationException;
import com.med.aftas.serverside.exception.ResourceNotFoundException;
import com.med.aftas.serverside.model.Level;
import com.med.aftas.serverside.repository.LevelRepository;
import com.med.aftas.serverside.service.LevelService;
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
        Level lastLevel = levelRepository.findLast();
        if (lastLevel != null){
            if (lastLevel.getPoints() > levelDto.getPoints()){
                throw new PointsValidationException("A level with a lower points cannot be inserted");
            }
        }
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
