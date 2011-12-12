/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;

/**
 *
 * @author Brittany Nkounkou
 */
public class AddMarkUpDialog extends AppDialog{
    
    protected JScrollPane markUpPane;
    protected JLabel errorLabel;
    protected String errorMessage;
    
    protected AddMarkUpDialog(MainFrame mf, String title, String instructions, String textToMarkUp, String error, JTree tags) {
        super(mf, title, new Object[]{instructions, textToMarkUp, tags});
        errorMessage = error;
    }
    
    @Override
    protected void initComponents(Object[] args) {
        super.initComponents(args);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JLabel instructions = new JLabel((String) args[0]);
        instructions.setAlignmentX((float)0.0);
        panel.add(instructions);
        
        JLabel textToMarkUp = new JLabel((String) args[1]);
        textToMarkUp.setAlignmentX((float)0.5);
        
        JScrollPane textToMarkUpPane = new JScrollPane();
        textToMarkUpPane.setViewportView(textToMarkUp);
        
        panel.add(textToMarkUpPane);
        
        markUpPane = new JScrollPane();
        markUpPane.setBackground(Color.WHITE);
        markUpPane.setAlignmentX((float)0.5);
        panel.add(markUpPane);
        
        errorLabel = new JLabel();
        errorLabel.setAlignmentX((float)0.5);
        panel.add(errorLabel);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        
        JButton okButton = new JButton("OK");
        okButton.setPreferredSize(new Dimension(75, 25));
        okButton.setAlignmentY((float)0.0);
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(validInput()) {
                    hasResults = true;
                    setVisible(false);
                } else {
                    displayErrorMessage();
                }
            }
        });
        buttonPanel.add(okButton);
        
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setPreferredSize(new Dimension(75, 25));
        cancelButton.setAlignmentY((float)0.0);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelButtonAction();
            }
        });
        buttonPanel.add(cancelButton);
        
        buttonPanel.setAlignmentX((float)1.0);
        panel.add(buttonPanel);
    }

    private void displayErrorMessage() {
        errorLabel.setText(errorMessage);
    }
    
    private void cancelButtonAction() {
        setVisible(false);
    }
    
    public boolean validInput() {
        return false;
    }
}
