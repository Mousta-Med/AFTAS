package com.med.aftas.serverside.dto;

import com.med.aftas.serverside.enums.IdentityDocumentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {

    private Integer num;

    @NotBlank(message = "name should be not Empty")
    private String name;

    @NotBlank(message = "family Name should be not Empty")
    private String familyName;

    @NotNull(message = "accession Date should not be null")
    private LocalDate accessionDate;

    @NotBlank(message = "nationality should be not Empty")
    private String nationality;

    private IdentityDocumentType identityDocument;

    @NotEmpty(message = "Identity Number should not be empty")
    private String identityNumber;
}
