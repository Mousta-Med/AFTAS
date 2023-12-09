package com.med.aftas.serverside.Service.Impl;

import com.med.aftas.serverside.Dto.CompetitionDto;
import com.med.aftas.serverside.Service.CompetitionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CompetitionServiceImpl implements CompetitionService {
    @Override
    public CompetitionDto save(CompetitionDto competitionDto) {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public CompetitionDto update(String id, CompetitionDto competitionDto) {
        return null;
    }

    @Override
    public CompetitionDto findOne(String id) {
        return null;
    }

    @Override
    public List<CompetitionDto> findAll() {
        return null;
    }

    @Override
    public Page<CompetitionDto> findWithPagination(Pageable pageable) {
        return null;
    }
}
