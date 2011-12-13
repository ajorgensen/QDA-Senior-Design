/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;


/**
 * Opens the About Project Dialog box which describes the project
 * with who it was made by
 * @author dumnzzz-sager
 */
public class AboutProjectDialog extends AppDialog {
    
    private JTextArea textArea;
    private JButton close;
    
    public AboutProjectDialog(MainFrame mf) {
        super(mf, "About");
    }
    
    @Override
    protected void initComponents(Object[] args) {
        super.initComponents(args);
        
        setMinimumSize(new java.awt.Dimension(320,100));

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        String about = "Qualitative Data Analysis (QDA) is the investigation of qualitative data (e.g. natural language text) for the purpose of finding and studying patterns and relationships in the data. \n\nThis QDA was created by David Crane, Andrew Jorgensen, Richard Loftis, Alan Marcarelli, and Brittany Nkoukou for University of Connecticut CSE 4904 Fall 2011 \n\nVersion 1.0";
        textArea = new JTextArea(about);
        textArea.setAlignmentX((float)0.5);
        textArea.setBackground(Color.LIGHT_GRAY);
        textArea.setSize(320, 100);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFocusable(false);
        textArea.setEditable(false);
        
        panel.add(textArea);
        
        close = new JButton("Close");
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                closeActionPerformed(evt);
            }
        });
        close.setAlignmentX((float)0.5);
        panel.add(close);

    }
    private void closeActionPerformed(ActionEvent evt) {
        setVisible(false);
    }
}
