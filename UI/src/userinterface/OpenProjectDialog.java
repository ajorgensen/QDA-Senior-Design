/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author dumnzzz-sager
 */
public class OpenProjectDialog extends AppDialog{
    private JLabel pUserLabel;
    private JTextField pUser;
    private JLabel passwordLabel;
    private JPasswordField password;
    private JLabel spacer;
    private JButton SignIn;
    private JButton Cancel;
    
    public OpenProjectDialog(MainFrame mf) {
        super(mf, "Open Project");
        
    }
    @Override
    protected void initComponents() {
        super.initComponents();
        
        setMinimumSize(new java.awt.Dimension(500, 150));
        
        pUserLabel = new JLabel("User  ");
        pUser = new JTextField(37);
        
        passwordLabel = new JLabel("Password");
        password = new JPasswordField(35);
        
        SignIn = new JButton("Sign In");
        SignIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                SignInActionPerformed(evt);
            } 
        });
        
        Cancel = new JButton("Cancel");
        Cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                CancelActionPerformed(evt);
            }
        });
                
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panel.add(pUserLabel);
        panel.add(pUser);
        
        panel.add(passwordLabel);
        panel.add(password);
        panel.add(SignIn);
        panel.add(Cancel);
    }
    
    private void CancelActionPerformed(ActionEvent evt) {
        setVisible(false);
    }
    
    private void SignInActionPerformed(ActionEvent evt) {
        if (validateInput()) {
            hasResults = true;
            setVisible(false);
        }
    }

    private boolean validateInput() {
        String n = pUser.getText();
        if(n.equals("")) {
            pUser.setText("Please enter a User Name");
            return false;
        }
        return true;
    }    
    
    public String getUserName() {
        if(hasResults) {
            return pUser.getText();
        }
        else {
            return null;
        }
    }

    public char[] getPassword() {
        if (hasResults) {
            return password.getPassword();
        }
        else {
            return null;
        }
    }
    
}
