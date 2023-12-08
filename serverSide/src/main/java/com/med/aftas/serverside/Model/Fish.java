package com.med.aftas.serverside.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Fish {

    @Id
    @NotBlank(message = "Fish Name Should not be Empty")
    private String name;

    @NotNull(message = "Fish Average Weight Should not be Null")
    @Positive(message = "Fish average weight should be a positive value")
    private Double averageWeight;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "level_id")
    @NotNull(message = "level id should not be null")
    private Level level;

    @OneToMany(mappedBy = "fish", fetch = FetchType.LAZY)
    private List<Hunting> huntings;
}
