package com.med.aftas.serverside.controller;

import com.med.aftas.serverside.dto.FishDto;
import com.med.aftas.serverside.dto.respDto.FishRespDto;
import com.med.aftas.serverside.service.impl.FishServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fish")
public class FishController {

    @Autowired
    private FishServiceImpl fishServiceImpl;

    @GetMapping
    public ResponseEntity<List<FishRespDto>> findAllFishs() {
        return ResponseEntity.ok(fishServiceImpl.findAll());
    }

    @GetMapping("/paginated")
    public ResponseEntity<List<FishRespDto>> getPaginatedFishs(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        PageRequest pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(fishServiceImpl.findWithPagination(pageable).getContent());
    }

    @GetMapping("/{name}")
    public ResponseEntity<FishRespDto> findOneFish(@PathVariable String name) {
        return ResponseEntity.ok(fishServiceImpl.findOne(name));
    }

    @PostMapping
    public ResponseEntity<FishRespDto> saveFish(@RequestBody @Valid FishDto fishDto) {
        return ResponseEntity.ok(fishServiceImpl.save(fishDto));
    }

    @PutMapping("/{name}")
    public ResponseEntity<FishRespDto> updateFish(@PathVariable String name, @RequestBody @Valid FishDto fishDto) {
        return ResponseEntity.ok(fishServiceImpl.update(name, fishDto));
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteFish(@PathVariable(name = "name") String name) {
        fishServiceImpl.delete(name);
        return ResponseEntity.noContent().build();
    }
}
