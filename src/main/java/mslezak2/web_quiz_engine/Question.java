package mslezak2.web_quiz_engine;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/** Class represents single question as a part of the quiz. It contains of:<br>
 *  - unique id<br>
 * - title<br>
 * - text (the actual question)<br>
 * - array of options (the 3rd one is always the correct one)*/

public class Question {
    
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;
    @NotBlank
    private String title;
    @NotBlank
    private String text;
    @Size(min=2)
    private String[] options;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int[] answer;
    
    public Question(String title, String text, String[] options, int[] answer) {
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }
    
//    @JsonProperty
    public void setAnswer(int[] answer) {
        this.answer = answer;
    }
    
//    @JsonIgnore
    public int[] getAnswer() {
        return answer;
    }
    
    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String[] getOptions() {
        return options;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }
}
