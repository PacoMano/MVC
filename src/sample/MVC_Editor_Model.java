package sample;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.*;
import java.util.Scanner;

/**
 * Processes text from {@link sample.MVC_Editor_View} and saves results in Attributes
 *
 * @author patry
 */

public class MVC_Editor_Model {


    //// ATTRIBUTES ////

    private String textArea= new String();
    private String wordCount = new String();
    private String charCount = new String();
    // TODO order wordCountTest and charCountTest


    //// GETTER & SETTER////

    /**
     * Returns attribute <pre>textArea</pre>
     *
     * @return this.textArea
     */
    public String getTextArea() {
        // used in tests and listeners
        return this.textArea;
    }

    /**
     * Returns attribute <pre>wordCount</pre>
     *
     * @return this.wordCount
     */
    public String getWordCount() {
        // used in tests and listeners
        return this.wordCount;
    }

    /**
     * Returns attribute <pre>charCount</pre>
     *
     * @return this.charCount
     */
    public String getCharCount() {
        // used in tests and listeners
        return this.charCount;
    }

    /**
     * Sets attribute <pre>textArea</pre> to parameter <pre>text</pre>
     *
     * @param text
     */
    public void setTextArea(String text) {
        // ubdates textArea so other Methods can read latest content directly from Attribute
        this.textArea = text;
    }


    //// METHODS ////

    /**
     * Reads content from file with path <pre>path</pre> and saves it to attribute <pre>textArea</pre>
     *
     * @param path
     * @exception FileNotFoundException
     */
    public void openFile(String path){
        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter("\\r");
            // makes scanner read whole file instead of only one line
            this.textArea = scanner.next();
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a new file at path <pre>path</pre> then reads content from attribute <pre>textArea</pre> and saves it
     * to just created file
     *
     * @param path
     */
    public void saveFile(String path){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            bw.write(this.textArea);
            System.out.println("Saved to " + path );
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Modifies content from attribute <pre>textArea</pre> so there are never multiple spaces directly next to one
     * another
     */
    public void trim() {
        StringBuilder text = new StringBuilder(this.getTextArea());
        // StringBuilder to make text mutable
        // TODO StringBuilder nicht direkt <<mutable string>>
        // TODO auch in protokoll
        for (int i = 0; i < text.length() - 1; i++) {
            if (text.substring(i, i + 2).equals("  ")) {
                text.deleteCharAt(i);
                i--;
                // check this index again b/c char at this index potentially changed
            }
        }
        this.textArea = text.toString();
    }

    /**
     * Counts chars in attribute <pre>textArea</pre> and saves result to attribute <pre>wordCount</pre>
     */
    public void charCount(){
        this.charCount = Integer.toString(this.getTextArea().length());
    }

    /**
     * Counts words in attribute <pre>textArea</pre> and saves result to attribute <pre>wordCount</pre>
     */
    public void wordCount(){
        String text = this.getTextArea();

        if (text.equals("")) {
            // otherwise no input is one word
            this.wordCount = "0";
        } else {
            this.wordCount = Integer.toString(text.split("(\\W+)").length);
        }
    }
}
