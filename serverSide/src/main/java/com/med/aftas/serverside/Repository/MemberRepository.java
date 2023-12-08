package com.med.aftas.serverside.Repository;

import com.med.aftas.serverside.Model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
}
