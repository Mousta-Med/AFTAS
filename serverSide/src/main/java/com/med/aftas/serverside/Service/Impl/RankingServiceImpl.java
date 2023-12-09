package com.med.aftas.serverside.Service.Impl;

import com.med.aftas.serverside.Dto.RankingDto;
import com.med.aftas.serverside.Service.RankingService;
import com.med.aftas.serverside.Model.RankingId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RankingServiceImpl implements RankingService {
    @Override
    public RankingDto save(RankingDto rankingDto) {
        return null;
    }

    @Override
    public void delete(RankingId id) {

    }

    @Override
    public RankingDto update(RankingId id, RankingDto rankingDto) {
        return null;
    }

    @Override
    public RankingDto findOne(RankingId id) {
        return null;
    }

    @Override
    public List<RankingDto> findAll() {
        return null;
    }

    @Override
    public Page<RankingDto> findWithPagination(Pageable pageable) {
        return null;
    }
}
