package sample;

import com.sun.org.apache.xpath.internal.operations.Bool;
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
        Boolean flag = new Boolean(true);
        Boolean notOnlySpaces = new Boolean(false);
        for (int i = 0; i < text.length() - 1; i++) {
            String cur = new String(text.substring(i, i + 2));
            if (! cur.equals("  ") &&! cur.equals(" \n") &&! cur.equals("\n ")) {
                notOnlySpaces = true;
            }
            if (cur.equals("  ")) {
                this.textArea.setText(text.deleteCharAt(i).toString());
                i = 0;
                flag = false;
            } else if (cur.equals(" .")) {
                this.textArea.setText(text.deleteCharAt(i).toString());
                i = 0;
                flag = false;
            }
        }
        if (flag && notOnlySpaces) {
            this.textArea.setText(text.toString());
        } else if (notOnlySpaces == false){
            this.textArea.setText("");
        }
    }

    public void charCount(String text){
        this.charCount.setText(Integer.toString(text.length()));
    }

    public void wordCount(String text){
        this.wordCount.setText(Integer.toString(text.split("(\\W+)").length));
    }
}
