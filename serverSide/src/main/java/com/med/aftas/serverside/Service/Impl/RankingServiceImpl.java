package com.med.aftas.serverside.Service.Impl;

import com.med.aftas.serverside.Dto.RankingDto;
import com.med.aftas.serverside.Exception.ResourceNotFoundException;
import com.med.aftas.serverside.Model.Ranking;
import com.med.aftas.serverside.Model.RankingId;
import com.med.aftas.serverside.Repository.RankingRepository;
import com.med.aftas.serverside.Service.RankingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RankingServiceImpl implements RankingService {

    @Autowired
    private RankingRepository rankingRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public RankingDto save(RankingDto rankingDto) {
        Ranking ranking = modelMapper.map(rankingDto, Ranking.class);
        return modelMapper.map(rankingRepository.save(ranking), RankingDto.class);
    }

    @Override
    public void delete(RankingId id) {
        rankingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Ranking Not found with this: " + id));
        rankingRepository.deleteById(id);
    }

    @Override
    public RankingDto update(RankingId id, RankingDto rankingDto) {
        Ranking existingRanking = rankingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Ranking Not found with this: " + id));
        return modelMapper.map(rankingRepository.save(existingRanking), RankingDto.class);
    }

    @Override
    public RankingDto findOne(RankingId id) {
        return rankingRepository.findById(id)
                .map(ranking -> modelMapper.map(ranking, RankingDto.class)).orElseThrow(() -> new ResourceNotFoundException("Ranking Not found with this: " + id));
    }

    @Override
    public List<RankingDto> findAll() {
        return rankingRepository.findAll().stream().map(ranking -> modelMapper.map(ranking, RankingDto.class)).collect(Collectors.toList());
    }

    @Override
    public Page<RankingDto> findWithPagination(Pageable pageable) {
        Page<Ranking> rankingsPage = rankingRepository.findAll(pageable);
        return rankingsPage.map(ranking -> modelMapper.map(ranking, RankingDto.class));
    }
}
