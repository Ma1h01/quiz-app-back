package com.yihao.quizapp.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.yihao.quizapp.dao.QuestionDao;
import com.yihao.quizapp.dao.QuizDao;
import com.yihao.quizapp.model.Question;
import com.yihao.quizapp.model.QuestionWrapper;
import com.yihao.quizapp.model.Quiz;
import com.yihao.quizapp.model.Response;

@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> create(String category, int numQ, String title) {
        try {
            Quiz quiz = new Quiz();
            quiz.setTitle(title);
            List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numQ);
            quiz.setQuestions(questions);
            
            // JPA will create the respective table if it doesn't exist
            quizDao.save(quiz);
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Failure", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> create(int numQ, String title) {
        try {
            Quiz quiz = new Quiz();
            quiz.setTitle(title);
            quiz.setQuestions(questionDao.findRandomQuestions(numQ));

            quizDao.save(quiz);
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Failure", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        try {
            // Optional type allows you to check whether there is the requested entry in the table
            Optional<Quiz> quiz = quizDao.findById(id);
            List<Question> questions = quiz.get().getQuestions();
            // Need to manually convert Question to QuestionWrapper because we dont want to pass fields like 'rightAnswer' to
            // the client
            List<QuestionWrapper> questionWrappers = new ArrayList<>();
            for (Question q : questions) {
                questionWrappers.add(new QuestionWrapper(quiz.get().getId(), q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4()));
            }
            return new ResponseEntity<>(questionWrappers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<QuestionWrapper>> getAllQuizQuestions() {
        try {
            // Optional type allows you to check whether there is the requested entry in the table
            List<Quiz> quizs = quizDao.findAll();
            List<QuestionWrapper> questionWrappers = new ArrayList<>();
            for (Quiz quiz : quizs) {
                List<Question> questions = quiz.getQuestions();
                for (Question q : questions) {
                    questionWrappers.add(new QuestionWrapper(quiz.getId(), q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4()));
                }
            }
            return new ResponseEntity<>(questionWrappers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }


    // Note, the orders of Question and Response are sorted
    public ResponseEntity<Integer> calculateQuiz(Integer id, List<Response> respnses) {
        try {
            Optional<Quiz> quiz = quizDao.findById(id);
            List<Question> questions = quiz.get().getQuestions();
            questions.sort(new Comparator<Question>() {

                @Override
                public int compare(Question o1, Question o2) {
                    return o1.getId() - o2.getId();
                }
                
            });

            respnses.sort(new Comparator<Response>() {

                @Override
                public int compare(Response o1, Response o2) {
                    return o1.getId() - o2.getId();
                }
                
            });
            int sum = 0;
            int i = 0;
            for (Response response : respnses) {
                if (response.getResponse().equals(questions.get(i).getRightAnswer())) {
                    sum++;
                }
                i++;
            }
            return new ResponseEntity<>(sum, HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(0, HttpStatus.BAD_REQUEST);
    }

    

    
}
