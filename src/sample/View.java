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

/**
 * Created by patryk on 21.01.17.
 */

public class View extends javafx.application.Application{

    private TextArea textArea = new TextArea();
    private Label wordCount = new Label();
    private Label charCount = new Label();

    @Override
    public void start(Stage primaryStage) throws Exception{


        //// INITIALIZATION ////

        ModelTest modelTest = new ModelTest();
        Model model = new Model();
        Controller controller = new Controller(model);


        //// TESTS ////

        modelTest.updateTextArea();
        modelTest.charCount();
        modelTest.wordCount();
        modelTest.trim();
        modelTest.saveAndOpen();


        //// UI ////

        VBox root = new VBox();

        MenuButton dropdownFile = new MenuButton("Datei");
        MenuItem menuOpen = new MenuItem("Öffnen");
        MenuItem menuSave = new MenuItem("Speichern");

        dropdownFile.getItems().addAll(
                menuOpen,
                menuSave
        );

        ToolBar fileBar = new ToolBar(
            // TODO make "menubar"
            dropdownFile
        );

        Button trim = new Button("Trim");

        Pane pane = new Pane();
        HBox.setHgrow(pane, Priority.ALWAYS);

        Label wordTag = new Label("Wörter:");
        this.wordCount = new Label("0");
        Separator separator = new Separator();
        Label charTag = new Label("Zeichen:");
        this.charCount = new Label("0");

        ToolBar toolBar = new ToolBar(
                trim,
                pane,
                wordTag,
                wordCount,
                separator,
                charTag,
                charCount
        );

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Datei öffnen:");

        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Speichern unter:");

        this.textArea = new TextArea();
        textArea.setPrefRowCount(25);

        root.getChildren().add(fileBar);
        root.getChildren().add(toolBar);
        root.getChildren().add(textArea);


        //// MODELLISTENERS ////

        model.getTextArea().textProperty().addListener((observable, oldValue, newValue) -> {
            this.textArea.setText(newValue);
        });

        model.getWordCount().textProperty().addListener((observable, oldValue, newValue) -> {
            this.wordCount.setText(newValue);
        });

        model.getCharCount().textProperty().addListener((observable, oldValue, newValue) -> {
            this.charCount.setText(newValue);
        });


        //// EVENTHANDLING ////

        textArea.textProperty().addListener((observable, oldValue, newValue) -> {
            controller.onChange(newValue);
        });

        menuOpen.setOnAction(event -> controller.onOpen(primaryStage, fileChooser));

        menuSave.setOnAction(event -> controller.onSave(primaryStage, directoryChooser));

        trim.setOnAction(event -> controller.onTrim());


        //// STAGESETUP ////

        primaryStage.setTitle("Editor");
        primaryStage.setScene(new Scene(root, 600, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}