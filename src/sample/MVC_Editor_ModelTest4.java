package sample;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;

/**
 * Created by patryk on 21.01.17.
 */

public class MVC_Editor_ModelTest4 {
    private void delete(String path) {
        try {
            Files.delete(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    void test(){
        // TODO
        /*try() {
            something.getExceptions();
            fail("Exception not thrown");
        } catch (Exception e) {
            assertArrayEquals(true, e instanceof MVC_Editor_Model.ModelException, "" + MVC_Editor_Model.ModelException);
        }*/
    }


    @org.junit.Test
    public void updateTextAreaTest() throws Exception {
        System.out.println("TEST: setTextAreaTest()");

        MVC_Editor_Model model = new MVC_Editor_Model();

        model.setTextArea("Lorem ipsum dolor sit");
        assertEquals("TextArea not updated.", "Lorem ipsum dolor sit", model.getTextArea().getText());
    }

    @org.junit.Test
    public void saveAndOpenTest(){
        System.out.println("TEST: saveAndOpenTest()");

        MVC_Editor_Model model = new MVC_Editor_Model();

        String path = new File("").getAbsolutePath() + "/saveAndOpenModuleTest.txt";
        System.out.println(path);

        model.setTextArea("Lorem ipsum dolor sit");
        model.saveFile(path);
        model.setTextArea("");
        model.openFile(path);
        assertEquals("SafeFile() or openFile() is not working properly.", "Lorem ipsum dolor sit",
                model.getTextArea().getText());
        this.delete(path);

        model.setTextArea("Lorem ipsum \ndolor sit");
        model.saveFile(path);
        model.setTextArea("");
        model.openFile(path);
        assertEquals("NewLine causing problems.", "Lorem ipsum \ndolor sit",
                model.getTextArea().getText());
        this.delete(path);

        model.setTextArea("Lorem ipsum \tdolor sit");
        model.saveFile(path);
        model.setTextArea("");
        model.openFile(path);
        assertEquals("Tab causing problems.", "Lorem ipsum \tdolor sit",
                model.getTextArea().getText());
        this.delete(path);

        model.setTextArea("Lorem ipsum dolor sit\n");
        model.saveFile(path);
        model.setTextArea("");
        model.openFile(path);
        assertEquals("Tab causing problems.", "Lorem ipsum dolor sit\n",
                model.getTextArea().getText());
        this.delete(path);
    }

    @org.junit.Test
    public void charCountTest() throws Exception {
        System.out.println("TEST: charCountTest()");

        MVC_Editor_Model model = new MVC_Editor_Model();
        // TODO streamline
        // TODO split tests so to make debug easier
        // TODO is 'actual' actually nessecary?

        model.setTextArea("Bob");
        model.charCount();
        Integer actual = Integer.parseInt(model.getCharCount().getText());
        assertEquals("'Bob' hat nicht 3 Zeichen", Integer.valueOf(3), actual);

        model.setTextArea("B o b");
        model.charCount();
        actual = Integer.parseInt(model.getCharCount().getText());
        assertEquals("'B o b' hat nicht 5 Zeichen", Integer.valueOf(5), actual);

        model.setTextArea("Bob\n.");
        model.charCount();
        actual = Integer.parseInt(model.getCharCount().getText());
        assertEquals("'Bob\n.' hat nicht 5 Zeichen", Integer.valueOf(5), actual);

        model.setTextArea("\"Bob\"");
        model.charCount();
        actual = Integer.parseInt(model.getCharCount().getText());
        assertEquals("'\"' not counted correctly.", Integer.valueOf(5), actual);

        model.setTextArea("Alice\tBob");
        model.charCount();
        actual = Integer.parseInt(model.getCharCount().getText());
        assertEquals("Tab not counted correctly.", Integer.valueOf(9), actual);

        model.setTextArea("Alice \n\tBob\t\n Charlie");
        model.charCount();
        actual = Integer.parseInt(model.getCharCount().getText());
        assertEquals("Tabs and spaces bevore and after newline not couted correctly.", Integer.valueOf(21), actual);

        model.setTextArea("     ");
        model.charCount();
        // fixed
        actual = Integer.parseInt(model.getCharCount().getText());
        assertEquals("Only spaces in textArea not couted correctly.", Integer.valueOf(5), actual);

        model.setTextArea("\t");
        model.charCount();
        // fixed
        actual = Integer.parseInt(model.getCharCount().getText());
        assertEquals("Only spaces and tabs in textArea not couted correctly.", Integer.valueOf(1), actual);

        model.setTextArea("   \t  ");
        model.charCount();
        actual = Integer.parseInt(model.getCharCount().getText());
        assertEquals("Only spaces and tabs in textArea not couted correctly.", Integer.valueOf(6), actual);

        model.setTextArea("   \n Alice \n   Bob");
        model.charCount();
        // fixed
        actual = Integer.parseInt(model.getCharCount().getText());
        assertEquals("Spaces and word in multiple lines not couted correctly.", Integer.valueOf(18), actual);

        model.setTextArea("   \n \t \n   \t");
        model.charCount();
        actual = Integer.parseInt(model.getCharCount().getText());
        assertEquals("Spaces and tabs in multiple lines not couted correctly.", Integer.valueOf(12), actual);

        model.setTextArea("");
        model.charCount();
        actual = Integer.parseInt(model.getCharCount().getText());
        assertEquals("Nothing in TextArea not counted correctly.", Integer.valueOf(0), actual);

        model.setTextArea("Null");
        model.charCount();
        actual = Integer.parseInt(model.getCharCount().getText());
        assertEquals("'Null' not counted correctly.", Integer.valueOf(4), actual);
    }

    @org.junit.Test
    public void wordCountTest() throws Exception {
        System.out.println("TEST: wordCountTest()");

        MVC_Editor_Model model = new MVC_Editor_Model();
        // TODO streamline
        // TODO split tests so to make debug easier
        // TODO is 'actual' actually nessecary?

        model.setTextArea("Bob");
        model.wordCount();
        Integer actual = Integer.parseInt(model.getWordCount().getText());
        assertEquals("'Bob' ist nicht 1 Wort.", Integer.valueOf(1), actual);

        model.setTextArea("Bob.");
        model.wordCount();
        actual = Integer.parseInt(model.getWordCount().getText());
        assertEquals("'Bob.' ist nicht 1 Wort.", Integer.valueOf(1), actual);

        model.setTextArea("Bob Charlie");
        model.wordCount();
        actual = Integer.parseInt(model.getWordCount().getText());
        assertEquals("'Bob Charlie' sind nicht 2 Worter.", Integer.valueOf(2), actual);

        model.setTextArea("Bob\nCharlie");
        model.wordCount();
        // fixed
        actual = Integer.parseInt(model.getWordCount().getText());
        assertEquals("'Bob\nCharlie' sind nicht 2 Worter.", Integer.valueOf(2), actual);

        model.setTextArea("Bob \n Charlie ");
        model.wordCount();
        actual = Integer.parseInt(model.getWordCount().getText());
        assertEquals("Mix of words, newLines and spaces not counted correctly.", Integer.valueOf(2), actual);

        model.setTextArea("Alice \n\tBob\t\n Charlie");
        model.wordCount();
        actual = Integer.parseInt(model.getWordCount().getText());
        assertEquals("Tabs and spaces bevore and after newline not couted correctly.", Integer.valueOf(3), actual);

        model.setTextArea("   \n \t \n   \t");
        model.wordCount();
        actual = Integer.parseInt(model.getWordCount().getText());
        assertEquals("Spaces and tabs in multiple lines not couted correctly.", Integer.valueOf(0), actual);

        model.setTextArea(" ");
        model.wordCount();
        actual = Integer.parseInt(model.getWordCount().getText());
        assertEquals("Only one space in TextArea not counted correctly.", Integer.valueOf(0), actual);

        model.setTextArea("     ");
        model.wordCount();
        actual = Integer.parseInt(model.getWordCount().getText());
        assertEquals("Only spaces in TextArea not counted correctly.", Integer.valueOf(0), actual);

        model.setTextArea("");
        model.wordCount();
        // fixed
        actual = Integer.parseInt(model.getWordCount().getText());
        assertEquals("Nothing in TextArea not counted correctly.", Integer.valueOf(0), actual);
    }

    @org.junit.Test
    public void trimTest() throws Exception {
        System.out.println("TEST: trimTest()");

        MVC_Editor_Model model = new MVC_Editor_Model();
        // TODO streamline
        // TODO split tests so to make debug easier

        model.setTextArea("Bob");
        model.trim();
        // fixed
        // fixed after making bug while fixing other bug
        assertEquals("No change expected.", "Bob", model.getTextArea().getText());

        model.setTextArea("Bob Charlie");
        model.trim();
        assertEquals("No change expected.", "Bob Charlie", model.getTextArea().getText());

        model.setTextArea("Bob\nCharlie");
        model.trim();
        assertEquals("No change expected.", "Bob\nCharlie", model.getTextArea().getText());

        model.setTextArea("Bob   Charlie");
        model.trim();
        // fixed after making bug while fixing different bug
        assertEquals("Bob Charlie", model.getTextArea().getText());

        model.setTextArea("Bob   Charlie   ");
        model.trim();
        // fixed after making bug while fixing different bug
        assertEquals("Bob Charlie ", model.getTextArea().getText());

        model.setTextArea("Bob Charlie   .");
        model.trim();
        // fixed
        assertEquals("Bob Charlie .", model.getTextArea().getText());

        model.setTextArea("Bob   Charlie   .");
        model.trim();
        assertEquals("Bob Charlie .", model.getTextArea().getText());

        model.setTextArea("Bob   \nCharlie   .");
        model.trim();
        assertEquals("Bob \nCharlie .", model.getTextArea().getText());

        model.setTextArea("Bob  \n  Charlie   .");
        model.trim();
        assertEquals("Bob \n Charlie .", model.getTextArea().getText());

        // Error in manual testing but not in automated testing
        // fixed
        model.setTextArea("  Alice");
        model.trim();
        assertEquals(" Alice", model.getTextArea().getText());

        model.setTextArea("   ");
        model.trim();
        // fixed multiple times
        assertEquals(" ", model.getTextArea().getText());

        model.setTextArea("asdf  \tasdf");
        model.trim();
        // inconsistent results in maual testing but unable to reconstruct in debug
        assertEquals("Spaces bevore tab not trimmed correctly.", "asdf \tasdf", model.getTextArea().getText());
    }
}