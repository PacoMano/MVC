package sample;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.*;
import java.util.Scanner;

/**
 * Created by patryk on 21.01.17.
 */

public class MVC_Editor_Model {


    //// ATTRIBUTES ////

    private TextArea textArea= new TextArea();
    private Label wordCount = new Label();
    private Label charCount = new Label();
    // TODO order wordCountTest and charCountTest


    //// GETTER ////

    public TextArea getTextArea() {
        // used in tests and listeners
        return this.textArea;
    }

    public Label getWordCount() {
        // used in tests and listeners
        return this.wordCount;
    }

    public Label getCharCount() {
        // used in tests and listeners
        return this.charCount;
    }


    //// METHODS ////

    public void updateTextArea(String text) {
        // ubdates textArea so other Methods can read latest content directly from Attribute
        this.textArea.setText(text);
    }

    public void openFile(String path){
        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter("\\r");
            // makes scanner read whole file instead of only one line
            this.textArea.setText(scanner.next());
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveFile(String path){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            bw.write(this.textArea.getText());
            System.out.println("Saved to " + path );
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void trim() {
        StringBuilder text = new StringBuilder(this.getTextArea().getText());
        // StringBuilder to make text mutable
        for (int i = 0; i < text.length() - 1; i++) {
            if (text.substring(i, i + 2).equals("  ")) {
                text.deleteCharAt(i);
                i--;
                // check this index again b/c char at this index potentially changed
            }
        }
        this.textArea.setText(text.toString());
    }

    public void charCount(){
        this.charCount.setText(Integer.toString(this.getTextArea().getText().length()));
    }

    public void wordCount(){
        String text = this.getTextArea().getText();

        if (text.equals("")) {
            // otherwise no input is one word
            this.wordCount.setText("0");
        } else {
            this.wordCount.setText(Integer.toString(text.split("(\\W+)").length));
        }
    }
}
