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
public class SearchView extends View {
    
    private CheckboxTree filesTree;
    private CheckboxTree tagsTree;
    
    public SearchView(String t, CheckboxTree files, CheckboxTree tags) {
        super(t);
        filesTree = files;
        tagsTree = tags;
    }
    
    public CheckboxTree getFilesTree() {
        return filesTree;
    }
    
    public CheckboxTree getTagsTree() {
        return tagsTree;
    }
}
