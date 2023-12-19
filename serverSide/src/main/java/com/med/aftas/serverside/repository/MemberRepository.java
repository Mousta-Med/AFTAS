package com.med.aftas.serverside.repository;

import com.med.aftas.serverside.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    List<Member> findMemberByNameContainingIgnoreCaseOrFamilyNameContainingIgnoreCase(String name, String familyName);
}
