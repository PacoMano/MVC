package sample;

import java.nio.file.Files;

import static org.junit.Assert.*;

/**
 * Created by dqi15bierzynski on 27.01.2017.
 */
public class ModelTest {
    @org.junit.Test
    public void openFile() throws Exception {

    }

    @org.junit.Test
    public void saveFile() throws Exception {
        Model model = new Model();
        // model.saveFile("C:\\Users\\Public\\Desktop\\test");
        // Files.lines()
    }

    @org.junit.Test
    public void trim() throws Exception {
        Model model = new Model();
        model.trim(new StringBuilder("Bob"));
        System.out.println(model.getTextArea().getText());
        assertEquals("No change expected", "Bob", model.getTextArea().getText());
        model.trim(new StringBuilder("Bob Charlie"));
        assertEquals("No change expected", "Bob Charlie", model.getTextArea().getText());
        model.trim(new StringBuilder("Bob\nCharlie"));
        assertEquals("No change expected", "Bob\nCharlie", model.getTextArea().getText());
        model.trim(new StringBuilder("Bob   Charlie"));
        assertEquals("Bob Charlie", model.getTextArea().getText());
        model.trim(new StringBuilder("Bob   Charlie   "));
        assertEquals("Bob Charlie", model.getTextArea().getText());
        model.trim(new StringBuilder("Bob Charlie   ."));
        assertEquals("Bob Charlie.", model.getTextArea().getText());
        model.trim(new StringBuilder("Bob   Charlie   ."));
        assertEquals("Bob Charlie.", model.getTextArea().getText());
        model.trim(new StringBuilder("Bob   \nCharlie   ."));
        assertEquals("Bob \nCharlie.", model.getTextArea().getText());
        model.trim(new StringBuilder("Bob  \n  Charlie   ."));
        assertEquals("Bob \n Charlie.", model.getTextArea().getText());
        model.trim(new StringBuilder("   "));
        assertEquals(" ", model.getTextArea().getText());
    }

    /*@org.junit.Test
    public void getCharCount() throws Exception {
        Model model = new Model();
        // TODO ENGLISH
        assertEquals("'Bob' hat nicht 3 Zeichen", Integer.valueOf(3), model.getCharCount("Bob"));
        assertEquals("'B o b' hat nicht 5 Zeichen", Integer.valueOf(5), model.getCharCount("B o b"));
        assertEquals("'Bob\n.' hat nicht 5 Zeichen", Integer.valueOf(5), model.getCharCount("Bob\n."));
        assertEquals("'\"Bob\"' hat nicht 5 Zeichen", Integer.valueOf(5), model.getCharCount("\"Bob\""));
        assertEquals("'Null' hat nicht 4 Zeichen", Integer.valueOf(4), model.getCharCount("Null"));
    }

    @org.junit.Test
    public void getWordCount() throws Exception {
        Model model = new Model();
        // TODO ENGLISH
        assertEquals("'Bob' ist nicht 1 Wort", Integer.valueOf(1), model.getWordCount("Bob"));
        assertEquals("'Bob.' ist nicht 1 Wort", Integer.valueOf(1), model.getWordCount("Bob."));
        assertEquals("'Bob Charlie' sind nicht 2 Worter", Integer.valueOf(2), model.getWordCount("Bob Charlie"));
        assertEquals("'Bob\nCharlie' sind nicht 2 Worter", Integer.valueOf(2), model.getWordCount("Bob\nCharlie"));
        assertEquals("' ' ist nicht 1 Wort", Integer.valueOf(1), model.getWordCount(" "));

    }*/

}