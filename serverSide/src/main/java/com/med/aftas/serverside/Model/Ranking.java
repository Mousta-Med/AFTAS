package com.med.aftas.serverside.Model;

import com.med.aftas.serverside.Util.RankingId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Ranking {

    @EmbeddedId
    private RankingId id;

    @NotNull(message = "Rank should not be null")
    @Min(value = 1, message = "Rank should be at least 1")
    private Integer rank;

    @NotNull(message = "Score should not be null")
    @PositiveOrZero(message = "Score should not be negative")
    private Integer score;

    @ManyToOne
    @JoinColumn(name = "member_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull(message = "member id should not be null")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "competition_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull(message = "competition id should not be null")
    private Competition competition;
}
