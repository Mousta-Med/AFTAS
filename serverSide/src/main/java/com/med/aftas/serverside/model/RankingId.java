package com.med.aftas.serverside.model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class RankingId implements Serializable {
    private String code;
    private Integer num;
}
