package mslezak2.web_quiz_engine.data;


import javax.persistence.Entity;

//TODO: To replace it with an enum class

/** Feedback to send back as JSON object to the user, after he submits the answer.
 * It consists of a boolean variable along with a String phrase that describe
 * the correctness of given answer.
 *  */
public class AnswerFeedback {

    //@JSONIgnore
    public static final AnswerFeedback NEGATIVE_FEEDBACK
            = new AnswerFeedback(false, "Wrong answer! Please, try again.");
    //@JSONIgnore
    public static final AnswerFeedback POSITIVE_FEEDBACK
            = new AnswerFeedback(true, "Congratulations, you're right!");

    private boolean success;
    private String feedback;

    public AnswerFeedback(boolean success, String feedback) {
        this.success = success;
        this.feedback = feedback;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
