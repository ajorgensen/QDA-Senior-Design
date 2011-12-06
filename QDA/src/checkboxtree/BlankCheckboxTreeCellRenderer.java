/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package checkboxtree;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.Icon;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 *
 * @author Brittany Nkounkou
 */
public class BlankCheckboxTreeCellRenderer extends JPanel implements CheckboxTreeCellRenderer{

    protected DefaultTreeCellRenderer label = new DefaultTreeCellRenderer();
    
    public BlankCheckboxTreeCellRenderer() {
	this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
	this.setOpaque(false);
	add(this.label);
    }
    
    @Override
    public Dimension getPreferredSize() {
	return this.label.getPreferredSize();
    }
    
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        this.label.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
        return this;
    }

    @Override
    public boolean isOnHotspot(int x, int y) {
        return false;
    }
    
    /**
     * Sets the icon used to represent non-leaf nodes that are not expanded.
     */
    public void setClosedIcon(Icon newIcon) {
	this.label.setClosedIcon(newIcon);
    }

    /**
     * Sets the icon used to represent leaf nodes.
     */
    public void setLeafIcon(Icon newIcon) {
	this.label.setLeafIcon(newIcon);
    }

    /**
     * Sets the icon used to represent non-leaf nodes that are expanded.
     */
    public void setOpenIcon(Icon newIcon) {
	this.label.setOpenIcon(newIcon);
    }
}
