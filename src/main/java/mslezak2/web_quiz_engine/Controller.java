package mslezak2.web_quiz_engine;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/** Controller that handles clients requests such as fetching the question and submitting the answer */
@RestController
public class Controller {

    ArrayList<Question> questions = new ArrayList<>(); // it's going to be replaced with the database in the future
    {
        questions.add(new Question("q1", "t1", new String[]{"a1","a2","a3","a4"})); // initializing list for testing purposes
    }

    @GetMapping("/api/quiz/{index}")
    private Question getQuestion(@PathVariable int index) {
        return questions.get(index);
    }

}
