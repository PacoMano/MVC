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
        System.out.println(selectedDirectory + " - openPath");

        model.openFile(selectedDirectory);
    }

    public void onSave(Stage primaryStage, DirectoryChooser directoryChooser){

        String selectedDirectory = directoryChooser.showDialog(primaryStage).getAbsolutePath();
        System.out.println(selectedDirectory + " - savePath");

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
