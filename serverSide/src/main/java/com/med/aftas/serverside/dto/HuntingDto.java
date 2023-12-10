package com.med.aftas.serverside.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HuntingDto {

    private Integer id;

    @PositiveOrZero(message = "number Of Fish should not be negative")
    private Integer numberOfFish;

    @NotNull(message = "fish should not be null")
    private FishDto fish;

    @NotNull(message = "member should not be null")
    private MemberDto member;

    @NotNull(message = "competition should not be null")
    private CompetitionDto competition;
}
