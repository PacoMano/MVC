package sample;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Created by patryk on 21.01.17.
 */
public class MVC_Editor_View extends javafx.application.Application{

    @Override
    public void start(Stage primaryStage) throws Exception{


        //// DEFINITION ////

        MVC_Editor_Model model = new MVC_Editor_Model();
        MVC_Editor_Controller controller = new MVC_Editor_Controller(model);
        MVC_Editor_ModelTest modelTest = new MVC_Editor_ModelTest();
        MVC_Editor_ControllerTest controllerTest = new MVC_Editor_ControllerTest();


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

        // MENUBAR //

        Menu menuFile = new Menu("Datei");

        MenuItem menuOpen = new MenuItem("Öffnen");
        MenuItem menuSave = new MenuItem("Speichern");

        menuFile.getItems().addAll(menuOpen, menuSave);

        MenuBar menuBar = new MenuBar(menuFile);

        // TOOLBAR //

        Button trimButton = new Button("Trim");

        Pane growingPane = new Pane();
        // makes pane have dynamic size so labels are arranged to the right
        HBox.setHgrow(growingPane, Priority.ALWAYS);

        Label wordTag = new Label("Wörter:");
        Label wordCountLabel = new Label("0");
        Separator separator = new Separator();
        Label charTag = new Label("Zeichen:");
        Label charCountLabel = new Label("0");

        ToolBar toolBar = new ToolBar(
                trimButton,
                growingPane,
                wordTag,
                wordCountLabel,
                separator,
                charTag,
                charCountLabel
        );

        // EXPLORER //

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Datei öffnen:");

        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Speichern unter:");

        // TEXTAREA //

        TextArea textArea = new TextArea();
        // makes textArea fill free vertical space
        VBox.setVgrow(textArea, Priority.ALWAYS);

        // UI CONSTRUCTION //

        VBox root = new VBox();

        root.getChildren().add(menuBar);
        root.getChildren().add(toolBar);
        root.getChildren().add(textArea);


        //// STAGESETUP ////

        primaryStage.setTitle("MVC-Texteditor");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();


        //// EVENTHANDLING ////

        textArea.textProperty().addListener((observable, oldValue, newValue) -> controller.onChange(newValue));

        menuOpen.setOnAction(event -> {
            String selectedDirectory = fileChooser.showOpenDialog(primaryStage).getAbsolutePath();
            controller.onOpen(selectedDirectory);
        });

        menuSave.setOnAction(event -> {
            String selectedDirectory = directoryChooser.showDialog(primaryStage).getAbsolutePath();
            controller.onSave(selectedDirectory + "/Datei.txt");
        });

        trimButton.setOnAction(event -> controller.onTrim());


        //// MODELLISTENERS ////

        model.getTextArea().textProperty().addListener((observable, oldValue, newValue) -> textArea.setText(newValue));

        model.getWordCount().textProperty().addListener((observable, oldValue, newValue) -> wordCountLabel.setText(newValue));

        model.getCharCount().textProperty().addListener((observable, oldValue, newValue) -> charCountLabel.setText(newValue));
    }
}