/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import java.awt.Component;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;
import model.Folder;

/**
 * This class is used to make empty folders in the repository look like folders rather than documents.
 * @author Brittany Nkounkou
 */
public class SpecialTreeCellRenderer extends DefaultTreeCellRenderer{
    /**
     * Ensures that if the tree node is a leaf and folder, then the open folder icon is used.
     */
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value,
						  boolean sel,
						  boolean expanded,
						  boolean leaf, int row,
						  boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
	
        if (leaf && value instanceof Folder && tree.isEnabled()) {
            setIcon(getOpenIcon());
        }
	return this;
    }
}
