package mslezak2.web_quiz_engine;

import mslezak2.web_quiz_engine.data.Option;
import mslezak2.web_quiz_engine.data.Question;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class WebQuizEngineApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebQuizEngineApplication.class, args);
    }
    
    //test
    @Bean
    public CommandLineRunner demo(QuestionRepository questionRepository) {
        return (args) -> {
            
            List<Option> opt = new ArrayList<>();
            opt.add(new Option("option 1"));
            opt.add(new Option("option 2"));
            List<Option> opt2 = new ArrayList<>();
            opt2.add(new Option("option 1"));
            opt2.add(new Option("option 2"));
            opt2.add(new Option("option 4"));
            Question question1 = new Question("Tytul pytania 1","Tekst pytania 1",opt,Set.of(0,1,2));
            Question question2 = new Question("Tytul pytania 2","Tekst pytania 2",opt2,Set.of(0));
            questionRepository.save(question1);
            questionRepository.save(question2);
            System.out.println(question1.getId() + "  " + question2.getId());
            
        };
    }

}
