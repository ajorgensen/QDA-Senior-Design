/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import javax.swing.JTree;
import javax.swing.tree.TreeSelectionModel;
import model.TagType;

/**
 *
 * @author Brittany Nkounkou
 */
public class AddTagDialog extends AddMarkUpDialog{
    
    private JTree tagTree;
    
    public AddTagDialog(MainFrame mf, String textToMarkUp, JTree tags) {
        super(mf, "Add Tag", "Please select a tag to apply to the following text:", textToMarkUp, "You must select one tag.", tags);
    }
    
    @Override
    protected void initComponents(Object[] args) {
        super.initComponents(args);
        
        tagTree = (JTree) args[2];
        tagTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        
        markUpPane.setViewportView(tagTree);
    }
    
    public TagType getSelectedTagType() {
        return (TagType) tagTree.getSelectionPath().getLastPathComponent();
    }
    
    @Override
    public boolean validInput() {
        return tagTree.getSelectionCount() == 1;
    }
}
