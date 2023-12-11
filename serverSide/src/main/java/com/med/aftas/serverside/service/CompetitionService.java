package com.med.aftas.serverside.service;

import com.med.aftas.serverside.dto.CompetitionDto;
import com.med.aftas.serverside.dto.respDto.CompetitionRespDto;

import java.time.LocalDate;
import java.util.List;

public interface CompetitionService extends BaseService<CompetitionDto, String, CompetitionRespDto> {
    List<CompetitionRespDto> findCompetitionsWithDate(LocalDate date);
}
