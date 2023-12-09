package com.med.aftas.serverside.Service.Impl;

import com.med.aftas.serverside.Dto.FishDto;
import com.med.aftas.serverside.Dto.RespDto.FishRespDto;
import com.med.aftas.serverside.Service.FishService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FishServiceImpl implements FishService {
    @Override
    public FishRespDto save(FishDto fishDto) {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public FishRespDto update(String id, FishDto fishDto) {
        return null;
    }

    @Override
    public FishRespDto findOne(String id) {
        return null;
    }

    @Override
    public List<FishRespDto> findAll() {
        return null;
    }

    @Override
    public Page<FishRespDto> findWithPagination(Pageable pageable) {
        return null;
    }
}
