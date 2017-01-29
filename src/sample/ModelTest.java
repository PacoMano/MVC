package sample;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

/**
 * Created by patryk on 21.01.17.
 */

public class ModelTest {

    private void delete(String path) {
        try {
            Files.delete(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void updateTextArea() throws Exception {
        System.out.println("TEST: updateTextArea()");

        Model model = new Model();

        model.updateTextArea("Lorem ipsum dolor sit");
        assertEquals("TextArea not updated.", "Lorem ipsum dolor sit", model.getTextArea().getText());
    }

    @org.junit.Test
    public void openFile() throws Exception {
        System.out.println("TEST: openFile()");
    }

    @org.junit.Test
    public void saveFile() throws Exception {
        System.out.println("TEST: saveFile()");
    }

    @org.junit.Test
    public void saveAndOpen(){
        System.out.println("TEST: saveAndOpen()");

        Model model = new Model();

        String os = System.getProperty("os.name");
        System.out.println("OS: " + os);
        String path = new String();
        if (os == "Windows") {
            path = new File("").getAbsolutePath() + "\\Test.txt";
        } else {
            path = new File("").getAbsolutePath() + "/Test.txt";
        }
        System.out.println(path);

        model.updateTextArea("Lorem ipsum dolor sit");
        model.saveFile(path);
        model.updateTextArea("");
        model.openFile(path);
        assertEquals("SafeFile() or openFile() is not working properly.", "Lorem ipsum dolor sit",
                model.getTextArea().getText());
        this.delete(path);

        model.updateTextArea("Lorem ipsum \ndolor sit");
        model.saveFile(path);
        model.updateTextArea("");
        model.openFile(path);
        assertEquals("NewLine causing problems.", "Lorem ipsum \ndolor sit",
                model.getTextArea().getText());
        this.delete(path);

        model.updateTextArea("Lorem ipsum \tdolor sit");
        model.saveFile(path);
        model.updateTextArea("");
        model.openFile(path);
        assertEquals("Tab causing problems.", "Lorem ipsum \tdolor sit",
                model.getTextArea().getText());
        this.delete(path);

        // TODO
        /*model.updateTextArea("Lorem ipsum dolor sit\n");
        model.saveFile(path);
        model.updateTextArea("");
        model.openFile(path);
        assertEquals("Newline at end causing problems.", "Lorem ipsum dolor sit\n",
                model.getTextArea().getText());
        this.delete(path);*/
    }

    @org.junit.Test
    public void charCount() throws Exception {
        System.out.println("TEST: charCount()");

        Model model = new Model();
        // TODO streamline
        // TODO split tests so to make debug easier
        // TODO is 'actual' actually nessecary?

        model.updateTextArea("Bob");
        model.charCount();
        Integer actual = Integer.parseInt(model.getCharCount().getText());
        assertEquals("'Bob' hat nicht 3 Zeichen", Integer.valueOf(3), actual);

        model.updateTextArea("B o b");
        model.charCount();
        actual = Integer.parseInt(model.getCharCount().getText());
        assertEquals("'B o b' hat nicht 5 Zeichen", Integer.valueOf(5), actual);

        model.updateTextArea("Bob\n.");
        model.charCount();
        actual = Integer.parseInt(model.getCharCount().getText());
        assertEquals("'Bob\n.' hat nicht 5 Zeichen", Integer.valueOf(5), actual);

        model.updateTextArea("\"Bob\"");
        model.charCount();
        actual = Integer.parseInt(model.getCharCount().getText());
        assertEquals("'\"' not counted correctly.", Integer.valueOf(5), actual);

        model.updateTextArea("Alice\tBob");
        model.charCount();
        actual = Integer.parseInt(model.getCharCount().getText());
        assertEquals("Tab not counted correctly.", Integer.valueOf(9), actual);

        model.updateTextArea("Alice \n\tBob\t\n Charlie");
        model.charCount();
        actual = Integer.parseInt(model.getCharCount().getText());
        assertEquals("Tabs and spaces bevore and after newline not couted correctly.", Integer.valueOf(21), actual);

        model.updateTextArea("     ");
        model.charCount();
        // fixed
        actual = Integer.parseInt(model.getCharCount().getText());
        assertEquals("Only spaces in textArea not couted correctly.", Integer.valueOf(5), actual);

        model.updateTextArea("\t");
        model.charCount();
        // fixed
        actual = Integer.parseInt(model.getCharCount().getText());
        assertEquals("Only spaces and tabs in textArea not couted correctly.", Integer.valueOf(1), actual);

        model.updateTextArea("   \t  ");
        model.charCount();
        actual = Integer.parseInt(model.getCharCount().getText());
        assertEquals("Only spaces and tabs in textArea not couted correctly.", Integer.valueOf(6), actual);

        model.updateTextArea("   \n Alice \n   Bob");
        model.charCount();
        // fixed
        actual = Integer.parseInt(model.getCharCount().getText());
        assertEquals("Spaces and word in multiple lines not couted correctly.", Integer.valueOf(18), actual);

        model.updateTextArea("   \n \t \n   \t");
        model.charCount();
        actual = Integer.parseInt(model.getCharCount().getText());
        assertEquals("Spaces and tabs in multiple lines not couted correctly.", Integer.valueOf(12), actual);

        model.updateTextArea("");
        model.charCount();
        actual = Integer.parseInt(model.getCharCount().getText());
        assertEquals("Nothing in TextArea not counted correctly.", Integer.valueOf(0), actual);

        model.updateTextArea("Null");
        model.charCount();
        actual = Integer.parseInt(model.getCharCount().getText());
        assertEquals("'Null' not counted correctly.", Integer.valueOf(4), actual);
    }

    @org.junit.Test
    public void wordCount() throws Exception {
        System.out.println("TEST: wordCount()");

        Model model = new Model();
        // TODO streamline
        // TODO split tests so to make debug easier
        // TODO is 'actual' actually nessecary?

        model.updateTextArea("Bob");
        model.wordCount();
        Integer actual = Integer.parseInt(model.getWordCount().getText());
        assertEquals("'Bob' ist nicht 1 Wort.", Integer.valueOf(1), actual);

        model.updateTextArea("Bob.");
        model.wordCount();
        actual = Integer.parseInt(model.getWordCount().getText());
        assertEquals("'Bob.' ist nicht 1 Wort.", Integer.valueOf(1), actual);

        model.updateTextArea("Bob Charlie");
        model.wordCount();
        actual = Integer.parseInt(model.getWordCount().getText());
        assertEquals("'Bob Charlie' sind nicht 2 Worter.", Integer.valueOf(2), actual);

        model.updateTextArea("Bob\nCharlie");
        model.wordCount();
        // fixed
        actual = Integer.parseInt(model.getWordCount().getText());
        assertEquals("'Bob\nCharlie' sind nicht 2 Worter.", Integer.valueOf(2), actual);

        model.updateTextArea("Bob \n Charlie ");
        model.wordCount();
        actual = Integer.parseInt(model.getWordCount().getText());
        assertEquals("Mix of words, newLines and spaces not counted correctly.", Integer.valueOf(2), actual);

        model.updateTextArea("Alice \n\tBob\t\n Charlie");
        model.wordCount();
        actual = Integer.parseInt(model.getWordCount().getText());
        assertEquals("Tabs and spaces bevore and after newline not couted correctly.", Integer.valueOf(3), actual);

        model.updateTextArea("   \n \t \n   \t");
        model.wordCount();
        actual = Integer.parseInt(model.getWordCount().getText());
        assertEquals("Spaces and tabs in multiple lines not couted correctly.", Integer.valueOf(0), actual);

        model.updateTextArea(" ");
        model.wordCount();
        actual = Integer.parseInt(model.getWordCount().getText());
        assertEquals("Only one space in TextArea not counted correctly.", Integer.valueOf(0), actual);

        model.updateTextArea("     ");
        model.wordCount();
        actual = Integer.parseInt(model.getWordCount().getText());
        assertEquals("Only spaces in TextArea not counted correctly.", Integer.valueOf(0), actual);

        model.updateTextArea("");
        model.wordCount();
        // fixed
        actual = Integer.parseInt(model.getWordCount().getText());
        assertEquals("Nothing in TextArea not counted correctly.", Integer.valueOf(0), actual);
    }

    @org.junit.Test
    public void trim() throws Exception {
        System.out.println("TEST: trim()");

        Model model = new Model();
        // TODO streamline
        // TODO split tests so to make debug easier

        model.updateTextArea("Bob");
        model.trim();
        // fixed
        // fixed after making bug while fixing other bug
        assertEquals("No change expected.", "Bob", model.getTextArea().getText());

        model.updateTextArea("Bob Charlie");
        model.trim();
        assertEquals("No change expected.", "Bob Charlie", model.getTextArea().getText());

        model.updateTextArea("Bob\nCharlie");
        model.trim();
        assertEquals("No change expected.", "Bob\nCharlie", model.getTextArea().getText());

        model.updateTextArea("Bob   Charlie");
        model.trim();
        // fixed after making bug while fixing different bug
        assertEquals("Bob Charlie", model.getTextArea().getText());

        model.updateTextArea("Bob   Charlie   ");
        model.trim();
        // fixed after making bug while fixing different bug
        assertEquals("Bob Charlie ", model.getTextArea().getText());

        model.updateTextArea("Bob Charlie   .");
        model.trim();
        // fixed
        assertEquals("Bob Charlie.", model.getTextArea().getText());

        model.updateTextArea("Bob   Charlie   .");
        model.trim();
        assertEquals("Bob Charlie.", model.getTextArea().getText());

        model.updateTextArea("Bob   \nCharlie   .");
        model.trim();
        assertEquals("Bob \nCharlie.", model.getTextArea().getText());

        model.updateTextArea("Bob  \n  Charlie   .");
        model.trim();
        assertEquals("Bob \n Charlie.", model.getTextArea().getText());

        model.updateTextArea("   ");
        model.trim();
        // fixed multiple times
        assertEquals("", model.getTextArea().getText());

        model.updateTextArea("asdf  \tasdf");
        model.trim();
        // inconsistent results in maual testing but unable to reconstruct in debug
        assertEquals("Spaces bevore tab not trimmed correctly.", "asdf \tasdf", model.getTextArea().getText());
    }
}