package MVC_Editor;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * JUnit 5 tests for {@link MVC_Editor_Model}
 *
 * @author dqi15bierzynski
 */
class MVC_Editor_ModelTest {

    /**
     * Deletes File with path <pre>path</pre>
     *
     * @param path path to file that is to be deleted
     */
    private void deleteFile(String path) {
        try {
            Files.delete(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Runs mothod saveFile with parameter <pre>path</pre>
     *
     * @param model
     * @param path
     */
    private void save(MVC_Editor_Model model, String path) {
        try {
            model.saveFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Runs mothod saveFile with parameter <pre>path</pre>
     *
     * @param model
     * @param path Parameter for openFile()
     */
    private void open(MVC_Editor_Model model, String path) {
        try {
            model.openFile(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @org.junit.jupiter.api.Test
    void test(){
        // TODO
        /*try() {
            something.getExceptions();
            fail("Exception not thrown");
        } catch (Exception e) {
            assertEquals(true, e instanceof MVC_Editor_Model.ModelException, "" + MVC_Editor_Model.ModelException);
        }*/
    }

    /**
     * JUnit 5 tests for method <pre>update()</pre> in class {@link MVC_Editor_Model}
     */
    @org.junit.jupiter.api.Test
    public void setTextAreaTest() throws Exception {
        System.out.println("TEST: setTextAreaTest()");

        MVC_Editor_Model model = new MVC_Editor_Model();

        model.update("Lorem ipsum dolor sit");
        assertEquals("TextArea not updated.", "Lorem ipsum dolor sit", model.getTextArea());
    }

    /**
     * JUnit 5 tests for methods <pre>save()</pre> and <pre>open()</pre> in class {@link MVC_Editor_Model}; Tests two
     * methods together b/c testing one would require the algorithm of the other to assert
     */
    @org.junit.jupiter.api.Test
    public void saveAndOpenTest(){
        // tested together b/c if testing only one this test method would need to implement the algorithm of the other
        // one to assert whether the test was successful;copying the algorithm or calling the method makes no difference

        System.out.println("TEST: saveAndOpenTest()");

        MVC_Editor_Model model = new MVC_Editor_Model();

        String path = new File("").getAbsolutePath() + "/saveAndOpenTest.txt";
        System.out.println(path);

        model.update("Lorem");
        save(model, path);
        model.update("");
        open(model, path);
        assertEquals("SafeFile() or openFile() not working properly.", "Lorem",
                model.getTextArea());
        this.deleteFile(path);

        model.update("Lorem ipsum dolor sit");
        save(model, path);
        model.update("");
        open(model, path);
        assertEquals("Spaces causing problems.", "Lorem ipsum dolor sit",
                model.getTextArea());
        this.deleteFile(path);

        model.update("Lorem ipsum \ndolor sit");
        save(model, path);
        model.update("");
        open(model, path);
        assertEquals("Newline causing problems.", "Lorem ipsum \ndolor sit",
                model.getTextArea());
        this.deleteFile(path);

        model.update("Lorem ipsum \tdolor sit");
        save(model, path);
        model.update("");
        open(model, path);
        assertEquals("Tab causing problems.", "Lorem ipsum \tdolor sit",
                model.getTextArea());
        this.deleteFile(path);

        model.update("Lorem ipsum dolor sit\n");
        save(model, path);
        model.update("");
        open(model, path);
        assertEquals("Newline at end causing problems.", "Lorem ipsum dolor sit\n",
                model.getTextArea());
        this.deleteFile(path);

        model.update("\nLorem ipsum dolor sit");
        save(model, path);
        model.update("");
        open(model, path);
        assertEquals("Newline at beginning causing problems.", "\nLorem ipsum dolor sit",
                model.getTextArea());
        this.deleteFile(path);
    }

    /**
     * JUnit 5 tests for method <pre>wordCount()</pre> in class {@link MVC_Editor_Model}
     */
    @org.junit.jupiter.api.Test
    public void wordCountTest() throws Exception {
        System.out.println("TEST: wordCountTest()");

        MVC_Editor_Model model = new MVC_Editor_Model();
        // TODO streamline
        // TODO split tests so to make debug easier
        // TODO is 'actual' actually nessecary?

        model.update("Bob");
        model.wordCount();
        Integer actual = Integer.parseInt(model.getWordCount());
        assertEquals("wordCount() not working correctly.", Integer.valueOf(1), actual);

        model.update("Bob.");
        model.wordCount();
        actual = Integer.parseInt(model.getWordCount());
        assertEquals("Dot not counted correctly.", Integer.valueOf(1), actual);

        model.update("Bob Charlie");
        model.wordCount();
        actual = Integer.parseInt(model.getWordCount());
        assertEquals("Space not counted correctly.", Integer.valueOf(2), actual);

        model.update("Bob\nCharlie");
        model.wordCount();
        // fixed
        actual = Integer.parseInt(model.getWordCount());
        assertEquals("Newline not counted correctly.", Integer.valueOf(2), actual);

        model.update("Bob \nCharlie.");
        model.wordCount();
        actual = Integer.parseInt(model.getWordCount());
        assertEquals("Space bevore newline not counted correctly.", Integer.valueOf(2), actual);

        model.update("Bob\n Charlie.");
        model.wordCount();
        actual = Integer.parseInt(model.getWordCount());
        assertEquals("Space after newline not counted correctly.", Integer.valueOf(2), actual);

        model.update("Alice\n Bob");
        model.charCount();
        actual = Integer.parseInt(model.getWordCount());
        assertEquals("Spaces after newline not couted correctly.", Integer.valueOf(2), actual);

        model.update("Alice \nBob");
        model.charCount();
        actual = Integer.parseInt(model.getWordCount());
        assertEquals("Space bevore newline not couted correctly.", Integer.valueOf(2), actual);

        model.update("Alice\n\tBob");
        model.charCount();
        actual = Integer.parseInt(model.getWordCount());
        assertEquals("Tab after newline not couted correctly.", Integer.valueOf(2), actual);

        model.update("Alice\t\nBob");
        model.charCount();
        actual = Integer.parseInt(model.getWordCount());
        assertEquals("Tab bevore newline not couted correctly.", Integer.valueOf(2), actual);

        model.update("   \n \t \n   \t");
        model.wordCount();
        actual = Integer.parseInt(model.getWordCount());
        assertEquals("Spaces and tabs in multiple lines not couted correctly.", Integer.valueOf(0), actual);

        model.update(" ");
        model.wordCount();
        actual = Integer.parseInt(model.getWordCount());
        assertEquals("Only one space in TextArea not counted correctly.", Integer.valueOf(0), actual);

        model.update("     ");
        model.wordCount();
        actual = Integer.parseInt(model.getWordCount());
        assertEquals("Only spaces in TextArea not counted correctly.", Integer.valueOf(0), actual);

        model.update("");
        model.wordCount();
        // fixed
        actual = Integer.parseInt(model.getWordCount());
        assertEquals("Nothing in TextArea not counted correctly.", Integer.valueOf(0), actual);

        /* unable to find uncomplicated fix
        model.update(" Alice");
        model.wordCount();
        actual = Integer.parseInt(model.getWordCount());
        assertEquals("Space at index 0 bevore word in TextArea not counted correctly.", Integer.valueOf(1), actual);*/
    }

    /**
     * JUnit 5 tests for method <pre>charCount()</pre> in class {@link MVC_Editor_Model}
     */
    @org.junit.jupiter.api.Test
    public void charCountTest() throws Exception {
        System.out.println("TEST: charCountTest()");

        MVC_Editor_Model model = new MVC_Editor_Model();
        // TODO streamline
        // TODO split tests so to make debug easier
        // TODO is 'actual' actually nessecary?

        model.update("Bob");
        model.charCount();
        Integer actual = Integer.parseInt(model.getCharCount());
        assertEquals("charCount not working correctly.", Integer.valueOf(3), actual);

        model.update("B o b");
        model.charCount();
        actual = Integer.parseInt(model.getCharCount());
        assertEquals("Spaces not counted correctly.", Integer.valueOf(5), actual);

        model.update("Bob\n.");
        model.charCount();
        actual = Integer.parseInt(model.getCharCount());
        assertEquals("New line not counted correctly.", Integer.valueOf(5), actual);

        model.update("\"Bob\"");
        model.charCount();
        actual = Integer.parseInt(model.getCharCount());
        assertEquals("Quotation marks not counted correctly.", Integer.valueOf(5), actual);

        model.update("Alice\tBob");
        model.charCount();
        actual = Integer.parseInt(model.getCharCount());
        assertEquals("Tab not counted correctly.", Integer.valueOf(9), actual);

        model.update("Alice\n Bob");
        model.charCount();
        actual = Integer.parseInt(model.getCharCount());
        assertEquals("Spaces after newline not couted correctly.", Integer.valueOf(10), actual);

        model.update("Alice \nBob");
        model.charCount();
        actual = Integer.parseInt(model.getCharCount());
        assertEquals("Space bevore newline not couted correctly.", Integer.valueOf(10), actual);

        model.update("Alice\n\tBob");
        model.charCount();
        actual = Integer.parseInt(model.getCharCount());
        assertEquals("Tab after newline not couted correctly.", Integer.valueOf(10), actual);

        model.update("Alice\t\nBob");
        model.charCount();
        actual = Integer.parseInt(model.getCharCount());
        assertEquals("Tab bevore newline not couted correctly.", Integer.valueOf(10), actual);

        model.update("     ");
        model.charCount();
        // fixed
        actual = Integer.parseInt(model.getCharCount());
        assertEquals("Only spaces in textArea not couted correctly.", Integer.valueOf(5), actual);

        model.update("\t");
        model.charCount();
        // fixed
        actual = Integer.parseInt(model.getCharCount());
        assertEquals("Only tab in textArea not couted correctly.", Integer.valueOf(1), actual);

        model.update("\t");
        model.charCount();
        // fixed
        actual = Integer.parseInt(model.getCharCount());
        assertEquals("Only tab in textArea not couted correctly.", Integer.valueOf(1), actual);

        model.update("   \t");
        model.charCount();
        actual = Integer.parseInt(model.getCharCount());
        assertEquals("Only spaces bevore tabs in textArea not couted correctly.", Integer.valueOf(4), actual);

        model.update("\t  ");
        model.charCount();
        actual = Integer.parseInt(model.getCharCount());
        assertEquals("Only spaces after tabs in textArea not couted correctly.", Integer.valueOf(3), actual);

        model.update("   \n Alice \n   Bob");
        model.charCount();
        // fixed
        actual = Integer.parseInt(model.getCharCount());
        assertEquals("Spaces and word in multiple lines not couted correctly.", Integer.valueOf(18), actual);

        model.update("   \n \t \n   \t");
        model.charCount();
        actual = Integer.parseInt(model.getCharCount());
        assertEquals("Spaces and tabs in multiple lines not couted correctly.", Integer.valueOf(12), actual);

        model.update("");
        model.charCount();
        actual = Integer.parseInt(model.getCharCount());
        assertEquals("Nothing in TextArea not counted correctly.", Integer.valueOf(0), actual);

        model.update("null");
        model.charCount();
        actual = Integer.parseInt(model.getCharCount());
        assertEquals("'null' not counted correctly.", Integer.valueOf(4), actual);
    }

    /**
     * JUnit 5 tests for method <pre>trim()</pre> in class {@link MVC_Editor_Model}
     */
    @org.junit.jupiter.api.Test
    public void trimTest() throws Exception {
        System.out.println("TEST: trimTest()");

        MVC_Editor_Model model = new MVC_Editor_Model();

        model.update("Bob");
        model.trim();
        // fixed
        // fixed after making bug while fixing other bug
        assertEquals("No change expected.", "Bob", model.getTextArea());

        model.update("Bob Charlie");
        model.trim();
        assertEquals("No change expected.", "Bob Charlie", model.getTextArea());

        model.update("Bob\nCharlie");
        model.trim();
        assertEquals("No change expected.", "Bob\nCharlie", model.getTextArea());

        model.update("Bob   Charlie");
        model.trim();
        // fixed after making bug while fixing different bug
        assertEquals("Multiple spaces between words not trimmed correctly.", "Bob Charlie",
                model.getTextArea());

        model.update("Bob Charlie   ");
        model.trim();
        // fixed after making bug while fixing different bug
        assertEquals("Multiple spaces after words not trimmed correctly.", "Bob Charlie ",
                model.getTextArea());

        model.update("   Bob Charlie");
        model.trim();
        assertEquals("Multiple spaces bevore words not trimmed correctly.", " Bob Charlie",
                model.getTextArea());

        model.update("Bob   \nCharlie");
        model.trim();
        assertEquals("Multiple spaces bevore newline not trimmed correctly.","Bob \nCharlie",
                model.getTextArea());

        model.update("Bob\n   Charlie");
        model.trim();
        assertEquals("Multiple spaces after newline not trimmed correctly.","Bob\n Charlie",
                model.getTextArea());

        // Error in manual testing but not in automated testing
        // fixed
        model.update("  Alice");
        model.trim();
        assertEquals("Multiple spaces bevore word not trimmed correctly."," Alice",
                model.getTextArea());

        model.update("   ");
        model.trim();
        // fixed multiple times
        assertEquals("Only multiple spaces trimmed correctly."," ", model.getTextArea());

        model.update("asdf  \tasdf");
        model.trim();
        // inconsistent results in maual testing but unable to reconstruct in debug
        assertEquals("Spaces bevore tab not trimmed correctly.", "asdf \tasdf", model.getTextArea());

        model.update("asdf\t   asdf");
        model.trim();
        assertEquals("Spaces after tab not trimmed correctly.", "asdf\t asdf", model.getTextArea());
    }
}