package com.yihao.quizapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yihao.quizapp.model.QuestionWrapper;
import com.yihao.quizapp.model.Response;
import com.yihao.quizapp.service.QuizService;

@RestController
@RequestMapping("quiz")
public class QuizController {
    @Autowired
    QuizService quizService;
    
    // @RequestParam extracts the parameter value from the URL
    // values are usually after the question mark '?', and the parameter name in the URL must be matched
    // E.g. localhost:8080/create?category=java&numQ=5&title="something"
    @PostMapping(value = "create", params = {"category", "numQ", "title"})
    public ResponseEntity<String> createQuizWithCategory(@RequestParam String category, 
            @RequestParam int numQ, @RequestParam String title) {
        return quizService.create(category, numQ, title);
    }

    // If we want to overload the methods for the same mapping, we need to explicitly specify the requested parameters
    @PostMapping(value = "create", params = {"numQ", "title"})
    public ResponseEntity<String> createQuizWithoutCategory(@RequestParam int numQ, 
            @RequestParam String title) {
        return quizService.create(numQ, title);
    }

    @GetMapping("get")
    public ResponseEntity<List<QuestionWrapper>> getQuiz() {
        return quizService.getQuizQuestions();
    }

    @GetMapping("get-all")
    public ResponseEntity<List<QuestionWrapper>> getAllQuiz() {
        return quizService.getAllQuizQuestions();
    }
    
    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> respnses) {
        return quizService.calculateQuiz(id, respnses);
    }

    
}
