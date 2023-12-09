package com.med.aftas.serverside.Repository;

import com.med.aftas.serverside.Model.Fish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FishRepository extends JpaRepository<Fish, String> {
}
