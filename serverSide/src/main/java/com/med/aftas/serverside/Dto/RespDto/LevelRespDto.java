package com.med.aftas.serverside.Dto.RespDto;

import com.med.aftas.serverside.Dto.FishDto;
import lombok.Data;

import java.util.List;

@Data
public class LevelRespDto {

    private Integer id;

    private String description;

    private Integer points;

    private List<FishDto> fishes;
}
