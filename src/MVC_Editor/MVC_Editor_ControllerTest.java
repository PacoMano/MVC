package MVC_Editor;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * JUnit 5 tests for method in {@link MVC_Editor_Controller}
 *
 * @author dqi15bierzynski
 */
class MVC_Editor_ControllerTest {

    /**
     * JUnit 5 tests for method <pre>onChange</pre>in {@link MVC_Editor_Controller}
     */
    @org.junit.jupiter.api.Test
    public void onChangeTest() {
        System.out.println("TEST: onChange()");

        MVC_Editor_Model model = new MVC_Editor_Model();
        MVC_Editor_Controller controller = new MVC_Editor_Controller(model);

        controller.onChange("Lorem ipsum");
        assertEquals("Lorem ipsum", model.getTextArea());
        assertEquals("2", model.getWordCount());
        assertEquals("11", model.getCharCount());
    }

    /**
     * JUnit 5 tests for method <pre>onSave</pre>in {@link MVC_Editor_Controller}
     */
    @org.junit.jupiter.api.Test
    public void onSaveTest() {
        // individual tests for onSave() and onOpen() b/c saveFile() and openFile() are already tested

        System.out.println("TEST: onSave()");

        MVC_Editor_Model model = new MVC_Editor_Model();
        MVC_Editor_Controller controller = new MVC_Editor_Controller(model);

        String path = new File("").getAbsolutePath() + "/onSaveTest.txt";

        controller.onChange("Lorem \nipsum");
        try {
            controller.onSave(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        controller.onChange("");
        try {
            model.openFile(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assertEquals("Lorem \nipsum", model.getTextArea());

        try {
            Files.delete(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * JUnit 5 tests for method <pre>onOpen</pre>in {@link MVC_Editor_Controller}
     */
    @org.junit.jupiter.api.Test
    public void onOpenTest() {
        // individual tests for onSave() and onOpen() b/c saveFile() and openFile() are already tested

        System.out.println("TEST: onOpen()");

        MVC_Editor_Model model = new MVC_Editor_Model();
        MVC_Editor_Controller controller = new MVC_Editor_Controller(model);

        String path = new File("").getAbsolutePath() + "/onOpenTest.txt";

        controller.onChange("Lorem \nipsum\n");
        try {
            controller.onSave(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        controller.onChange("");
        try {
            controller.onOpen(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assertEquals("Lorem \nipsum\n", model.getTextArea());

        try {
            Files.delete(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * JUnit 5 tests for method <pre>onTrim</pre>in {@link MVC_Editor_Controller}
     */
    @org.junit.jupiter.api.Test
    public void onTrimTest() {
        System.out.println("TEST: onTrim()");

        MVC_Editor_Model model = new MVC_Editor_Model();
        MVC_Editor_Controller controller = new MVC_Editor_Controller(model);

        controller.onChange("  Lorem  ipsum  ");
        controller.onTrim();
        assertEquals(" Lorem ipsum ", model.getTextArea());
    }
}