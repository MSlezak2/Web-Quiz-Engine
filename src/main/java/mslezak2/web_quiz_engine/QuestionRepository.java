package mslezak2.web_quiz_engine;

import mslezak2.web_quiz_engine.data.Question;
import org.springframework.data.repository.CrudRepository;

public interface QuestionRepository extends CrudRepository<Question,Long> {

}
