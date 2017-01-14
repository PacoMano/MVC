package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
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
        //test
        //arent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        VBox root = new VBox();

        MenuButton dropdownFile = new MenuButton("Datei");
        MenuItem menuOpen = new MenuItem("Öffnen");
        MenuItem menuSave = new MenuItem("Speichern");

        dropdownFile.getItems().addAll(
                menuOpen,
                menuSave
        );

        ToolBar fileBar = new ToolBar(
            dropdownFile
        );

        Button trim = new Button("Trim");

        Pane pane = new Pane();
        HBox spring = new HBox(pane);
        HBox.setHgrow(pane, Priority.ALWAYS);

        Label wordCount = new Label("X Wörter");
        Separator separator = new Separator();
        Label charCount = new Label("Y Zeichen");

        ToolBar toolBar = new ToolBar(
                trim,
                pane,
                wordCount,
                separator,
                charCount
        );

        root.getChildren().add(fileBar);
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
        primaryStage.setScene(new Scene(root, 600, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
