package com.med.aftas.serverside.dto;

import com.med.aftas.serverside.model.RankingId;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RankingDto {

    private RankingId id;

    @NotNull(message = "Rank should not be null")
    @Min(value = 1, message = "Rank should be at least 1")
    private Integer rank;

    @NotNull(message = "Score should not be null")
    @PositiveOrZero(message = "Score should not be negative")
    private Integer score;

    @NotNull(message = "member should not be null")
    private Integer memberNum;

    @NotNull(message = "competition should not be null")
    private String competitionCode;
}
