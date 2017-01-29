package sample;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

/**
 * Created by patryk on 21.01.17.
 */

public class Model {


    //// ATTRIBUTES ////

    private TextArea textArea= new TextArea();
    private Label wordCount = new Label();
    private Label charCount = new Label();


    //// GETTER ////

    public TextArea getTextArea() {
        // used in tests and listeners
        return this.textArea;
    }

    public Label getWordCount(){
        // used in tests and listeners
        return this.wordCount;
    }

    public Label getCharCount(){
        // used in tests and listeners
        return this.charCount;
    }


    //// METHODS ////

    public void updateTextArea(String text) {
        this.textArea.setText(text);
    }

    public void openFile(String path){

    }

    public void saveFile(String path){

    }

    public void trim() {
        Boolean flag = Boolean.TRUE;
        Boolean notOnlySpaces = Boolean.FALSE;
        StringBuilder text = new StringBuilder(this.getTextArea().getText());
        for (int i = 0; i < text.length() - 1; i++) {
            // TODO ASK overengeneered?
            // TODO tabs if overengeneered == false
            String cur = text.substring(i, i + 2);
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
        } else if (!notOnlySpaces){
            this.textArea.setText("");
        }
    }

    public void charCount(){
        String text = this.getTextArea().getText();
        this.charCount.setText(Integer.toString(text.length()));
    }

    public void wordCount(){
        String text = this.getTextArea().getText();
        if (text.equals("")) {
            // otherwise no input is one word
            this.wordCount.setText("0");
        } else {
            // TODO which one?
            // this.wordCount.setText(Integer.toString(text.split("([ \\t\\n]+)").length));
            this.wordCount.setText(Integer.toString(text.split("(\\W+)").length));
        }
    }
}
