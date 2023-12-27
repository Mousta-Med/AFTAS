package com.med.aftas.serverside.controller;

import com.med.aftas.serverside.dto.HuntingDto;
import com.med.aftas.serverside.dto.respDto.HuntingRespDto;
import com.med.aftas.serverside.service.HuntingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hunting")
public class HuntingController {

    @Autowired
    private HuntingService huntingService;

    @GetMapping
    public ResponseEntity<List<HuntingRespDto>> findAllHuntings() {
        return ResponseEntity.ok(huntingService.findAll());
    }

    @GetMapping("/paginated")
    public ResponseEntity<List<HuntingRespDto>> getPaginatedHuntings(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        PageRequest pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(huntingService.findWithPagination(pageable).getContent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HuntingRespDto> findOneHunting(@PathVariable Integer id) {
        return ResponseEntity.ok(huntingService.findOne(id));
    }

    @GetMapping("/{competitionCode}/{memberNum}")
    public ResponseEntity<List<HuntingRespDto>> getHuntingsByCompetitionCodeAndMemberNum(@PathVariable String competitionCode, @PathVariable Integer memberNum) {
        return ResponseEntity.ok(huntingService.getHuntingsByCompetitionCodeAndMemberNum(competitionCode, memberNum));
    }

    @PostMapping
    public ResponseEntity<HuntingRespDto> saveHunting(@RequestBody @Valid HuntingDto huntingDto) {
        return ResponseEntity.ok(huntingService.save(huntingDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HuntingRespDto> updateHunting(@PathVariable Integer id, @RequestBody @Valid HuntingDto huntingDto) {
        return ResponseEntity.ok(huntingService.update(id, huntingDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHunting(@PathVariable(name = "id") Integer id) {
        huntingService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
