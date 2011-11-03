/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.*;

/**
 *
 * @author Brittany Nkounkou
 */
public class View extends javax.swing.JScrollPane {
    private String title;
    private String abbrv;
    
    protected View(String t) {
        setTitle(t);
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setMinimumSize(new Dimension(500, 0));
        panel.setPreferredSize(new Dimension(750, 100));
        GroupLayout jPanel1Layout = new GroupLayout(panel);
        panel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 197, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 247, Short.MAX_VALUE)
        );
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
