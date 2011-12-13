/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.CompoundBorder;

/**
 *
 * @author Brittany Nkounkou
 */
public class MarkUpDetail extends JScrollPane{
    
    public MarkUpDetail(String text) {
        JTextArea ta = new JTextArea();
        ta.setAlignmentX(Component.CENTER_ALIGNMENT);
        ta.setBackground(new Color(240, 240, 255));
        ta.setLineWrap(true);
        ta.setWrapStyleWord(true);
        ta.setText(text);
        this.setPreferredSize(new Dimension(220, 100));
        //this.setSize(this.getPreferredSize());
        this.setMinimumSize(new Dimension(220, 100));
        this.setMaximumSize(new Dimension(220, 100));
        ta.setEditable(false);
        ta.setFocusable(false);
        this.setViewportView(ta);
    }
}
