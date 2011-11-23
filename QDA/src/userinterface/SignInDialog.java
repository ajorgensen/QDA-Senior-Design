/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
/**
 *
 * @author Brittany Nkounkou
 */
public class SignInDialog extends AppDialog{
    
    private JLabel pUserLabel;
    private JTextField pUser;
    private JLabel passwordLabel;
    private JPasswordField password;
    private JLabel spacer;
    private JButton SignIn;
    private JButton Cancel;
    
    public SignInDialog(MainFrame mf) {
        super(mf, "Sign In");
    }
    
    @Override
    protected void initComponents() {
        super.initComponents();
        
        setMinimumSize(new java.awt.Dimension(300, 150));
        
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
//        pNameLabel = new JLabel("Project Name:  ");
//        pNameLabel.setHorizontalAlignment(JLabel.RIGHT);
//        c.weightx = 0.5;
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.gridx = 0;
//        c.gridy = 0;
//        panel.add(pNameLabel, c);
        
        
        pUserLabel = new JLabel("User Name ");
        pUserLabel.setHorizontalAlignment(JLabel.RIGHT);
        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        panel.add(pUserLabel, c);
        
        pUser = new JTextField();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 3;
        c.gridx = 1;
        c.gridy = 0;
        panel.add(pUser, c);
        
        passwordLabel = new JLabel("Password ");
        passwordLabel.setHorizontalAlignment(JLabel.RIGHT);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        panel.add(passwordLabel, c);
        
        password = new JPasswordField();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 3;
        c.gridx = 1;
        c.gridy = 1;
        panel.add(password, c);
        
        spacer = new JLabel(" ");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 2;
        panel.add(spacer, c);
        
        SignIn = new JButton("Sign In");
        SignIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                SignInActionPerformed(evt);
            } 
        });
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 2;
        panel.add(SignIn, c);
        
        Cancel = new JButton("Cancel");
        Cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                CancelActionPerformed(evt);
            }
        });
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 3;
        c.gridy = 2;
        panel.add(Cancel, c);
                
//        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
//        panel.add(pUserLabel);
//        panel.add(pUser);
//        
//        panel.add(passwordLabel);
//        panel.add(password);
//        panel.add(SignIn);
//        panel.add(Cancel);
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
