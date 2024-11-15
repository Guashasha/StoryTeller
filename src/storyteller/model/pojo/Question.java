package storyteller.model.pojo;

/**
 *
 * @author zaido
 */
public class Question {
    private String spanishQuestion;
    private String englishQuestion;
    private Word answer;
    private int answered;

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

    public Word getQuestionAnswer() {return answer;}

    public void SetQuestionAnswer(Word answer) { this.answer = answer;}

    public int isAnswered() {
        return answered;
    }

    public void setIsAnswered(int answered) {
        this.answered = answered;
    }
    
    
}
