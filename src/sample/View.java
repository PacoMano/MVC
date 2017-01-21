package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class View extends javafx.application.Application{

    @Override
    public void start(Stage primaryStage) throws Exception{

        //// UI ////

        Model model = new Model();
        Controller controller = new Controller(model);

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

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Datei öffnen:");

        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Speichern unter:");

        TextArea textArea = new TextArea();
        textArea.setPrefRowCount(25);

        root.getChildren().add(fileBar);
        root.getChildren().add(toolBar);
        root.getChildren().add(textArea);

        //// EVENTHANDLING ////

        textArea.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                controller.onChange(newValue);
                System.out.println("newValue: " + newValue);
            }
        });

        menuOpen.setOnAction(event ->  {
            controller.onOpen(primaryStage, fileChooser);
        });

        menuSave.setOnAction(event -> {
            controller.onSave(primaryStage, directoryChooser);
        });

        trim.setOnAction(event -> {
            controller.onTrim(textArea.getText());
        });


        //// STAGESETUP ////

        primaryStage.setTitle("Editor");
        primaryStage.setScene(new Scene(root, 600, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}