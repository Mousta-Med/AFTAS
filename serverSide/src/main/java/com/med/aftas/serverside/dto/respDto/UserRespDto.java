package com.med.aftas.serverside.dto.respDto;

import com.med.aftas.serverside.dto.HuntingDto;
import com.med.aftas.serverside.dto.RankingDto;
import com.med.aftas.serverside.enums.IdentityDocumentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRespDto {

    private Integer num;

    private String name;

    private String familyName;

    private LocalDate accessionDate;

    private String nationality;

    private IdentityDocumentType identityDocument;

    private String identityNumber;

    private List<RankingDto> rankings;

    private List<HuntingDto> huntings;
}