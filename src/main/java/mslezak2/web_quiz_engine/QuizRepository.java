package mslezak2.web_quiz_engine;

import mslezak2.web_quiz_engine.data.Question;
import org.springframework.data.repository.CrudRepository;

public interface QuizRepository extends CrudRepository<Question,Long> {

}
