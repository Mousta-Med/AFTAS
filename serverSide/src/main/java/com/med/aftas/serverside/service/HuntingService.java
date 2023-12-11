package com.med.aftas.serverside.service;

import com.med.aftas.serverside.dto.HuntingDto;

import java.util.List;

public interface HuntingService extends BaseService<HuntingDto, Integer, HuntingDto> {
    List<HuntingDto> getHuntingsByCompetitionCodeAndMemberNum(String code, Integer num);
}
