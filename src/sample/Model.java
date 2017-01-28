package sample;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

/**
 * Created by patryk on 21.01.17.
 */

public class Model {
    private TextArea textArea= new TextArea();
    private Label wordCount = new Label();
    private Label charCount = new Label();

    public TextArea getTextArea() {
        return this.textArea;
    }

    public Label getWordCount(){
        return this.wordCount;
    }

    public Label getCharCount(){
        return this.charCount;
    }

    public void openFile(String path){

    }

    public void saveFile(String path){

    }

    public void trim(StringBuilder text) {
        for (int i = 0; i < text.length() - 1; i++) {
            if (new String(text.substring(i, i + 2)).equals("  ")) {
                this.textArea.setText(text.deleteCharAt(i).toString());
                i = 0;
            }
        }
    }

    public void charCount(String text){
        this.charCount.setText(Integer.toString(text.length()));
    }

    public void wordCount(String text){
        this.wordCount.setText(Integer.toString(text.split("(\\W+)").length));
    }
}
