package com.med.aftas.serverside.Repository;

import com.med.aftas.serverside.Model.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelRepository extends JpaRepository<Level, Integer> {
}
