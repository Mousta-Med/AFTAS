package com.med.aftas.serverside.controller;

import com.med.aftas.serverside.dto.CompetitionDto;
import com.med.aftas.serverside.dto.respDto.CompetitionRespDto;
import com.med.aftas.serverside.service.impl.CompetitionServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/competition")
public class CompetitionController {

    @Autowired
    private CompetitionServiceImpl competitionServiceImpl;

    @GetMapping
    public ResponseEntity<List<CompetitionRespDto>> findAllCompetitions() {
        return ResponseEntity.ok(competitionServiceImpl.findAll());
    }

    @GetMapping("/paginated")
    public ResponseEntity<List<CompetitionRespDto>> getPaginatedCompetitions(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        PageRequest pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(competitionServiceImpl.findWithPagination(pageable).getContent());
    }

    @GetMapping("/{code}")
    public ResponseEntity<CompetitionRespDto> findOneCompetition(@PathVariable String code) {
        return ResponseEntity.ok(competitionServiceImpl.findOne(code));
    }

    @PostMapping
    public ResponseEntity<CompetitionRespDto> saveCompetition(@RequestBody @Valid CompetitionDto competitionDto) {
        return ResponseEntity.ok(competitionServiceImpl.save(competitionDto));
    }

    @PutMapping("/{code}")
    public ResponseEntity<CompetitionRespDto> updateCompetition(@PathVariable String code, @RequestBody @Valid CompetitionDto competitionDto) {
        return ResponseEntity.ok(competitionServiceImpl.update(code, competitionDto));
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteCompetition(@PathVariable(name = "code") String code) {
        competitionServiceImpl.delete(code);
        return ResponseEntity.noContent().build();
    }
}
