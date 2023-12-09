package com.med.aftas.serverside.Dto;

import com.med.aftas.serverside.Model.RankingId;
import lombok.Data;

@Data
public class RankingDto {

    private RankingId id;

    private Integer rank;

    private Integer score;

    private MemberDto member;

    private CompetitionDto competition;
}
