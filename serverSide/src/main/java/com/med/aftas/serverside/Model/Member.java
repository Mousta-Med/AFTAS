package com.med.aftas.serverside.Model;

import com.med.aftas.serverside.Enum.IdentityDocumentType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "name should be not Empty")
    private String name;

    @NotBlank(message = "family Name should be not Empty")
    private String familyName;

    @NotNull(message = "accession Date should not be null")
    private LocalDate accessionDate;

    @NotBlank(message = "nationality should be not Empty")
    private String nationality;

    @NotEmpty(message = "Identity Document Type should not be empty")
    @Enumerated(EnumType.STRING)
    private IdentityDocumentType identityDocument;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Ranking> rankings;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Hunting> huntings;
}
