package mslezak2.web_quiz_engine.data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Option {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@JsonIgnore
    private long id;
    @NotBlank
    private String option;
    
    public Option(String option) {
        this.option = option;
    }
    
    protected Option() {
    
    }
    
    public long getId() {
        return id;
    }
    
    public String getOption() {
        return option;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public void setOption(String option) {
        this.option = option;
    }
}
