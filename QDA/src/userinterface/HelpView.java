/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * This class displays the 'Help' tab in the views area.
 * @author Brittany Nkounkou
 */
public class HelpView extends View {
    
    /**
     * The pane in which the help content is displayed.
     */
    private JScrollPane center;
    
    /**
     * Creates a new Help View.
     */
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
