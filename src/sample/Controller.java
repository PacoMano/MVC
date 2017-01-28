package sample;

import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

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

    public void onTrim(StringBuilder text){
        model.trim(text);
    }

    public void onChange(String text){
        model.wordCount(text);
        model.charCount(text);
    }
}
