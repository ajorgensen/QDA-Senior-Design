/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import it.cnr.imaa.essi.lablib.gui.checkboxtree.CheckboxTree;
import model.SourceText;

/**
 *
 * @author Brittany Nkounkou
 */
public class SourceTextView extends View {
    
    private SourceText sourceText;
    private CheckboxTree tagsTree;
    
    public SourceTextView(SourceText st, CheckboxTree tags) {
        super(st.getText());
        sourceText = st;
        tagsTree = tags;
    }
    
    public SourceText getSourceText() {
        return sourceText;
    }
    
    public CheckboxTree getTagsTree() {
        return tagsTree;
    }
}
