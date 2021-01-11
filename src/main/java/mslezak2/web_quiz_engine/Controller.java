package mslezak2.web_quiz_engine;

import mslezak2.web_quiz_engine.data.AnswerFeedback;
import mslezak2.web_quiz_engine.data.Question;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.*;

/** Controller that handles clients requests such as fetching the question and submitting the answer */
@RestController
public class Controller {
    
    QuestionRepository questionRepository;

    
    ArrayList<Question> questions = new ArrayList<>(); // it's going to be replaced with the database in the future
//    {
//        questions.add(new Question("q1", "t1", new String[]{"a1","a2","a3","a4"}, new int[]{2,3})); // initializing list for testing purposes
//    }

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
    
    //TODO: Implement mechanism to convert answer from int[] to String (and vice versa)
    /**
     * POST request should contain JSON object inside its body with single property named "answer" with int
     * as a value representing chosen answer
     */
    @PostMapping("/api/quizzes/{id}/solve") //TODO: Is that HashMap as type of RequestBody OK, or is there any better way to do this?
    private AnswerFeedback postAnswer(@PathVariable long id, @RequestBody HashMap<String, String> answer) {
    
        AnswerFeedback result = AnswerFeedback.NEGATIVE_FEEDBACK;
        
        //fetching the question by provided id and its correct answer field
        Question question;
        Optional<Question> questionOptional = questionRepository.findById(id);
        if (questionOptional.isPresent()) {
            
            question = questionOptional.orElseThrow();
            //TODO: Handle wrong id with suitable exception
            String correctAnswer = question.getAnswer();
    
            //fetching posted answer and checking if it's correct
            String postedAnswer = answer.get("answer");
            if (Objects.equals(correctAnswer, postedAnswer)) {
                result = AnswerFeedback.POSITIVE_FEEDBACK;
            }
            
        }
        
        return result;
        
//
//        if (id < questions.size()) {
//            // TODO: Should answer parameter be of that type? Is there any other, cooler solution?
//
//            int[] postedAnswer = answer.get("answer");
//            String correctAnswerString= questions.get(id).getAnswer();
//            int[] correctAnswer = answerStringToIntArray(correctAnswerString);
//
//            if (isAnswerCorrect(postedAnswer, correctAnswer)) {
//                return AnswerFeedback.POSITIVE_FEEDBACK;
//            } else {
//                return AnswerFeedback.NEGATIVE_FEEDBACK;
//            }
//
//        } else {
//            throw new ResponseStatusException(
//                    HttpStatus.NOT_FOUND, "(not found)"
//            );
//        }
//
    }
    
    private int[] answerStringToIntArray(String correctAnswerString) {
        int[] correctAnswer = new int[correctAnswerString.length()];
        for (int i = 0; i < correctAnswerString.length(); i++) {
            correctAnswer[i] = (int) correctAnswerString.charAt(i);
        }
        return correctAnswer;
    }
    
    /**Method lets upload new questions defined by the user*/
    @PostMapping("/api/quizzes")
    private Question createQuestion(@Valid @RequestBody Question question) {
        
        //TODO: Is there any more elegant way to handle that id setting?
        question.setId(questions.size()); //every question gets its unique id
        questions.add(question);
        return questions.get(questions.size() - 1);
        
    }
    
    
    private boolean isAnswerCorrect(int[] postedAnswer, int[] correctAnswer) {
        
        boolean correct = false;
        if (Arrays.equals(postedAnswer, correctAnswer)
            || (postedAnswer == null && correctAnswer.length == 0)
            || (postedAnswer.length == 0 && correctAnswer == null) ) {
            
            correct = true;
            
        }
        return correct;
        
    }
}
