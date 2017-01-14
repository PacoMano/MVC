package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;

public class View extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        //arent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        VBox root = new VBox();

        MenuButton dropdownFile = new MenuButton("Datei");
        MenuItem menuOpen = new MenuItem("Öffnen");
        MenuItem menuSave = new MenuItem("Speichern");
        dropdownFile.getItems().addAll(
                menuOpen,
                menuSave
        );
        ToolBar toolBar = new ToolBar(
            dropdownFile
        );
        root.getChildren().add(toolBar);

        Label labelSelectedDirectory = new Label("Dir");
        root.getChildren().add(labelSelectedDirectory);

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Datei öffnen:");

        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Speichern unter:");

        menuOpen.setOnAction(event -> {
            fileChooser.showOpenDialog(primaryStage);
            File selectedDirectory = directoryChooser.showDialog(primaryStage);
            labelSelectedDirectory.setText(selectedDirectory.getAbsolutePath());
        });

        menuSave.setOnAction(event -> {
            directoryChooser.showDialog(primaryStage);
            File selectedDirectory = directoryChooser.showDialog(primaryStage);
            labelSelectedDirectory.setText(selectedDirectory.getAbsolutePath());

        });
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}