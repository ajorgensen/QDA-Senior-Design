/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.*;
import javax.swing.border.CompoundBorder;

/**
 *
 * @author Brittany Nkounkou
 */
public class View extends javax.swing.JScrollPane {
    private String title;
    private String abbrv;
    
    private JPanel panel;
    protected JPanel left;
    protected JPanel center;
    protected JPanel right;
    
    protected View(String t) {
        setTitle(t);
        
        panel = new JPanel();
        panel.setBackground(Color.BLUE);
        panel.setLayout(new java.awt.BorderLayout());
        
        left = new JPanel();
        left.setBackground(new Color(250,250,250));
        left.setPreferredSize(new Dimension(200, 500));
        left.setMinimumSize(new Dimension(200, 500));
        left.setMaximumSize(new Dimension(200, 0));
        left.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(left, BorderLayout.LINE_START);
        
        center = new JPanel();
        center.setBackground(Color.WHITE);
        center.setBorder(new CompoundBorder(BorderFactory.createMatteBorder(0,1,0,1,Color.BLACK),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        center.setPreferredSize(new Dimension(500, 500));
        center.setMinimumSize(new Dimension(500, 500));
        panel.add(center, BorderLayout.CENTER);

        right = new JPanel();
        right.setBackground(new Color(250,250,250));
        right.setPreferredSize(new Dimension(200, 500));
        right.setMinimumSize(new Dimension(200, 500));
        right.setMaximumSize(new Dimension(200, 0));
        right.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(right, BorderLayout.LINE_END);
        
        setViewportView(panel);
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
