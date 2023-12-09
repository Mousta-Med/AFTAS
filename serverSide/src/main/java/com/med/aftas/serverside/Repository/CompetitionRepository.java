package com.med.aftas.serverside.Repository;

import com.med.aftas.serverside.Model.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, String> {
}
