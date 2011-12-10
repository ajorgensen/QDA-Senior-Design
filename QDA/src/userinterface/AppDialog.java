/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author Brittany Nkounkou
 */
public class AppDialog extends javax.swing.JDialog{
    protected JPanel panel;
    protected boolean hasResults;
    
    protected AppDialog(MainFrame mf, String title) {
        this(mf, title, null);
    }
    protected AppDialog(MainFrame mf, String title, Object[] args) {
        super(mf, title, true);
        hasResults = false;
        initComponents(args);
        
        //set preferred size
        pack();
        
        // set location
        int x = ((mf.getWidth()-getWidth())/2)+mf.getX();
        int y = ((mf.getHeight()-getHeight())/2)+mf.getY();
        x = Math.max(0, x);
        y = Math.max(0, y);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        x = Math.min(x, screenSize.width-getWidth());
        y = Math.min(y, screenSize.height-getHeight());
        this.setLocation(x, y);
    }
    
    protected void initComponents(Object[] args) {
        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.add(panel);
        this.setResizable(false);
    }
    
    public boolean hasResults() {
        return hasResults;
    }
}
