/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import checkboxtree.TreeCheckingModel;
import model.MarkedUpText;

/**
 *
 * @author Brittany Nkounkou
 */
public class SourceTextView extends View {
    
    private MarkedUpText markedUpText;
    private TreeCheckingModel tagsModel;
    
    public SourceTextView(MarkedUpText mut, TreeCheckingModel tm) {
        super(mut.getSourceText().getText());
        markedUpText = mut;
        tagsModel = tm;
    }
    
    public MarkedUpText getMarkedUpText() {
        return markedUpText;
    }
    
    public TreeCheckingModel getTagsModel() {
        return tagsModel;
    }
}
