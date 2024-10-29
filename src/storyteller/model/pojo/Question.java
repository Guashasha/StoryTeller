package storyteller.model.pojo;

/**
 *
 * @author zaido
 */
public class Question {
    private String spanishQuestion;
    private String englishQuestion;
    private Word answers;
    private boolean answered;

    public String getSpanishQuestion() {
        return spanishQuestion;
    }

    public void setSpanishQuestion(String spanishQuestion) {
        this.spanishQuestion = spanishQuestion;
    }

    public String getEnglishQuestion() {
        return englishQuestion;
    }

    public void setEnglishQuestion(String englishQuestion) {
        this.englishQuestion = englishQuestion;
    }

    public Word getQuestionAnswer() { return answers;}

    public void setQuestionAnswer(Word answers) { this.answers = answers; }

    public boolean getAnswered() {
        return answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }
    
    
}
