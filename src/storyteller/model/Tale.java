package storyteller.model;

/**
 *
 * @author zaido
 */
public class Tale {
    private int id;
    private String title;
    private String spanishText;
    private State state;
    
    public Tale(int id, String title) {
        this.id = id;
        this.title = title;
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
