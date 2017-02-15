package sample;

/**
 * Controlls communication from {@link sample.MVC_Editor_View} to {@link sample.MVC_Editor_Model}
 *
 * @author patryk
 */

public class MVC_Editor_Controller {

    private MVC_Editor_Model model;

    /**
     * Constructor initialising attribute <pre>model</pre> with parameter <pre>model</pre>
     *
     * @param model
     */
    public MVC_Editor_Controller(MVC_Editor_Model model){
        this.model = model;
    }

    /**
     * Executes method <pre>openFile(selectedDirectory)</pre> in {@link sample.MVC_Editor_Model} with parameter
     * <pre>selectedDirectory</pre>
     *
     * @param selectedDirectory
     */
    public void onOpen(String selectedDirectory){
        model.openFile(selectedDirectory);
    }

    /**
     * Executes method <pre>saveFile(selectedDirectory)</pre> in {@link sample.MVC_Editor_Model} with parameter
     * <pre>selectedDirectory</pre>
     *
     * @param selectedDirectory
     */
    public void onSave(String selectedDirectory){
        model.saveFile(selectedDirectory);
    }

    /**
     * Executes method <pre>trim()</pre> in {@link sample.MVC_Editor_Model}
     */
    public void onTrim(){
        model.trim();
    }

    /**
     * Executes method <pre>setTextArea(text)</pre> with parameter <pre>text</pre>, methods <pre>wordCount</pre> and
     * <pre>charCount</pre> in {@link sample.MVC_Editor_Model}
     *
     * @param text
     */
    public void onChange(String text){
        model.setTextArea(text);
        model.wordCount();
        model.charCount();
    }

    public String updateTextArea() {
        return model.getTextArea();
    }

    public String updateWordCountLabel() {
        return model.getWordCount();
    }

    public String updateCharCountLabel() {
        return model.getCharCount();
    }
}
