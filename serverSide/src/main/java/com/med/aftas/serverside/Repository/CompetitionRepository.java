package com.med.aftas.serverside.Repository;

import com.med.aftas.serverside.Model.Competition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitionRepository extends JpaRepository<Competition, String> {
}
