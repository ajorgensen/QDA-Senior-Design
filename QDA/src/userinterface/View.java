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
    
    private JPanel west;
    protected JPanel tools;
    protected JPanel left;
    protected JScrollPane center;
    
    protected View(String t) {
        setTitle(t);
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        
        west = new JPanel();
        west.setLayout(new BorderLayout());
        
        tools = new JPanel();
        tools.setBackground(new Color(250,250,250));
        west.add(tools, BorderLayout.NORTH);
        
        left = new JPanel();
        left.setBackground(new Color(250,250,250));
        west.add(left, BorderLayout.CENTER);
        
        add(west, BorderLayout.LINE_START);
        
        center = new JScrollPane();
        center.setBackground(Color.WHITE);
        center.setBorder(BorderFactory.createEmptyBorder());
        center.setViewportBorder(BorderFactory.createMatteBorder(0,1,0,1,Color.DARK_GRAY));
        
        add(center, BorderLayout.CENTER);
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
