package com.med.aftas.serverside.Repository;

import com.med.aftas.serverside.Model.Ranking;
import com.med.aftas.serverside.Model.RankingId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RankingRepository extends JpaRepository<Ranking, RankingId> {
    List<Ranking> findByIdNum(Integer num);

    List<Ranking> findByIdCode(String code);
}
