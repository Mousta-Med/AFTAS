package com.med.aftas.serverside.Dto;

import com.med.aftas.serverside.Model.RankingId;
import lombok.Data;

@Data
public class RankingDto {

    private RankingId id;

    private Integer rank;

    private Integer score;

    private Integer member_id;

    private String competition_code;
}
