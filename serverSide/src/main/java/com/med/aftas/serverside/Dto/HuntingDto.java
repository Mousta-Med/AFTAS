package com.med.aftas.serverside.Dto;

import lombok.Data;

@Data
public class HuntingDto {

    private Integer id;

    private Integer numberOfFish;

    private String fish_name;

    private Integer member_id;

    private String competition_code;
}
