package mslezak2.web_quiz_engine;

/** Class represents single question as a part of the quiz. It contains of:<br>
 * - title<br>
 * - text (the actual question)<br>
 * - array of options (the 3rd one is always the correct one)*/
public class Question {

    private String title;
    private String text;
    private String[] options;

    public Question(String title, String text, String[] options) {
        this.title = title;
        this.text = text;
        this.options = options;
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
