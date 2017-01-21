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

    private TextArea textArea = new TextArea();
    private Label wordCount = new Label();
    private Label charCount = new Label();

    public TextArea getTextArea() {
        return textArea;
    }

    public void setTextArea(String text) {
        this.textArea.setText(text);
    }

    public void setWordCount(Integer wordCount) {
        this.wordCount.setText(wordCount.toString());
    }

    public void setCharCount(Integer charCount) {
        this.charCount.setText(charCount.toString());
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        //// UI ////

        Model model = new Model();
        Controller controller = new Controller(model, this);

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

        this.wordCount = new Label("0");
        Separator separator = new Separator();
        this.charCount = new Label("0");

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

        this.textArea = new TextArea();
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

        //// ////



        //// STAGESETUP ////

        primaryStage.setTitle("Editor");
        primaryStage.setScene(new Scene(root, 600, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}