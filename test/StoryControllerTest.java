

import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import storyteller.model.pojo.Tale;
import storyteller.controller.StoryController;

/**
 *
 * @author zaido
 */
public class StoryControllerTest {
    private StoryController controller;
    
    @Test
    public void testGetAllTales() {
        List<Tale> tales = controller.getAllStories();
        int talesExpected = 6;
        assertEquals(tales.size(), talesExpected);
    }
    
    @Test
    public void testGetTale() {
        Tale taleObtained = controller.getTale(1);
        String taleTitleExpected = "The magical tree";
        if(taleObtained != null) {
            assertEquals(taleObtained.getTitle(), taleTitleExpected);
        } else {
            fail("Cuento obtenido es null");
        }
    }
    
    @Test
    public void testGetUnreadTales_MaxSize() {
        List<Tale> unreadTales = controller.getUnreadTales();
        List<Tale> unreadTalesExpected = Arrays.asList(
                new Tale(1, "The magical tree"),
                new Tale(2, "Why did the dragon get depressed"),
                new Tale(3, "Friendship tale"),
                new Tale(4, "The homeless eye"),
                new Tale(5, "The magician's world war"),
                new Tale(6, "The elephant pants")
        );
        assertEquals(unreadTalesExpected, unreadTales);
    }
    
    @Test
    public void getReadTales_MaxSize() {
        List<Tale> readTales = controller.getReadTales();
        List<Tale> readTalesExpected = Arrays.asList(
                new Tale(1, "The magical tree"),
                new Tale(2, "Why did the dragon get depressed"),
                new Tale(3, "Friendship tale"),
                new Tale(4, "The homeless eye"),
                new Tale(5, "The magician's world war"),
                new Tale(6, "The elephant pants")
        );
        assertEquals(readTales, readTalesExpected);
    }
}
