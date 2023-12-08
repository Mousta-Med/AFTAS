package com.med.aftas.serverside.Repository;

import com.med.aftas.serverside.Model.Fish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FishRepository extends JpaRepository<Fish, String> {
}
