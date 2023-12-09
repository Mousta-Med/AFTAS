package com.med.aftas.serverside.Service.Impl;

import com.med.aftas.serverside.Dto.LevelDto;
import com.med.aftas.serverside.Service.LevelService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LevelServiceImpl implements LevelService {
    @Override
    public LevelDto save(LevelDto levelDto) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public LevelDto update(Integer id, LevelDto levelDto) {
        return null;
    }

    @Override
    public LevelDto findOne(Integer id) {
        return null;
    }

    @Override
    public List<LevelDto> findAll() {
        return null;
    }

    @Override
    public Page<LevelDto> findWithPagination(Pageable pageable) {
        return null;
    }
}
