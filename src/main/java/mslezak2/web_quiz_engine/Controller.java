package mslezak2.web_quiz_engine;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/** Controller that handles clients requests such as fetching the question and submitting the answer */
@RestController
public class Controller {

    ArrayList<Question> questions = new ArrayList<>(); // it's going to be replaced with the database in the future
    {
        questions.add(new Question("q1", "t1", new String[]{"a1","a2","a3","a4"}, 2)); // initializing list for testing purposes
    }

    @GetMapping("/api/quiz/{index}")
    private Question getQuestion(@PathVariable int index) {
        return questions.get(index);
    }

    @PostMapping("/api/quiz")
    private AnswerFeedback postAnswer(@RequestParam int answer) {
        if (answer == 2) {
            return AnswerFeedback.POSITIVE_FEEDBACK;
        } else {
            return AnswerFeedback.NEGATIVE_FEEDBACK;
        }
    }
    
    /**Method lets upload new questions defined by the user*/
    @PostMapping("/api/quizzes")
    private Question createQuestion(@RequestBody Question question) {
        question.setId(questions.size()); //every question gets its unique id
        questions.add(question);
        return questions.get(questions.size() - 1);
    }
}
