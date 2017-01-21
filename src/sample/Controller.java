package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.File;

public class Controller {

    private Model model;
    private View view;

    public Controller(Model model, View view){
        this.model = model;
        this.view = view;
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

    public void onTrim(String text){
        view.setTextArea(model.trim(text));
    }

    public void onChange(String text){
        view.setWordCount(model.getWordCount(text));
        view.setCharCount(model.getCharCount(text));
    }
}
