/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import java.awt.Dimension;
import javax.swing.JTextArea;

/**
 * This Dialog is used to add a Comment to a MarkedUpText.
 * @author Brittany Nkounkou
 */
public class AddCommentDialog extends AddMarkUpDialog{
    
    /**
     * The are in which the user writes his/her comment.
     */
    JTextArea comment;
    
    /**
     * Creates a new AddCommentDialog.
     * @param mf owning window
     * @param textToMarkUp text in file that is being commented on
     */
    public AddCommentDialog(MainFrame mf, String textToMarkUp) {
        super(mf, "Add Comment", "Please write a coment for the following text:", textToMarkUp, "Your comment cannot be empty.", null);
    }
    
    /**
     * Initializes the components of an AddCommentDialog.
     * @param args 
     */
    @Override
    protected void initComponents(Object[] args) {
        super.initComponents(args);
        
        comment = new JTextArea();
        comment.setEditable(true);
        comment.setMinimumSize(new Dimension(305, 75));
        comment.setMaximumSize(new Dimension(305, 1000));
        comment.setLineWrap(true);
        
        markUpPane.setViewportView(comment);
    }
    
    /**
     * Gets the user's inputted comment.
     * @return the user's inputted comment
     */
    public String getCommentText() {
        return comment.getText();
    }
    
    /**
     * Ensures that the user's inputted comment is valid.
     * @return false the user's comment is empty, true otherwise
     */
    @Override
    public boolean validInput() {
        return !comment.getText().isEmpty();
    }
}
