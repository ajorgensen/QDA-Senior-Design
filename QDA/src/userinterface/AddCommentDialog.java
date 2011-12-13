/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import java.awt.Dimension;
import javax.swing.JTextArea;

/**
 *
 * @author Brittany Nkounkou
 */
public class AddCommentDialog extends AddMarkUpDialog{
    
    JTextArea comment;
    
    public AddCommentDialog(MainFrame mf, String textToMarkUp) {
        super(mf, "Add Comment", "Please write a coment for the following text:", textToMarkUp, "Your comment cannot be empty.", null);
    }
    
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
    
    public String getCommentText() {
        return comment.getText();
    }
    
    @Override
    public boolean validInput() {
        return !comment.getText().isEmpty();
    }
}
