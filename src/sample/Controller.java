package sample;

import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Created by patryk on 21.01.17.
 */

public class Controller {

    private Model model;

    public Controller(Model model){
        this.model = model;
    }

    public void onOpen(Stage primaryStage, FileChooser fileChooser){

        String selectedDirectory = fileChooser.showOpenDialog(primaryStage).getAbsolutePath();

        model.openFile(selectedDirectory);
    }

    public void onSave(Stage primaryStage, DirectoryChooser directoryChooser){
        // TODO let user name file?
        String selectedDirectory = directoryChooser.showDialog(primaryStage).getAbsolutePath();
        String os = System.getProperty("os.name");
        System.out.println(os);
        if (os == "Windows") {
            selectedDirectory = selectedDirectory + "\\File.txt";
        } else {
            selectedDirectory = selectedDirectory + "/File.txt";
        }
        System.out.println(selectedDirectory);
        System.out.println(selectedDirectory + "| - savePath");

        model.saveFile(selectedDirectory);
    }

    public void onTrim(){
        model.trim();
    }

    public void onChange(String text){
        model.updateTextArea(text);
        model.wordCount();
        model.charCount();
    }
}
