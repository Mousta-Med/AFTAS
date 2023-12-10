package com.med.aftas.serverside.service.impl;

import com.med.aftas.serverside.dto.RankingDto;
import com.med.aftas.serverside.exception.CompetitionDateValidationException;
import com.med.aftas.serverside.exception.ResourceNotFoundException;
import com.med.aftas.serverside.model.Ranking;
import com.med.aftas.serverside.model.RankingId;
import com.med.aftas.serverside.repository.RankingRepository;
import com.med.aftas.serverside.service.RankingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime registrationEndTime = rankingDto.getCompetition().getStartTime().minusHours(24);
        if (currentTime.isAfter(registrationEndTime) && !currentTime.isAfter(rankingDto.getCompetition().getStartTime())) {
            throw new CompetitionDateValidationException("Registration for competitions is allowed from the announcement until 24 hours before the start.");
        }
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
        BeanUtils.copyProperties(rankingDto, existingRanking);
        existingRanking.setId(id);
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
