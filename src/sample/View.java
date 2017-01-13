package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class View extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        VBox root = new VBox();
        primaryStage.setTitle("Hello World");

        MenuButton dropdownFile = new MenuButton("Datei");
        dropdownFile.getItems().addAll(
                new MenuItem("Öffnen"),
                new MenuItem("Speichern")
        );
        ToolBar toolBar = new ToolBar(
            dropdownFile
        );
        root.getChildren().add(toolBar);

        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}