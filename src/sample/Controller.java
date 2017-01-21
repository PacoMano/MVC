package sample;

import javafx.scene.control.TextArea;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.File;

public class Controller {

    private Model model;

    public Controller(Model model){
        this.model = model;
    }

    public void onOpen(Stage primaryStage, FileChooser fileChooser){

        fileChooser.showOpenDialog(primaryStage);
        File selectedDirectory = fileChooser.showOpenDialog(primaryStage);
        System.out.println(selectedDirectory);

        model.openFile(selectedDirectory);
    }

    public void onSave(Stage primaryStage, DirectoryChooser directoryChooser){

        directoryChooser.showDialog(primaryStage);
        File selectedDirectory = directoryChooser.showDialog(primaryStage);
        System.out.println(selectedDirectory);

        model.saveFile(selectedDirectory);
    }

    public void onTrim(TextArea textArea){
        model.trim(textArea);
    }

    public void onKeyPress(TextArea textArea){
        model.refreshTools(textArea);
    }
}
