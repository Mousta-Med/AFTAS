package com.med.aftas.serverside.controller;

import com.med.aftas.serverside.dto.MemberDto;
import com.med.aftas.serverside.dto.respDto.MemberRespDto;
import com.med.aftas.serverside.service.impl.MemberServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/member")
public class MemberController {

    @Autowired
    private MemberServiceImpl memberServiceImpl;

    @GetMapping
    public ResponseEntity<List<MemberRespDto>> findAllMembers() {
        return ResponseEntity.ok(memberServiceImpl.findAll());
    }

    @GetMapping("/paginated")
    public ResponseEntity<List<MemberRespDto>> getPaginatedMembers(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        PageRequest pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(memberServiceImpl.findWithPagination(pageable).getContent());
    }

    @GetMapping("/{num}")
    public ResponseEntity<MemberRespDto> findOneMember(@PathVariable Integer num) {
        return ResponseEntity.ok(memberServiceImpl.findOne(num));
    }

    @GetMapping("/search/{query}")
    public ResponseEntity<List<MemberRespDto>> findMemberByNameOrFamilyName(@PathVariable String query) {
        return ResponseEntity.ok(memberServiceImpl.findByNameOrFamilyName(query));
    }

    @PostMapping
    public ResponseEntity<MemberRespDto> saveMember(@RequestBody @Valid MemberDto memberDto) {
        return ResponseEntity.ok(memberServiceImpl.save(memberDto));
    }

    @PutMapping("/{num}")
    public ResponseEntity<MemberRespDto> updateMember(@PathVariable Integer num, @RequestBody @Valid MemberDto memberDto) {
        return ResponseEntity.ok(memberServiceImpl.update(num, memberDto));
    }

    @DeleteMapping("/{num}")
    public ResponseEntity<Void> deleteMember(@PathVariable(name = "num") Integer num) {
        memberServiceImpl.delete(num);
        return ResponseEntity.noContent().build();
    }
}
