import java.util.List;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import storyteller.controller.AnswerQuestionController;
import storyteller.model.pojo.Question;

public class AnswerQuestionControllerTest {
    private AnswerQuestionController controller;
    
    @Test
    public void matchAnswerWResponse(){
        String word = controller.setWord("Hola");
        String answer = "Hola";
        boolean wordCorrect = answer.equals(word);
        assertEquals(wordCorrect, true);
    }
    
    @Test
    public void testCorrectAnswer(){
        String word = controller.getWord();
        String answer = "Manzana";
        boolean wordCorrect = answer.equals(word);
        assertEquals(wordCorrect, true);
    }
    
    @Test
    public void testWrongAnswer(){
        String word = controller.getWord();
        String answer = "asdeas";
        boolean wordCorrect = answer.equals(word);
        assertEquals(wordCorrect, false);
    }
    
    @Test
    public void testAnswerSlotEmpty(){
        boolean slotEmpty = controller.answerQuestion("");
        assertEquals(slotEmpty, true);
    }
}
