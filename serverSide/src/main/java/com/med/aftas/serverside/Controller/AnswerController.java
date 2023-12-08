package com.springboot.youquiz.Controller;

import com.springboot.youquiz.Service.Impl.AnswerServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/answer")
public class AnswerController {


    @Autowired
    private AnswerServiceImpl answerServiceImpl;

    @GetMapping
    public ResponseEntity<List<AnswerDto>> findAllAnswers() {
        return ResponseEntity.ok(answerServiceImpl.findAll());
    }

    @GetMapping("/paginated")
    public ResponseEntity<List<AnswerDto>> getPaginatedAnswers(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        PageRequest pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(answerServiceImpl.findWithPagination(pageable).getContent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnswerDto> findOneAnswer(@PathVariable Long id) {
        return ResponseEntity.ok(answerServiceImpl.findOne(id));
    }

    @PostMapping
    public ResponseEntity<AnswerDto> saveAnswer(@Valid @RequestBody AnswerDto answerDto) {
        return ResponseEntity.ok(answerServiceImpl.save(answerDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnswerDto> updateAnswer(@PathVariable long id, @Valid @RequestBody AnswerDto answerDto) {
        return ResponseEntity.ok(answerServiceImpl.update(id, answerDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnswer(@PathVariable(name = "id") Long id) {
        answerServiceImpl.delete(id);
        return ResponseEntity.noContent().build();
    }

}
