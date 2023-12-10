package com.med.aftas.serverside.service;

import com.med.aftas.serverside.dto.MemberDto;
import com.med.aftas.serverside.dto.respDto.MemberRespDto;

import java.util.List;

public interface MemberService extends BaseService<MemberDto, Integer, MemberRespDto> {
    List<MemberRespDto> findByNameOrFamilyName(String query);
}
