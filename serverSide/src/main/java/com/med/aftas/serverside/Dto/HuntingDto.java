package com.med.aftas.serverside.Dto;

import lombok.Data;

@Data
public class HuntingDto {

    private Integer id;

    private Integer numberOfFish;

    private FishDto fish;

    private MemberDto member;

    private CompetitionDto competition;
}
