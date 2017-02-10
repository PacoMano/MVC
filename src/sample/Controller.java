package sample;

import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Created by patryk on 21.01.17.
 */

public class Controller {

    private Model model;

    public Controller(Model model){
        this.model = model;
    }

    public void onOpen(String selectedDirectory){
        model.openFile(selectedDirectory);
    }

    public void onSave(String selectedDirectory){
        model.saveFile(selectedDirectory);
    }

    public void onTrim(){
        model.trim();
    }

    public void onChange(String text){
        model.updateTextArea(text);
        model.wordCount();
        model.charCount();
    }
}
