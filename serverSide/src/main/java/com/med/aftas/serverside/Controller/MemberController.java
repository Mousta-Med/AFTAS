package com.med.aftas.serverside.Controller;

import com.med.aftas.serverside.Dto.MemberDto;
import com.med.aftas.serverside.Service.Impl.MemberServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = "/api/v1/member")
public class MemberController {

    @Autowired
    private MemberServiceImpl memberServiceImpl;

    @GetMapping
    public ResponseEntity<List<MemberDto>> findAllMembers() {
        return ResponseEntity.ok(memberServiceImpl.findAll());
    }

    @GetMapping("/paginated")
    public ResponseEntity<List<MemberDto>> getPaginatedMembers(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        PageRequest pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(memberServiceImpl.findWithPagination(pageable).getContent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberDto> findOneMember(@PathVariable Integer id) {
        return ResponseEntity.ok(memberServiceImpl.findOne(id));
    }

    @PostMapping
    public ResponseEntity<MemberDto> saveMember(@Valid @RequestBody MemberDto memberDto) {
        return ResponseEntity.ok(memberServiceImpl.save(memberDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemberDto> updateMember(@PathVariable Integer id, @Valid @RequestBody MemberDto memberDto) {
        return ResponseEntity.ok(memberServiceImpl.update(id, memberDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable(name = "id") Integer id) {
        memberServiceImpl.delete(id);
        return ResponseEntity.noContent().build();
    }
}
