/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.*;

/**
 * This class is virtually abstract by its protected constructor.
 * It provides a template for 'tabs' that are added to the views area.
 * Those inheriting classes are HelpView, SourceTextView, and SearchView.
 * @author Brittany Nkounkou
 */
public class View extends javax.swing.JPanel {
    private String title;
    private String abbrv;

    /**
     * Creates a View.
     * @param t tab title
     */
    protected View(String t) {
        setTitle(t);
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());
    }
    
    /**
     * Gets the title of this View.
     * @return this View's title
     */
    public final String getTitle() {
        return title;
    }
    
    /**
     * Sets the title of this View.
     * @param t new title
     */
    public final void setTitle(String t) {
        title = t;
        if (t.length() > 25) {
            abbrv = t.substring(0, 20)+"...  ";
        }
        else {
            abbrv = t+"  ";
        }
    }
    
    /**
     * Gets an abbreviation of the title
     * @return title abbreviation
     */
    public final String getAbbrv() {
        return abbrv;
    }
}
