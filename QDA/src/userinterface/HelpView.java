/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Brittany Nkounkou
 */
public class HelpView extends View {
    
    private JScrollPane center;
    
    public HelpView() {
        super("Help");
        
        center = new JScrollPane();
        
        JPanel content = new JPanel();
        content.setBackground(Color.WHITE);
        //content.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        content.add(new JLabel("Help is on the way..."));
        center.setViewportView(content);
        add(center, BorderLayout.CENTER);
    }
}
