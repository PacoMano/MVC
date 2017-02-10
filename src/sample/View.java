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
import sun.java2d.cmm.ColorTransform;

/**
 * Created by patryk on 21.01.17.
 */
public class View extends javafx.application.Application{

    // TODO rename to MVC-Editor (Model, Controller, View)

    private TextArea textArea = new TextArea();
    private Label wordCount = new Label();
    private Label charCount = new Label();

    @Override
    public void start(Stage primaryStage) throws Exception{


        //// INITIALIZATION ////

        Model model = new Model();
        Controller controller = new Controller(model);
        ModelTest modelTest = new ModelTest();
        ControllerTest controllerTest = new ControllerTest();


        //// TESTS ////

        modelTest.updateTextArea();
        modelTest.charCount();
        modelTest.wordCount();
        modelTest.trim();
        modelTest.saveAndOpen();

        controllerTest.onChangeTest();
        controllerTest.onSaveAndOnOpenTest();
        controllerTest.onTrimTest();

        System.out.println("Testing finished successfully");


        //// UI ////

        VBox root = new VBox();

        /*MenuButton dropdownFile = new MenuButton("Datei");
        MenuItem menuOpen = new MenuItem("Öffnen");
        MenuItem menuSave = new MenuItem("Speichern");

        dropdownFile.getItems().addAll(
                menuOpen,
                menuSave
        );*/

        Menu menuFile = new Menu("File");

        MenuItem menuOpen = new MenuItem("Open");
        MenuItem menuSave = new MenuItem("Save");

        menuFile.getItems().addAll(menuOpen, menuSave);

        MenuBar menuBar = new MenuBar(menuFile);

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

        root.getChildren().add(menuBar);
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

        menuOpen.setOnAction(event -> {
            String selectedDirectory = fileChooser.showOpenDialog(primaryStage).getAbsolutePath();
            controller.onOpen(selectedDirectory);
        });

        menuSave.setOnAction(event -> {
            String selectedDirectory = directoryChooser.showDialog(primaryStage).getAbsolutePath();
            selectedDirectory = selectedDirectory + "/File.txt";
            // TODO let user name file?
            System.out.println("Saving to " + selectedDirectory);
            controller.onSave(selectedDirectory);
        });

        trim.setOnAction(event -> controller.onTrim());


        //// STAGESETUP ////

        primaryStage.setTitle("Editor");
        primaryStage.setScene(new Scene(root, 600, 500));
        // Minimal reasonable window size
        primaryStage.setMinHeight(200);
        primaryStage.setMinWidth(300);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}