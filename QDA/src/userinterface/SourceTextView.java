/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import it.cnr.imaa.essi.lablib.gui.checkboxtree.CheckboxTree;

/**
 *
 * @author Brittany Nkounkou
 */
public class SourceTextView extends View {
    
    private CheckboxTree tagsTree;
    
    public SourceTextView(String t, CheckboxTree tags) {
        super(t);
        tagsTree = tags;
    }
    
    public CheckboxTree getTagsTree() {
        return tagsTree;
    }
}
