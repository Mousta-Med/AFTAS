package com.med.aftas.serverside.Dto;

import com.med.aftas.serverside.Enum.IdentityDocumentType;
import lombok.Data;

import java.time.LocalDate;
@Data
public class MemberDto {

    private Integer id;

    private String name;

    private String familyName;

    private LocalDate accessionDate;

    private String nationality;

    private IdentityDocumentType identityDocument;
}
