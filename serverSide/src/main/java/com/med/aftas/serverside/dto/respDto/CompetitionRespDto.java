package com.med.aftas.serverside.dto.respDto;

import com.med.aftas.serverside.dto.HuntingDto;
import com.med.aftas.serverside.dto.RankingDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
