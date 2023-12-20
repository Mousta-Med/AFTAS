package com.med.aftas.serverside.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Ranking {

    @EmbeddedId
    private RankingId id;

    private Integer rank;

    private Integer score;

    @ManyToOne
    @MapsId("memberNum")
    @JoinColumn(name = "member_num")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member member;

    @ManyToOne
    @MapsId("competitionCode")
    @JoinColumn(name = "competition_code")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Competition competition;
}
