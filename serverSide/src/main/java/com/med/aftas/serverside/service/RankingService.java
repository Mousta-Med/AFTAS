package com.med.aftas.serverside.service;

import com.med.aftas.serverside.dto.RankingDto;
import com.med.aftas.serverside.model.RankingId;

import java.util.List;

public interface RankingService extends BaseService<RankingDto, RankingId, RankingDto> {
    List<RankingDto> SetUpCompetitionRankings(String competitionCode);
}
