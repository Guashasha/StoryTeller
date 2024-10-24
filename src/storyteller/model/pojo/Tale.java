package storyteller.model.pojo;

import storyteller.model.pojo.State;
import storyteller.model.pojo.Question;

/**
 *
 * @author zaido
 */
public class Tale {
    private int id;
    private String title;
    private String spanishText;
    private State state;
    private Question question;
    
    public Tale(){
    }
    
    public Tale(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSpanishText() {
        return spanishText;
    }

    public void setSpanishText(String spanishText) {
        this.spanishText = spanishText;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
    
}
