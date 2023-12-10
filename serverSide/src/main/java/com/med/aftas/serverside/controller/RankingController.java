package com.med.aftas.serverside.controller;

import com.med.aftas.serverside.dto.RankingDto;
import com.med.aftas.serverside.model.RankingId;
import com.med.aftas.serverside.service.impl.RankingServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ranking")
public class RankingController {

    @Autowired
    private RankingServiceImpl rankingServiceImpl;

    @GetMapping
    public ResponseEntity<List<RankingDto>> findAllRankings() {
        return ResponseEntity.ok(rankingServiceImpl.findAll());
    }

    @GetMapping("/paginated")
    public ResponseEntity<List<RankingDto>> getPaginatedRankings(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        PageRequest pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(rankingServiceImpl.findWithPagination(pageable).getContent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RankingDto> findOneRanking(@PathVariable RankingId id) {
        return ResponseEntity.ok(rankingServiceImpl.findOne(id));
    }

    @PostMapping
    public ResponseEntity<RankingDto> saveRanking(@RequestBody @Valid RankingDto rankingDto) {
        return ResponseEntity.ok(rankingServiceImpl.save(rankingDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RankingDto> updateRanking(@PathVariable RankingId id, @RequestBody @Valid RankingDto rankingDto) {
        return ResponseEntity.ok(rankingServiceImpl.update(id, rankingDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRanking(@PathVariable(name = "id") RankingId id) {
        rankingServiceImpl.delete(id);
        return ResponseEntity.noContent().build();
    }
}
