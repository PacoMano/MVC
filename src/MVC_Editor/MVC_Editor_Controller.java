package MVC_Editor;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Controlls communication from {@link MVC_Editor_View} to {@link MVC_Editor_Model}
 *
 * @author dqi15bierzynski
 */
public class MVC_Editor_Controller {

    private MVC_Editor_Model model;

    /**
     * Constructor initialising attribute <pre>model</pre> with parameter <pre>model</pre>
     *
     * @param model {@link MVC_Editor_Model}
     */
    public MVC_Editor_Controller(MVC_Editor_Model model){
        this.model = model;
    }

    /**
     * Executes method <pre>openFile(selectedDirectory)</pre> in {@link MVC_Editor_Model} with parameter
     * <pre>selectedDirectory</pre>
     *
     * @param selectedDirectory Parameter for openFile()
     */
    public void onOpen(String selectedDirectory) throws FileNotFoundException{
        model.openFile(selectedDirectory);
    }

    /**
     * Executes method <pre>saveFile(selectedDirectory)</pre> in {@link MVC_Editor_Model} with parameter
     * <pre>selectedDirectory</pre>
     *
     * @param selectedDirectory Parameter for openFile()
     */
    public void onSave(String selectedDirectory) throws IOException {
        model.saveFile(selectedDirectory);
    }

    /**
     * Executes method <pre>trim()</pre> in {@link MVC_Editor.MVC_Editor_Model}
     */
    public void onTrim(){
        model.trim();
    }

    /**
     * Executes method <pre>setTextArea(text)</pre> with parameter <pre>text</pre>, methods <pre>wordCount</pre> and
     * <pre>charCount</pre> in {@link MVC_Editor_Model}
     *
     * @param text Parameter for setTextArea()
     */
    public void onChange(String text){
        model.setTextArea(text);
        model.wordCount();
        model.charCount();
    }

    /**
     * Returns <pre>getTextArea()</pre> from {@link MVC_Editor_Model}
     *
     * @return getTextArea()
     */
    public String updateTextArea() {
        return model.getTextArea();
    }

    /**
     * Returns <pre>getWordCount</pre> from {@link MVC_Editor_Model}
     *
     * @return getWordCount
     */
    public String updateWordCountLabel() {
        return model.getWordCount();
    }

    /**
     * Returns <pre>getCharCount</pre> from {@link MVC_Editor_Model}
     *
     * @return getCharCount
     */
    public String updateCharCountLabel() {
        return model.getCharCount();
    }
}
