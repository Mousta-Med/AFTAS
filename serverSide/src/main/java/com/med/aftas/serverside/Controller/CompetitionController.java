package com.med.aftas.serverside.Controller;

import com.med.aftas.serverside.Dto.CompetitionDto;
import com.med.aftas.serverside.Dto.RespDto.CompetitionRespDto;
import com.med.aftas.serverside.Service.Impl.CompetitionServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Validated
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

    @GetMapping("/{id}")
    public ResponseEntity<CompetitionRespDto> findOneCompetition(@PathVariable String id) {
        return ResponseEntity.ok(competitionServiceImpl.findOne(id));
    }

    @PostMapping
    public ResponseEntity<CompetitionRespDto> saveCompetition(@RequestBody @Valid CompetitionDto competitionDto) {
        return ResponseEntity.ok(competitionServiceImpl.save(competitionDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompetitionRespDto> updateCompetition(@PathVariable String id, @RequestBody @Valid CompetitionDto competitionDto) {
        return ResponseEntity.ok(competitionServiceImpl.update(id, competitionDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompetition(@PathVariable(name = "code") String id) {
        competitionServiceImpl.delete(id);
        return ResponseEntity.noContent().build();
    }
}
