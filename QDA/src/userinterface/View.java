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
 *
 * @author Brittany Nkounkou
 */
public class View extends javax.swing.JPanel {
    private String title;
    private String abbrv;

    protected View(String t) {
        setTitle(t);
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());
    }
    
    public final String getTitle() {
        return title;
    }
    
    public final void setTitle(String t) {
        title = t;
        if (t.length() > 25) {
            abbrv = t.substring(0, 20)+"...  ";
        }
        else {
            abbrv = t+"  ";
        }
    }
    
    public final String getAbbrv() {
        return abbrv;
    }
}
