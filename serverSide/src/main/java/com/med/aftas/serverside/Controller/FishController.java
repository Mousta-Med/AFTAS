package com.med.aftas.serverside.Controller;

import com.med.aftas.serverside.Dto.FishDto;
import com.med.aftas.serverside.Dto.RespDto.FishRespDto;
import com.med.aftas.serverside.Service.Impl.FishServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = "/api/v1/fish")
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

    @GetMapping("/{id}")
    public ResponseEntity<FishRespDto> findOneFish(@PathVariable String id) {
        return ResponseEntity.ok(fishServiceImpl.findOne(id));
    }

    @PostMapping
    public ResponseEntity<FishRespDto> saveFish(@Valid @RequestBody FishDto fishDto) {
        return ResponseEntity.ok(fishServiceImpl.save(fishDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FishRespDto> updateFish(@PathVariable String id, @Valid @RequestBody FishDto fishDto) {
        return ResponseEntity.ok(fishServiceImpl.update(id, fishDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFish(@PathVariable(name = "id") String id) {
        fishServiceImpl.delete(id);
        return ResponseEntity.noContent().build();
    }
}
