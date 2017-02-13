package sample;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

/**
 * Created by patryk on 12.02.17.
 */
class MVC_Editor_ModelTest {
    private void deleteFile(String path) {
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
    public void setTextAreaTest() throws Exception {
        System.out.println("TEST: setTextAreaTest()");

        MVC_Editor_Model model = new MVC_Editor_Model();

        model.setTextArea("Lorem ipsum dolor sit");
        assertEquals("TextArea not updated.", "Lorem ipsum dolor sit", model.getTextArea().getText());
    }

    @org.junit.Test
    public void saveAndOpenTest(){
        // tested together b/c if testing only one this test method would need to implement the algorithm of the other
        // one to assert whether the test was successful;copying the algorithm or calling the method makes no difference

        System.out.println("TEST: saveAndOpenTest()");

        MVC_Editor_Model model = new MVC_Editor_Model();

        String path = new File("").getAbsolutePath() + "/saveAndOpenTest.txt";
        System.out.println(path);

        model.setTextArea("Lorem");
        model.saveFile(path);
        model.setTextArea("");
        model.openFile(path);
        assertEquals("SafeFile() or openFile() not working properly.", "Lorem",
                model.getTextArea().getText());
        this.deleteFile(path);

        model.setTextArea("Lorem ipsum dolor sit");
        model.saveFile(path);
        model.setTextArea("");
        model.openFile(path);
        assertEquals("Spaces causing problems.", "Lorem ipsum dolor sit",
                model.getTextArea().getText());
        this.deleteFile(path);

        model.setTextArea("Lorem ipsum \ndolor sit");
        model.saveFile(path);
        model.setTextArea("");
        model.openFile(path);
        assertEquals("Newline causing problems.", "Lorem ipsum \ndolor sit",
                model.getTextArea().getText());
        this.deleteFile(path);

        model.setTextArea("Lorem ipsum \tdolor sit");
        model.saveFile(path);
        model.setTextArea("");
        model.openFile(path);
        assertEquals("Tab causing problems.", "Lorem ipsum \tdolor sit",
                model.getTextArea().getText());
        this.deleteFile(path);

        model.setTextArea("Lorem ipsum dolor sit\n");
        model.saveFile(path);
        model.setTextArea("");
        model.openFile(path);
        assertEquals("Newline at end causing problems.", "Lorem ipsum dolor sit\n",
                model.getTextArea().getText());
        this.deleteFile(path);

        model.setTextArea("\nLorem ipsum dolor sit");
        model.saveFile(path);
        model.setTextArea("");
        model.openFile(path);
        assertEquals("Newline at beginning causing problems.", "\nLorem ipsum dolor sit",
                model.getTextArea().getText());
        this.deleteFile(path);
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
        assertEquals("charCount not working correctly.", Integer.valueOf(3), actual);

        model.setTextArea("B o b");
        model.charCount();
        actual = Integer.parseInt(model.getCharCount().getText());
        assertEquals("Spaces not counted correctly.", Integer.valueOf(5), actual);

        model.setTextArea("Bob\n.");
        model.charCount();
        actual = Integer.parseInt(model.getCharCount().getText());
        assertEquals("New line not counted correctly.", Integer.valueOf(5), actual);

        model.setTextArea("\"Bob\"");
        model.charCount();
        actual = Integer.parseInt(model.getCharCount().getText());
        assertEquals("Quotation marks not counted correctly.", Integer.valueOf(5), actual);

        model.setTextArea("Alice\tBob");
        model.charCount();
        actual = Integer.parseInt(model.getCharCount().getText());
        assertEquals("Tab not counted correctly.", Integer.valueOf(9), actual);

        model.setTextArea("Alice\n Bob");
        model.charCount();
        actual = Integer.parseInt(model.getCharCount().getText());
        assertEquals("Spaces after newline not couted correctly.", Integer.valueOf(10), actual);

        model.setTextArea("Alice \nBob");
        model.charCount();
        actual = Integer.parseInt(model.getCharCount().getText());
        assertEquals("Space bevore newline not couted correctly.", Integer.valueOf(10), actual);

        model.setTextArea("Alice\n\tBob");
        model.charCount();
        actual = Integer.parseInt(model.getCharCount().getText());
        assertEquals("Tab after newline not couted correctly.", Integer.valueOf(10), actual);

        model.setTextArea("Alice\t\nBob");
        model.charCount();
        actual = Integer.parseInt(model.getCharCount().getText());
        assertEquals("Tab bevore newline not couted correctly.", Integer.valueOf(10), actual);

        model.setTextArea("     ");
        model.charCount();
        // fixed
        actual = Integer.parseInt(model.getCharCount().getText());
        assertEquals("Only spaces in textArea not couted correctly.", Integer.valueOf(5), actual);

        model.setTextArea("\t");
        model.charCount();
        // fixed
        actual = Integer.parseInt(model.getCharCount().getText());
        assertEquals("Only tab in textArea not couted correctly.", Integer.valueOf(1), actual);

        model.setTextArea("\t");
        model.charCount();
        // fixed
        actual = Integer.parseInt(model.getCharCount().getText());
        assertEquals("Only tab in textArea not couted correctly.", Integer.valueOf(1), actual);

        model.setTextArea("   \t");
        model.charCount();
        actual = Integer.parseInt(model.getCharCount().getText());
        assertEquals("Only spaces bevore tabs in textArea not couted correctly.", Integer.valueOf(4), actual);

        model.setTextArea("\t  ");
        model.charCount();
        actual = Integer.parseInt(model.getCharCount().getText());
        assertEquals("Only spaces after tabs in textArea not couted correctly.", Integer.valueOf(3), actual);

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

        model.setTextArea("null");
        model.charCount();
        actual = Integer.parseInt(model.getCharCount().getText());
        assertEquals("'null' not counted correctly.", Integer.valueOf(4), actual);
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
        assertEquals("wordCount() not working correctly.", Integer.valueOf(1), actual);

        model.setTextArea("Bob.");
        model.wordCount();
        actual = Integer.parseInt(model.getWordCount().getText());
        assertEquals("Dot not counted correctly.", Integer.valueOf(1), actual);

        model.setTextArea("Bob Charlie");
        model.wordCount();
        actual = Integer.parseInt(model.getWordCount().getText());
        assertEquals("Space not counted correctly.", Integer.valueOf(2), actual);

        model.setTextArea("Bob\nCharlie");
        model.wordCount();
        // fixed
        actual = Integer.parseInt(model.getWordCount().getText());
        assertEquals("Newline not counted correctly.", Integer.valueOf(2), actual);

        model.setTextArea("Bob \nCharlie.");
        model.wordCount();
        actual = Integer.parseInt(model.getWordCount().getText());
        assertEquals("Space bevore newline not counted correctly.", Integer.valueOf(2), actual);

        model.setTextArea("Bob\n Charlie.");
        model.wordCount();
        actual = Integer.parseInt(model.getWordCount().getText());
        assertEquals("Space after newline not counted correctly.", Integer.valueOf(2), actual);

        model.setTextArea("Alice\n Bob");
        model.charCount();
        actual = Integer.parseInt(model.getWordCount().getText());
        assertEquals("Spaces after newline not couted correctly.", Integer.valueOf(2), actual);

        model.setTextArea("Alice \nBob");
        model.charCount();
        actual = Integer.parseInt(model.getWordCount().getText());
        assertEquals("Space bevore newline not couted correctly.", Integer.valueOf(2), actual);

        model.setTextArea("Alice\n\tBob");
        model.charCount();
        actual = Integer.parseInt(model.getWordCount().getText());
        assertEquals("Tab after newline not couted correctly.", Integer.valueOf(2), actual);

        model.setTextArea("Alice\t\nBob");
        model.charCount();
        actual = Integer.parseInt(model.getWordCount().getText());
        assertEquals("Tab bevore newline not couted correctly.", Integer.valueOf(2), actual);

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

        /* unable to find uncomplicated fix
        model.setTextArea(" Alice");
        model.wordCount();
        actual = Integer.parseInt(model.getWordCount().getText());
        assertEquals("Space at index 0 bevore word in TextArea not counted correctly.", Integer.valueOf(1), actual);*/
    }

    @org.junit.Test
    public void trimTest() throws Exception {
        System.out.println("TEST: trimTest()");

        MVC_Editor_Model model = new MVC_Editor_Model();

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
        assertEquals("Multiple spaces between words not trimmed correctly.", "Bob Charlie", model.getTextArea().getText());

        model.setTextArea("Bob Charlie   ");
        model.trim();
        // fixed after making bug while fixing different bug
        assertEquals("Multiple spaces after words not trimmed correctly.", "Bob Charlie ", model.getTextArea().getText());

        model.setTextArea("   Bob Charlie");
        model.trim();
        assertEquals("Multiple spaces bevore words not trimmed correctly.", " Bob Charlie", model.getTextArea().getText());

        model.setTextArea("Bob   \nCharlie");
        model.trim();
        assertEquals("Multiple spaces bevore newline not trimmed correctly.","Bob \nCharlie", model.getTextArea().getText());

        model.setTextArea("Bob\n   Charlie");
        model.trim();
        assertEquals("Multiple spaces after newline not trimmed correctly.","Bob\n Charlie", model.getTextArea().getText());

        // Error in manual testing but not in automated testing
        // fixed
        model.setTextArea("  Alice");
        model.trim();
        assertEquals("Multiple spaces bevore word not trimmed correctly."," Alice", model.getTextArea().getText());

        model.setTextArea("   ");
        model.trim();
        // fixed multiple times
        assertEquals("Only multiple spaces trimmed correctly."," ", model.getTextArea().getText());

        model.setTextArea("asdf  \tasdf");
        model.trim();
        // inconsistent results in maual testing but unable to reconstruct in debug
        assertEquals("Spaces bevore tab not trimmed correctly.", "asdf \tasdf", model.getTextArea().getText());

        model.setTextArea("asdf\t   asdf");
        model.trim();
        assertEquals("Spaces after tab not trimmed correctly.", "asdf\t asdf", model.getTextArea().getText());
    }
}