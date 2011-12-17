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
 * This class is virtually abstract by its protected constructor.
 * It provides a template for all 'pop-up' dialogs in the application.
 * @author Brittany Nkounkou
 */
public class AppDialog extends javax.swing.JDialog{
    /**
     * The main panel in which content is displayed.
     */
    protected JPanel panel;
    
    /**
     * Stores whether or not valid inputs are available.
     */
    protected boolean hasResults;
    
    /**
     * Creates a new AppDialog template for all dialogs in the application, with null Object[] args.
     * @param mf owning window
     * @param title frame title
     */
    protected AppDialog(MainFrame mf, String title) {
        this(mf, title, null);
    }
    
    /**
     * Creates a new AppDialog template for all dialogs in the application, with null Object[] args.
     * @param mf owning window
     * @param title frame title
     * @param args arguments for initComponents()
     */
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
    
    /**
     * Initializes the components of an AppDialog.
     * @param args unused
     */
    protected void initComponents(Object[] args) {
        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.add(panel);
        this.setResizable(false);
    }
    
    /**
     * Gets whether not this dialog has valid results to return after it has been closed.
     * @return whether or not this dialog has valid results
     */
    public boolean hasResults() {
        return hasResults;
    }
}
