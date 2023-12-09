package com.med.aftas.serverside.Dto.RespDto;

import com.med.aftas.serverside.Dto.HuntingDto;
import com.med.aftas.serverside.Dto.RankingDto;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class CompetitionRespDto {

    private String code;

    private LocalDate date;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Integer numberOfParticipants;

    private String location;

    private Double amount;

    private List<RankingDto> rankings;

    private List<HuntingDto> huntings;
}
