//package com.springboot.youquiz.Service.Impl;
//
//import com.springboot.youquiz.Model.Answer;
//import com.springboot.youquiz.Model.AssignQuiz;
//import com.springboot.youquiz.Model.Validation;
//import com.springboot.youquiz.Repository.AssignQuizRepository;
//import com.springboot.youquiz.Repository.ValidationRepository;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//import org.springframework.web.server.ResponseStatusException;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class AnswerServiceImpl implements BaseService<AnswerDto, Long, AnswerDto> {
//
//    @Autowired
//    private AnswerRepository answerRepository;
//    @Autowired
//    private ValidationRepository validationRepository;
//    @Autowired
//    private AssignQuizRepository assignQuizRepository;
//
//    @Autowired
//    private ModelMapper modelMapper;
//
//
//    @Override
//    public AnswerDto save(AnswerDto answerDto) {
//        Answer answer = modelMapper.map(answerDto, Answer.class);
//        Validation validation = validationRepository.findById(answerDto.getValidationId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Validation not found with"));
//        AssignQuiz assignQuiz = assignQuizRepository.findById(answerDto.getAssignQuizId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "AssignQuiz not found with"));
//        answer.setAssignQuiz(assignQuiz);
//        answer.setValidation(validation);
//        return modelMapper.map(answerRepository.save(answer), AnswerDto.class);
//    }
//
//    @Override
//    public void delete(Long id) {
//        answerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Answer not found with id " + id));
//        answerRepository.deleteById(id);
//    }
//
//    @Override
//    public AnswerDto update(Long id, AnswerDto answerDto) {
//        Answer existingAnswer = answerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Answer not found with id " + id));
//        Validation validation = validationRepository.findById(answerDto.getValidationId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Validation not found with"));
//        AssignQuiz assignQuiz = assignQuizRepository.findById(answerDto.getAssignQuizId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "AssignQuiz not found with"));
//        existingAnswer.setAssignQuiz(assignQuiz);
//        existingAnswer.setValidation(validation);
//        existingAnswer.setPlayed(answerDto.getPlayed());
//        return modelMapper.map(answerRepository.save(existingAnswer), AnswerDto.class);
//    }
//
//    @Override
//    public AnswerDto findOne(Long id) {
//        return answerRepository.findById(id)
//                .map(answer -> modelMapper.map(answer, AnswerDto.class)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Answer not found with id " + id));
//    }
//
//    @Override
//    public List<AnswerDto> findAll() {
//        return answerRepository.findAll().stream().map(answer -> modelMapper.map(answer, AnswerDto.class)).collect(Collectors.toList());
//    }
//
//    @Override
//    public Page<AnswerDto> findWithPagination(Pageable pageable) {
//        Page<Answer> answersPage = answerRepository.findAll(pageable);
//        return answersPage.map(answer -> modelMapper.map(answer, AnswerDto.class));
//    }
//}
