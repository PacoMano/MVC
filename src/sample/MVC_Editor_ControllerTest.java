package sample;

import static org.junit.Assert.assertEquals;

import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


/**
 * Created by patryk on
 */
class MVC_Editor_ControllerTest {

    @org.junit.jupiter.api.Test
    public void onChangeTest() {
        System.out.println("TEST: onChange()");

        MVC_Editor_Model model = new MVC_Editor_Model();
        MVC_Editor_Controller controller = new MVC_Editor_Controller(model);

        controller.onChange("Lorem ipsum");
        assertEquals("Lorem ipsum", model.getTextArea());
    }

    @org.junit.jupiter.api.Test
    public void onSaveTest() {
        // individual tests for onSave() and onOpen() b/c saveFile() and openFile() are already tested

        System.out.println("TEST: onSave()");

        MVC_Editor_Model model = new MVC_Editor_Model();
        MVC_Editor_Controller controller = new MVC_Editor_Controller(model);

        String path = new File("").getAbsolutePath() + "/onSaveTest.txt";

        controller.onChange("Lorem \nipsum");
        controller.onSave(path);
        controller.onChange("");
        model.openFile(path);
        assertEquals("Lorem \nipsum", model.getTextArea());

        try {
            Files.delete(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @org.junit.jupiter.api.Test
    public void onOpenTest() {
        // individual tests for onSave() and onOpen() b/c saveFile() and openFile() are already tested

        System.out.println("TEST: onOpen()");

        MVC_Editor_Model model = new MVC_Editor_Model();
        MVC_Editor_Controller controller = new MVC_Editor_Controller(model);

        String path = new File("").getAbsolutePath() + "/onOpenTest.txt";

        controller.onChange("Lorem \nipsum\n");
        controller.onSave(path);
        controller.onChange("");
        controller.onOpen(path);
        assertEquals("Lorem \nipsum\n", model.getTextArea());

        try {
            Files.delete(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


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