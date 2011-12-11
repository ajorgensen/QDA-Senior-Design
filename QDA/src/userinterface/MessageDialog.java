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
import javax.swing.JTextField;

/**
 *
 * @author Brittany Nkounkou
 */
public class MessageDialog extends AppDialog{
    private JLabel userName;
    private JTextField user;
    private JLabel error;
    
    public MessageDialog(MainFrame mf, String errorMessage) {
        super(mf, "Message", new Object[]{errorMessage});
    }
    
    @Override
    protected void initComponents(Object[] args) {
        super.initComponents(args);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JLabel message = new JLabel((String) args[0]);
        message.setAlignmentX((float)0.5);
        panel.add(message);
        
        panel.add(Box.createRigidArea(new Dimension(150, 20)));
        
        userName = new JLabel("Enter A User Name:");
        userName.setAlignmentX((float)0.5);
        panel.add(userName);
        
        user = new JTextField();
        user.setAlignmentX((float)0.5);
        panel.add(user);
        
        error = new JLabel("  ");
        error.setAlignmentX((float)0.5);
        panel.add(error);
        
        JButton ok = new JButton("OK");
        ok.setAlignmentX((float)0.5);
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(validateInput() == true) {
                    setVisible(false);
                } else {
                    
                }
            }
        });
        panel.add(ok);
    }
    
    public String getUserName() {
        return user.getText();
    }
    public Boolean validateInput() {
        String n = user.getText();
        if(n.equals("")) {
            error.setText("User Name Required");
            return false;
        } else {
            return true;
        }
    }
}
