package com.med.aftas.serverside.repository;

import com.med.aftas.serverside.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    List<Member> findMemberByNameOrFamilyName(String name, String familyName);
}
