package com.med.aftas.serverside.Controller;

import com.med.aftas.serverside.Dto.LevelDto;
import com.med.aftas.serverside.Service.Impl.LevelServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Validated
@RestController
@RequestMapping("/api/v1/level")
public class LevelController {

    @Autowired
    private LevelServiceImpl levelServiceImpl;

    @GetMapping
    public ResponseEntity<List<LevelDto>> findAllLevels() {
        return ResponseEntity.ok(levelServiceImpl.findAll());
    }

    @GetMapping("/paginated")
    public ResponseEntity<List<LevelDto>> getPaginatedLevels(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        PageRequest pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(levelServiceImpl.findWithPagination(pageable).getContent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LevelDto> findOneLevel(@PathVariable Integer id) {
        return ResponseEntity.ok(levelServiceImpl.findOne(id));
    }

    @PostMapping
    public ResponseEntity<LevelDto> saveLevel(@RequestBody @Valid LevelDto levelDto) {
        return ResponseEntity.ok(levelServiceImpl.save(levelDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LevelDto> updateLevel(@PathVariable Integer id, @RequestBody @Valid LevelDto levelDto) {
        return ResponseEntity.ok(levelServiceImpl.update(id, levelDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLevel(@PathVariable(name = "id") Integer id) {
        levelServiceImpl.delete(id);
        return ResponseEntity.noContent().build();
    }
}
