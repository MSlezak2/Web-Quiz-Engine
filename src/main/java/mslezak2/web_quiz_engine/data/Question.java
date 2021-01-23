package mslezak2.web_quiz_engine.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/** Class represents single question as a part of the quiz. It contains of:<br>
 *  - unique id<br>
 * - title<br>
 * - text (the actual question)<br>
 * - array of options (the 3rd one is always the correct one)*/

@Entity
public class Question {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long id;
    
    @NotBlank
    private String title;
    
    @NotBlank
    private String text;
    
    @Size(min = 2, max = 10)
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "QUESTION_ID")
    private List<Option> options = new ArrayList<>();
    
    //TODO: Correct answers should not exceed the amount of options
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ElementCollection
    private Set<Integer> answer;
    
    
    
    public Question(String title, String text, List<Option> options, Set<Integer> answer) {
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer;
    }
    
    protected Question() {
    
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public long getId() {
        return id;
    }
    
//    @JsonProperty
    public void setAnswer(Set<Integer> answer) {
        this.answer = answer;
    }
    
//    @JsonIgnore
    public Set<Integer> getAnswer() {
        return answer;
    }
    
    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }
    public void setOptions(List<Option> options) {
        this.options = options;
    }


}
