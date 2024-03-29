/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;

/**
 * This class is virtually abstract by its protected constructor.
 * It provides the template for the inheriting classes AddTagDialog and AddCommentDialog.
 * @author Brittany Nkounkou
 */
public class AddMarkUpDialog extends AppDialog{
    
    /**
     * The area where the text being marked up is displayed.
     */
    protected JScrollPane markUpPane;
    
    /**
     * An error label that is displayed if the user tries to 'OK' on invalid input.
     */
    protected JLabel errorLabel;
    
    /**
     * The text of the error label.
     */
    protected String errorMessage;
    
    /**
     * Protected constructor, providing a template to a dialog that adds a MarkUp.
     * @param mf owning window
     * @param title title of the frame
     * @param instructions instructions to the user for adding markUp
     * @param textToMarkUp text that is being marked up.
     * @param error message that should display upon invlid input
     * @param tags the current tag tree of the project (for AddTagDialog, null for AddCommentDialog)
     */
    protected AddMarkUpDialog(MainFrame mf, String title, String instructions, String textToMarkUp, String error, JTree tags) {
        super(mf, title, new Object[]{instructions, textToMarkUp, tags});
        errorMessage = error;
    }
    
    /**
     * Initializes the components of an AddMarkUpDialog.
     * @param args forwarded to super
     */
    @Override
    protected void initComponents(Object[] args) {
        super.initComponents(args);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JLabel instructions = new JLabel((String) args[0]);
        instructions.setAlignmentX((float)0.0);
        instructions.setPreferredSize(new Dimension(305, 25));
        instructions.setMinimumSize(new Dimension(305, 25));
        instructions.setMaximumSize(new Dimension(305, 25));
        panel.add(instructions);
        
        JTextArea textToMarkUp = new JTextArea((String) args[1]);
        textToMarkUp.setLineWrap(true);
        textToMarkUp.setMinimumSize(new Dimension(305, 75));
        textToMarkUp.setMaximumSize(new Dimension(305, 1000));
        textToMarkUp.setEditable(false);
        textToMarkUp.setBackground(new Color(240, 240, 240));
        
        JScrollPane textToMarkUpPane = new JScrollPane();
        textToMarkUpPane.setViewportView(textToMarkUp);
        textToMarkUpPane.setAlignmentX((float)0.0);
        textToMarkUpPane.setPreferredSize(new Dimension(305, 100));
        textToMarkUpPane.setMinimumSize(new Dimension(305, 100));
        textToMarkUpPane.setMaximumSize(new Dimension(305, 100));
        panel.add(textToMarkUpPane);
        
        markUpPane = new JScrollPane();
        markUpPane.setBackground(Color.WHITE);
        markUpPane.setAlignmentX((float)0.0);
        markUpPane.setPreferredSize(new Dimension(305, 100));
        markUpPane.setMinimumSize(new Dimension(305, 100));
        markUpPane.setMaximumSize(new Dimension(305, 100));
        panel.add(markUpPane);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        
        errorLabel = new JLabel();
        errorLabel.setAlignmentX((float)0.0);
        errorLabel.setPreferredSize(new Dimension(180, 25));
        errorLabel.setMinimumSize(new Dimension(180, 25));
        errorLabel.setMaximumSize(new Dimension(180, 25));
        buttonPanel.add(errorLabel);
        
        JButton okButton = new JButton("OK");
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
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelButtonAction();
            }
        });
        buttonPanel.add(cancelButton);
        
        buttonPanel.setAlignmentX((float)0.0);
        panel.add(buttonPanel);
    }

    /**
     * Makes the error label visible.
     */
    private void displayErrorMessage() {
        errorLabel.setText(errorMessage);
    }
    
    /**
     * Closes the dialog.
     */
    private void cancelButtonAction() {
        setVisible(false);
    }
    
    /**
     * Overridable method for inheriting classes.
     * @return returns whether or not the user's input is valid.
     */
    public boolean validInput() {
        return false;
    }
}
