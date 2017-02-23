package Editor;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Defines {@link EditorController} and {@link EditorModel}, creates UI in scene
 *
 * @author dqi15bierzynski
 */
public class EditorView extends javafx.application.Application{

    /**
     * Defines {@link EditorController} and {@link EditorModel}, constructs UI in <pre>primaryScene</pre>
     * including Eventhandlers for each button and listener for <pre>textArea</pre>; UI constists of
     * <pre>MenuBar</pre>, <pre>ToolBar</pre>, <pre>TextArea</pre>, <pre>FileChooser</pre>, <pre>DirectoryChooser</pre>
     *
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage){

        // TODO StarUML

        // DEFINITION //
        ////////////////

        EditorController controller = new EditorController();


        // UI //
        ////////

        Menu menuFile = new Menu("Datei");

        MenuItem menuOpen = new MenuItem("Öffnen");
        MenuItem menuSave = new MenuItem("Speichern");

        menuFile.getItems().addAll(menuOpen, menuSave);

        MenuBar menuBar = new MenuBar(menuFile);

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

        TextArea textArea = new TextArea();
        // makes textArea fill free vertical space
        VBox.setVgrow(textArea, Priority.ALWAYS);

        VBox root = new VBox();

        root.getChildren().add(menuBar);
        root.getChildren().add(toolBar);
        root.getChildren().add(textArea);

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Datei öffnen:");

        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Speichern unter:");


        // STAGESETUP //
        ////////////////

        primaryStage.setTitle("MVC-Texteditor");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();


        // EVENTHANDLING //
        ///////////////////

        textArea.textProperty().addListener((observable, oldValue, newValue) -> {
            controller.onChange(newValue);
            wordCountLabel.setText(controller.updateWordCountLabel());
            charCountLabel.setText(controller.updateCharCountLabel());
        });

        menuOpen.setOnAction(event -> {
            try {
                String selectedDirectory = fileChooser.showOpenDialog(primaryStage).getAbsolutePath();
                controller.onOpen(selectedDirectory);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            textArea.setText(controller.updateTextArea());
        });

        menuSave.setOnAction(event -> {
            String selectedDirectory = directoryChooser.showDialog(primaryStage).getAbsolutePath();
            try {
                controller.onSave(selectedDirectory + "/Datei.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }

            textArea.setText(controller.updateTextArea());
        });

        trimButton.setOnAction(event -> {
            controller.onTrim();

            textArea.setText(controller.updateTextArea());
        });
    }
}
