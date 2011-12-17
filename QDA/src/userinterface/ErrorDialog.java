/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 * This dialog is to be used by any part of the application that needs to display an error to the user.
 * @author Brittany Nkounkou
 */
public class ErrorDialog extends AppDialog{
    
    /**
     * Creates a new ErrorDialog.
     * @param mf owning winow
     * @param errorMessage error message to be displayed
     */
    public ErrorDialog(MainFrame mf, String errorMessage) {
        super(mf, "Error", new Object[]{errorMessage});
    }
    
    /**
     * Initializes the components of an ErrorDialog
     * @param args first object is the error message to be displayed
     */
    @Override
    protected void initComponents(Object[] args) {
        super.initComponents(args);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JLabel message = new JLabel((String) args[0]);
        message.setAlignmentX((float)0.5);
        panel.add(message);
        
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        JButton ok = new JButton("OK");
        ok.setAlignmentX((float)0.5);
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        panel.add(ok);
    }
}
