package mslezak2.web_quiz_engine;

import mslezak2.web_quiz_engine.data.AnswerFeedback;
import mslezak2.web_quiz_engine.data.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.*;

/** Controller that handles clients requests such as fetching the question and submitting the answer */
@RestController
public class Controller {
    
    //TODO: Why is it not recommended? How to do that properly?
    @Autowired
    QuestionRepository questionRepository;

    
    ArrayList<Question> questions = new ArrayList<>(); // it's going to be replaced with the database in the future
//    {
//        questions.add(new Question("q1", "t1", new String[]{"a1","a2","a3","a4"}, new int[]{2,3})); // initializing list for testing purposes
//    }

    @GetMapping("/api/quizzes/{id}")
    private Question getQuestion(@PathVariable long id) {
        
        //TODO: Handle incorrect id (NoSuchElementException)
        return questionRepository.findById(id).orElseThrow();
        
    }
    
    @GetMapping("/api/quizzes")
    private ArrayList<Question> getAllQuestions() {
        
        return (ArrayList<Question>) questionRepository.findAll();
        
    }
    
    //TODO: Implement mechanism to convert answer from int[] to String (and vice versa)
    /**
     * POST request should contain JSON object inside its body with single property named "answer" with int
     * as a value representing chosen answer
     */
    @PostMapping("/api/quizzes/{id}/solve") //TODO: Is that HashMap as type of RequestBody OK, or is there any better way to do this?
    private AnswerFeedback postAnswer(@PathVariable long id, @RequestBody HashMap<String, Set<Integer>> answer) {
        
        AnswerFeedback result = AnswerFeedback.NEGATIVE_FEEDBACK;
    
        //fetching the question by provided id and its correct answer field
        Question question = questionRepository.findById(id).orElseThrow();
        
        //TODO: Handle wrong id with suitable exception (IndexOutOfBoundsException / NoSuchElementException)
        Set<Integer> correctAnswer = question.getAnswer();

        //fetching posted answer and checking if it's correct
        Set<Integer> postedAnswer = answer.get("answer");
        //TODO: Handle wrong format of the request
        if (correctAnswer.equals(postedAnswer)) {
            result = AnswerFeedback.POSITIVE_FEEDBACK;
        }
        
        return result;
        
    }
    
    /**Method lets upload new questions defined by the user*/
    @PostMapping("/api/quizzes")
    private Question createQuestion(@Valid @RequestBody Question question) {
        //TODO: Handle wrong format of request etc.
        
        questionRepository.save(question);
        return question;
        
    }
    
}
