package com.med.aftas.serverside.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class RankingId implements Serializable {

    @Column(name = "member_num")
    private Integer memberNum;

    @Column(name = "competition_code")
    private String competitionCode;

}
