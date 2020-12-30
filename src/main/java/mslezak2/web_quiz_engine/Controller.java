package mslezak2.web_quiz_engine;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;

/** Controller that handles clients requests such as fetching the question and submitting the answer */
@RestController
public class Controller {

    ArrayList<Question> questions = new ArrayList<>(); // it's going to be replaced with the database in the future
    {
        questions.add(new Question("q1", "t1", new String[]{"a1","a2","a3","a4"}, new int[]{2,3})); // initializing list for testing purposes
    }

    @GetMapping("/api/quizzes/{id}")
    private Question getQuestion(@PathVariable int id) {
        if (id < questions.size()) {
            return questions.get(id);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "(not found)"
            );
        }
    }
    
    @GetMapping("/api/quizzes")
    private ArrayList<Question> getAllQuestions() {
        return questions;
    }

    /**POST request should contain single int value representing chosen answer inside its body*/
    @PostMapping("/api/quizzes/{id}/solve")
    private AnswerFeedback postAnswer(@PathVariable int id, @RequestBody int[] answer) {
        if (id < questions.size()) {
        
//            if (Arrays.equals(answer,questions.get(id).getAnswer()) ) {
            if (Arrays.equals(answer,questions.get(id).getAnswer())) {
                return AnswerFeedback.POSITIVE_FEEDBACK;
            } else {
                return AnswerFeedback.NEGATIVE_FEEDBACK;
            }
        
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "(not found)"
            );
        }
    }
    
    /**Method lets upload new questions defined by the user*/
    @PostMapping("/api/quizzes")
    private Question createQuestion(@Valid @RequestBody Question question) {
        //TODO: Is there any more elegant way to handle that id setting?
        question.setId(questions.size()); //every question gets its unique id
        questions.add(question);
        return questions.get(questions.size() - 1);
    }
}
