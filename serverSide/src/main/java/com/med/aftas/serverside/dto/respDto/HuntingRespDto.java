package com.med.aftas.serverside.dto.respDto;

import com.med.aftas.serverside.dto.CompetitionDto;
import com.med.aftas.serverside.dto.FishDto;
import com.med.aftas.serverside.dto.MemberDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HuntingRespDto {

    private Integer id;

    private Integer numberOfFish;

    private FishDto fish;

    private MemberDto member;

    private CompetitionDto competition;
}
