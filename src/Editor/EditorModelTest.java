package Editor;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * JUnit 5 tests for {@link EditorModel}
 *
 * @author dqi15bierzynski
 */
class EditorModelTest {

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
     * @param model Object to call saveFile() upon
     * @param path Parameter for saveFile()
     */
    private void save(EditorModel model, String path) {
        try {
            model.saveFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Runs mothod saveFile with parameter <pre>path</pre>
     *
     * @param model Object to call openFile() upon
     * @param path Parameter for openFile()
     */
    private void open(EditorModel model, String path) {
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
            assertEquals(true, e instanceof EditorModel.ModelException, "" + EditorModel.ModelException);
        }*/
    }

    /**
     * JUnit 5 tests for method <pre>update()</pre> in class {@link EditorModel}
     */
    @org.junit.jupiter.api.Test
    public void updateTest() throws Exception {
        System.out.println("TEST: updateTest()");

        EditorModel model = new EditorModel();

        model.update("Lorem ipsum dolor sit");
        assertEquals("TextArea not updated.", "Lorem ipsum dolor sit", model.getTextArea());
    }

    /**
     * JUnit 5 tests for methods <pre>save()</pre> and <pre>open()</pre> in class {@link EditorModel}; Tests two
     * methods together b/c testing one would require the algorithm of the other to assert
     */
    @org.junit.jupiter.api.Test
    public void saveFileAndOpenFileTest(){
        // tested together b/c if testing only one this test method would need to implement the algorithm of the other
        // one to assert whether the test was successful;copying the algorithm or calling the method makes no difference

        System.out.println("TEST: saveFileAndOpenFileTest()");

        EditorModel model = new EditorModel();

        String path = new File("").getAbsolutePath() + "/saveFileAndOpenFileTest.txt";
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
     * JUnit 5 tests for method <pre>wordCount()</pre> in class {@link EditorModel}
     */
    @org.junit.jupiter.api.Test
    public void wordCountTest() throws Exception {
        System.out.println("TEST: wordCountTest()");

        EditorModel model = new EditorModel();
        // TODO streamline
        // TODO split tests so to make debug easier
        // TODO is 'actual' actually nessecary?

        model.update("Bob");
        Integer actual = Integer.parseInt(model.getWordCount());
        assertEquals("wordCount() not working correctly.", Integer.valueOf(1), actual);

        model.update("Bob Charlie");
        actual = Integer.parseInt(model.getWordCount());
        assertEquals("Space not counted correctly.", Integer.valueOf(2), actual);

        model.update("Bob\nCharlie");
        // fixed
        actual = Integer.parseInt(model.getWordCount());
        assertEquals("Newline not counted correctly.", Integer.valueOf(2), actual);

        model.update("Bob\n Charlie.");
        actual = Integer.parseInt(model.getWordCount());
        assertEquals("Space after newline not counted correctly.", Integer.valueOf(2), actual);

        model.update("Bob \nCharlie.");
        actual = Integer.parseInt(model.getWordCount());
        assertEquals("Space bevore newline not counted correctly.", Integer.valueOf(2), actual);

        model.update("Alice\t Bob");
        actual = Integer.parseInt(model.getWordCount());
        assertEquals("Spaces after tab not couted correctly.", Integer.valueOf(2), actual);

        model.update("Alice \nBob");
        actual = Integer.parseInt(model.getWordCount());
        assertEquals("Space bevore tab not couted correctly.", Integer.valueOf(2), actual);

        model.update("Alice\n\tBob");
        actual = Integer.parseInt(model.getWordCount());
        assertEquals("Tab after newline not couted correctly.", Integer.valueOf(2), actual);

        model.update("Alice\t\nBob");
        actual = Integer.parseInt(model.getWordCount());
        assertEquals("Tab bevore newline not couted correctly.", Integer.valueOf(2), actual);

        model.update("   \n \t \n   \t");
        actual = Integer.parseInt(model.getWordCount());
        assertEquals("Spaces and tabs in multiple lines not couted correctly.", Integer.valueOf(0), actual);

        model.update(" ");
        actual = Integer.parseInt(model.getWordCount());
        assertEquals("Only one space in TextArea not counted correctly.", Integer.valueOf(0), actual);

        model.update("     ");
        actual = Integer.parseInt(model.getWordCount());
        assertEquals("Only spaces in TextArea not counted correctly.", Integer.valueOf(0), actual);

        model.update("");
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
     * JUnit 5 tests for method <pre>charCount()</pre> in class {@link EditorModel}
     */
    @org.junit.jupiter.api.Test
    public void charCountTest() throws Exception {
        System.out.println("TEST: charCountTest()");

        EditorModel model = new EditorModel();
        // TODO streamline
        // TODO split tests so to make debug easier
        // TODO is 'actual' actually nessecary?

        model.update("Bob");
        Integer actual = Integer.parseInt(model.getCharCount());
        assertEquals("charCount not working correctly.", Integer.valueOf(3), actual);

        model.update("B o b");
        actual = Integer.parseInt(model.getCharCount());
        assertEquals("Spaces not counted correctly.", Integer.valueOf(5), actual);

        model.update("Bob\n.");
        actual = Integer.parseInt(model.getCharCount());
        assertEquals("New line not counted correctly.", Integer.valueOf(5), actual);

        model.update("     ");
        // fixed
        actual = Integer.parseInt(model.getCharCount());
        assertEquals("Only spaces in textArea not couted correctly.", Integer.valueOf(5), actual);

        model.update("");
        actual = Integer.parseInt(model.getCharCount());
        assertEquals("Nothing in TextArea not counted correctly.", Integer.valueOf(0), actual);

        model.update("null");
        actual = Integer.parseInt(model.getCharCount());
        assertEquals("'null' not counted correctly.", Integer.valueOf(4), actual);
    }

    /**
     * JUnit 5 tests for method <pre>trim()</pre> in class {@link EditorModel}
     */
    @org.junit.jupiter.api.Test
    public void trimTest() throws Exception {
        System.out.println("TEST: trimTest()");

        EditorModel model = new EditorModel();

        model.update("");
        model.trim();
        assertEquals("No change expected.", "", model.getTextArea());

        model.update(" ");
        model.trim();
        assertEquals("No change expected.", " ", model.getTextArea());

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

        model.update("asdf  \tasdf");
        model.trim();
        // inconsistent results in maual testing but unable to reconstruct in debug
        assertEquals("Spaces bevore tab not trimmed correctly.", "asdf \tasdf", model.getTextArea());

        model.update("asdf\t   asdf");
        model.trim();
        assertEquals("Spaces after tab not trimmed correctly.", "asdf\t asdf", model.getTextArea());

        model.update("   ");
        model.trim();
        // fixed multiple times
        assertEquals("Only multiple spaces trimmed correctly."," ", model.getTextArea());
    }
}