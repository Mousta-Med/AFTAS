package com.med.aftas.serverside.Controller;

import com.med.aftas.serverside.Dto.CompetitionDto;
import com.med.aftas.serverside.Service.Impl.CompetitionServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = "/api/v1/competition")
public class CompetitionController {

    @Autowired
    private CompetitionServiceImpl competitionServiceImpl;

    @GetMapping
    public ResponseEntity<List<CompetitionDto>> findAllCompetitions() {
        return ResponseEntity.ok(competitionServiceImpl.findAll());
    }

    @GetMapping("/paginated")
    public ResponseEntity<List<CompetitionDto>> getPaginatedCompetitions(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        PageRequest pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(competitionServiceImpl.findWithPagination(pageable).getContent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompetitionDto> findOneCompetition(@PathVariable String id) {
        return ResponseEntity.ok(competitionServiceImpl.findOne(id));
    }

    @PostMapping
    public ResponseEntity<CompetitionDto> saveCompetition(@Valid @RequestBody CompetitionDto competitionDto) {
        return ResponseEntity.ok(competitionServiceImpl.save(competitionDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompetitionDto> updateCompetition(@PathVariable String id, @Valid @RequestBody CompetitionDto competitionDto) {
        return ResponseEntity.ok(competitionServiceImpl.update(id, competitionDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompetition(@PathVariable(name = "id") String id) {
        competitionServiceImpl.delete(id);
        return ResponseEntity.noContent().build();
    }
}
