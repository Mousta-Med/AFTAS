package com.med.aftas.serverside.Repository;

import com.med.aftas.serverside.Model.Hunting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HuntingRepository extends JpaRepository<Hunting, Integer> {
}
