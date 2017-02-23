package Editor;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * JUnit 5 tests for method in {@link EditorController}
 *
 * @author dqi15bierzynski
 */
class EditorControllerModelTest {

    /**
     * JUnit 5 tests for method <pre>onChange</pre> in {@link EditorController}
     */
    @org.junit.jupiter.api.Test
    public void onChangeTest() {
        System.out.println("TEST: onChange()");

        EditorModel model = new EditorModel();
        EditorController controller = new EditorController();

        controller.onChange("Lorem ipsum");
        assertEquals("Lorem ipsum", model.getTextArea());
        assertEquals("2", model.getWordCount());
        assertEquals("11", model.getCharCount());
    }

    /**
     * JUnit 5 tests for method <pre>onSave</pre> in {@link EditorController}
     */
    @org.junit.jupiter.api.Test
    public void onSaveTest() {
        // individual tests for onSave() and onOpen() b/c saveFile() and openFile() are already tested

        System.out.println("TEST: onSave()");

        EditorModel model = new EditorModel();
        EditorController controller = new EditorController();

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
     * JUnit 5 tests for method <pre>onOpen</pre> in {@link EditorController}
     */
    @org.junit.jupiter.api.Test
    public void onOpenTest() {
        // individual tests for onSave() and onOpen() b/c saveFile() and openFile() are already tested

        System.out.println("TEST: onOpen()");

        EditorModel model = new EditorModel();
        EditorController controller = new EditorController();

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
     * JUnit 5 tests for method <pre>onTrim</pre> in {@link EditorController}
     */
    @org.junit.jupiter.api.Test
    public void onTrimTest() {
        System.out.println("TEST: onTrim()");

        EditorModel model = new EditorModel();
        EditorController controller = new EditorController();

        controller.onChange("  Lorem  ipsum  ");
        controller.onTrim();
        assertEquals(" Lorem ipsum ", model.getTextArea());
    }
}