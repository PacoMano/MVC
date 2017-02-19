package Editor;

import java.io.*;
import java.util.Scanner;

/**
 * Processes text from {@link EditorView} and saves results in Attributes
 *
 * @author dqi15bierzynski
 */

public class EditorModel {


    ///// ATTRIBUTES /////

    private String textArea = new String("");
    //                  ... = new String("") to prevent NullPointerException
    private String wordCount;
    private String charCount;


    ///// GETTER & SETTER/////

    /**
     * Returns attribute <pre>textArea</pre>
     *
     * @return this.textArea
     */
    public String getTextArea() {
        // used in tests and to return attribute to view (controller)
        return this.textArea;
    }

    /**
     * Returns attribute <pre>wordCount</pre>
     *
     * @return this.wordCount
     */
    public String getWordCount() {
        // used in tests and to return attribute to view (controller)
        return this.wordCount;
    }

    /**
     * Returns attribute <pre>charCount</pre>
     *
     * @return this.charCount
     */
    public String getCharCount() {
        // used in tests and to return attribute to view (controller)
        return this.charCount;


    ///// METHODS /////

    }

    /**
     * Sets attribute <pre>textArea</pre> to parameter <pre>text</pre> and executes private Methods
     * <pre>wordCount()</pre> and <pre>charCount()</pre>
     *
     * @param text content from <pre>textArea</pre> in {@link EditorView}
     */
    public void update(String text) {
        // updates textArea so other Methods can read latest content directly from Attribute
        this.textArea = text;
        // executes wordCount() and charCount() b/c textArea just changed
        wordCount();
        charCount();
    }

    /**
     * Reads content from file with path <pre>path</pre> and saves it to attribute <pre>textArea</pre>
     *
     * @param path path to file
     * @exception FileNotFoundException
     */
    public void openFile(String path) throws FileNotFoundException{
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        scanner.useDelimiter("\\r");
        // makes scanner read whole file instead of only one line
        this.textArea = scanner.next();
        scanner.close();
    }

    /**
     * Creates a new file at path <pre>path</pre> then reads content from attribute <pre>textArea</pre> and saves it
     * to just created file
     *
     * @param path path to file
     * @exception IOException
     */
    public void saveFile(String path) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            bw.write(this.textArea);
            System.out.println("Saved to " + path );
            bw.close();
        }
    }

    /**
     * Modifies content from attribute <pre>textArea</pre> so there are never multiple spaces directly next to one
     * another
     */
    public void trim() {
        if (this.textArea.length() > 1) {
            // trim does not make sense if textArea shorter than 2 characters
            StringBuilder text = new StringBuilder(this.textArea);
            // StringBuilder b/c it's more efficient when deleting a single char multiple times
            // TODO StringBuilder nicht direkt <<mutable string>>
            // TODO auch in protokoll
            for (int i = 0; i < this.textArea.length() - 1; i++) {
                if (this.textArea.substring(i, i + 2).equals("  ")) {
                    text.deleteCharAt(i);
                    i--;
                    // check this index again b/c char at this index potentially changed
                }
            }
            this.textArea = text.toString();
        }
    }

    /**
     * Counts words in attribute <pre>textArea</pre> and saves result to attribute <pre>wordCount</pre>
     */
    private void wordCount(){
        String text = this.getTextArea();

        if (text.equals("")) {
            // otherwise no input is one word
            this.wordCount = "0";
        } else {
            this.wordCount = Integer.toString(text.split("(\\W+)").length);
        }
    }

    /**
     * Counts chars in attribute <pre>textArea</pre> and saves result to attribute <pre>wordCount</pre>
     */
    private void charCount(){
        this.charCount = Integer.toString(this.getTextArea().length());
    }
}

