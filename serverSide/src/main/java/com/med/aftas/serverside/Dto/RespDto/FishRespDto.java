package com.med.aftas.serverside.Dto.RespDto;

import com.med.aftas.serverside.Dto.HuntingDto;
import com.med.aftas.serverside.Dto.LevelDto;
import lombok.Data;

import java.util.List;
@Data
public class FishRespDto {

    private String name;

    private Double averageWeight;

    private LevelDto level;

    private List<HuntingDto> huntings;
}
