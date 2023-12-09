package com.med.aftas.serverside.Service.Impl;

import com.med.aftas.serverside.Dto.HuntingDto;
import com.med.aftas.serverside.Service.HuntingService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HuntingServiceImpl implements HuntingService {
    @Override
    public HuntingDto save(HuntingDto huntingDto) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public HuntingDto update(Integer id, HuntingDto huntingDto) {
        return null;
    }

    @Override
    public HuntingDto findOne(Integer id) {
        return null;
    }

    @Override
    public List<HuntingDto> findAll() {
        return null;
    }

    @Override
    public Page<HuntingDto> findWithPagination(Pageable pageable) {
        return null;
    }
}
