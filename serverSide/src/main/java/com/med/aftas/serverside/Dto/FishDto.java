package com.med.aftas.serverside.Dto;

import lombok.Data;

@Data
public class FishDto {

    private String name;

    private Double averageWeight;

    private LevelDto level;
}
