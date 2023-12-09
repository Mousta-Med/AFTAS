package com.med.aftas.serverside.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Competition {

    @Id
    @NotNull(message = "Code Should Not Be Null")
    @Pattern(regexp = "[A-Za-z]{3}-\\d{8}", message = "Code should follow the pattern '3 letters - 8 digits'")
    private String code;

    @NotNull(message = "Date Should Not Be Null")
    private LocalDate date;

    @NotNull(message = "Start Time Should Not Be Null")
    private LocalDateTime startTime;

    @NotNull(message = "End Time Should Not Be Null")
    private LocalDateTime endTime;

    @Positive(message = "number Of Participants should be a positive value")
    private Integer numberOfParticipants;

    @NotBlank(message = "Location Should Not Be Empty")
    private String location;

    @NotNull(message = "Amount Should Not Be Null")
    private Double amount;

    @OneToMany(mappedBy = "competition", fetch = FetchType.LAZY)
    private List<Ranking> rankings;

    @OneToMany(mappedBy = "competition", fetch = FetchType.LAZY)
    private List<Hunting> huntings;
}
