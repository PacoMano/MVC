package Editor;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Controlls communication from {@link EditorView} to {@link EditorModel}
 *
 * @author dqi15bierzynski
 */
public class EditorController {

    private EditorModel model;

    /**
     * Constructor initialising attribute <pre>model</pre> with parameter <pre>model</pre>
     *
     * @param model {@link EditorModel}
     */
    public EditorController(EditorModel model){
        this.model = model;
    }

    /**
     * Executes method <pre>openFile(selectedDirectory)</pre> in {@link EditorModel} with parameter
     * <pre>selectedDirectory</pre>
     *
     * @param selectedDirectory Parameter for openFile()
     */
    public void onOpen(String selectedDirectory) throws FileNotFoundException{
        model.openFile(selectedDirectory);
    }

    /**
     * Executes method <pre>saveFile(selectedDirectory)</pre> in {@link EditorModel} with parameter
     * <pre>selectedDirectory</pre>
     *
     * @param selectedDirectory Parameter for openFile()
     */
    public void onSave(String selectedDirectory) throws IOException {
        model.saveFile(selectedDirectory);
    }

    /**
     * Executes method <pre>trim()</pre> in {@link EditorModel}
     */
    public void onTrim(){
        model.trim();
    }

    /**
     * Executes method <pre>update(text)</pre> with parameter <pre>text</pre> in {@link EditorModel}
     *
     * @param text Parameter for update()
     */
    public void onChange(String text){
        model.update(text);
    }

    /**
     * Returns <pre>getTextArea()</pre> from {@link EditorModel}
     *
     * @return getTextArea()
     */
    public String updateTextArea() {
        return model.getTextArea();
    }

    /**
     * Returns <pre>getWordCount</pre> from {@link EditorModel}
     *
     * @return getWordCount
     */
    public String updateWordCountLabel() {
        return model.getWordCount();
    }

    /**
     * Returns <pre>getCharCount</pre> from {@link EditorModel}
     *
     * @return getCharCount
     */
    public String updateCharCountLabel() {
        return model.getCharCount();
    }
}
