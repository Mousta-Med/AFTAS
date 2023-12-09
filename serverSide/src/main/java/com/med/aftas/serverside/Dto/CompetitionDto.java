package com.med.aftas.serverside.Dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class CompetitionDto {

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
