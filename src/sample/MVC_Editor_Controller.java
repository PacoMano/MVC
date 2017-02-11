package sample;

/**
 * Created by patryk on 21.01.17.
 */

public class MVC_Editor_Controller {

    private MVC_Editor_Model model;

    public MVC_Editor_Controller(MVC_Editor_Model model){
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
