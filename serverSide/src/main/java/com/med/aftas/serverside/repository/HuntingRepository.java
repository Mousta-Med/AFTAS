package com.med.aftas.serverside.repository;

import com.med.aftas.serverside.model.Hunting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HuntingRepository extends JpaRepository<Hunting, Integer> {
    Optional<Hunting> getHuntingByCompetitionCodeAndFishNameAndMemberNum(String competitionCode, String fishName, Integer memberNum);
}
