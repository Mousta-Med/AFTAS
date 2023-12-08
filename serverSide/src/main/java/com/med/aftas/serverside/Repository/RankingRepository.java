package com.med.aftas.serverside.Repository;

import com.med.aftas.serverside.Model.Ranking;
import com.med.aftas.serverside.Util.RankingId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RankingRepository extends JpaRepository<Ranking, RankingId> {
    List<Ranking> findByIdNum(Integer num);

    List<Ranking> findByIdCode(String code);
}
