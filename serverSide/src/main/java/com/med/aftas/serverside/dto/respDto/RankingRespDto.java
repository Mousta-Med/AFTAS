package com.med.aftas.serverside.dto.respDto;

import com.med.aftas.serverside.dto.CompetitionDto;
import com.med.aftas.serverside.dto.MemberDto;
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
public class RankingRespDto {

    private Integer rank;

    private Integer score;

    private MemberDto member;

    private CompetitionDto competition;
}
