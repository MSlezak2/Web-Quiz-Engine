package mslezak2.web_quiz_engine.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Option {
    
    @Id
    @Column()
    int id;
    
    String option;
    
    public Option(String option) {
        this.option = option;
    }
    
    public Option() {
    
    }
}
