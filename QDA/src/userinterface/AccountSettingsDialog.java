/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import model.User;
/**
 *
 * @author Brittany Nkounkou
 */
public class AccountSettingsDialog extends AppDialog{
    
    private JButton changeUserName;
    private JButton changePass;
    private JButton cancelMain;
    private JButton cancelSub;
    private JButton cancelSub2;
    private JButton submitUser;
    private JButton submitPass;
    private JLabel userNameLabel;
    private JLabel userOldLabel;
    private JLabel userNewLabel;
    private JLabel passLabel;
    private JLabel oldPassLabel;
    private JLabel newPassLabel;
    private JLabel confirmPassLabel;
    private JTextField userName;
    private JTextField userOld;
    private JTextField userNew;
    private JPasswordField pass;
    private JPasswordField oldPass;
    private JPasswordField newPass;
    private JPasswordField confirmPass;
    private JLabel spacer;
    private JLabel error;
    
    public AccountSettingsDialog(MainFrame mf) {
        super(mf, "Account Settings");
    }
    
    @Override
    protected void initComponents(Object[] args) {
        super.initComponents(args);
        
        setMinimumSize(new java.awt.Dimension(400, 200));
        
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        changeUserName = new JButton("Change User Name");
        changeUserName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                changeUserNameActionPerformed(evt);
            }
        });
        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        panel.add(changeUserName, c);
        
        changePass = new JButton("Change Password");
        changePass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                changePassActionPerformed(evt);
            }
        });
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        panel.add(changePass, c);
        
        cancelMain = new JButton("Cancel");
        cancelMain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                cancelMainActionPerformed(evt);
            }
        });
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 0;
        panel.add(cancelMain, c);
    }
    
    private void cancelMainActionPerformed(ActionEvent evt) {
        setVisible(false);
    }
    private void changePassActionPerformed(ActionEvent evt) {
        changeUserName.setVisible(false);
        changePass.setVisible(false);
        cancelMain.setVisible(false);
        
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        userNameLabel = new JLabel("Username ");
        userNameLabel.setHorizontalAlignment(JLabel.RIGHT);
        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        panel.add(userNameLabel, c);
        
        userName = new JTextField();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 4;
        c.gridx = 1;
        c.gridy = 0;
        panel.add(userName, c);
        
        oldPassLabel = new JLabel("Old Password ");
        oldPassLabel.setHorizontalAlignment(JLabel.RIGHT);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        panel.add(oldPassLabel, c);
        
        oldPass = new JPasswordField();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 4;
        c.gridx = 1;
        c.gridy = 1;
        panel.add(oldPass, c);
        
        newPassLabel = new JLabel("New Password ");
        newPassLabel.setHorizontalAlignment(JLabel.RIGHT);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 2;
        panel.add(newPassLabel, c);
        
        newPass = new JPasswordField();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 4;
        c.gridx = 1;
        c.gridy = 2;
        panel.add(newPass, c);
        
        confirmPassLabel = new JLabel("Confirm New Password ");
        confirmPassLabel.setHorizontalAlignment(JLabel.RIGHT);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 3;
        panel.add(confirmPassLabel, c);
        
        confirmPass = new JPasswordField();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 4;
        c.gridx = 1;
        c.gridy = 3;
        panel.add(confirmPass, c);
        
        spacer = new JLabel("                                      ");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 4;
        panel.add(spacer, c);
        
        submitPass = new JButton("Change Password");
        submitPass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                submitPassActionPerformed(evt);
            }
        });
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 3;
        c.gridy = 4;
        panel.add(submitPass, c);
        
        cancelSub2 = new JButton("Cancel");
        cancelSub2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                cancelSub2ActionPerformed(evt);
            }
        });
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 4;
        c.gridy = 4;
        panel.add(cancelSub2, c);
        
        error = new JLabel(" ");
        error.setHorizontalAlignment(JLabel.CENTER);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 5;
        c.gridx = 0;
        c.gridy = 5;
        panel.add(error, c);
        
        
    }
    
    private void submitPassActionPerformed(ActionEvent evt) {
        if(validatePasswordInput()) {
            User alpha = new User(userName.getText(), oldPass.getPassword().toString());
            User beta = new User(userName.getText(), newPass.getPassword().toString());
            if(alpha.comparePassword(oldPass.getPassword().toString()) == true) {
                //MainFrame.project.removeUser(alpha);
                //MainFrame.project.addUser(beta);
                hasResults = true;
                setVisible(false);
            } else {
                error.setText("Old Password Is Incorrect");
            }
            
        }
    }
    private void changeUserNameActionPerformed(ActionEvent evt) {
        changeUserName.setVisible(false);
        changePass.setVisible(false);
        cancelMain.setVisible(false);
        
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        userOldLabel = new JLabel("Old Username ");
        userOldLabel.setHorizontalAlignment(JLabel.RIGHT);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        panel.add(userOldLabel, c);
        
        userOld = new JTextField();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 4;
        c.gridx = 1;
        c.gridy = 0;
        panel.add(userOld, c);
        
        userNewLabel = new JLabel("New Username ");
        userNewLabel.setHorizontalAlignment(JLabel.RIGHT);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        panel.add(userNewLabel, c);
        
        userNew = new JTextField();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 4;
        c.gridx = 1;
        c.gridy = 1;
        panel.add(userNew, c);
        
        passLabel = new JLabel("Password ");
        passLabel.setHorizontalAlignment(JLabel.RIGHT);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 2;
        panel.add(passLabel, c);
        
        pass = new JPasswordField();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 4;
        c.gridx = 1;
        c.gridy = 2;
        panel.add(pass, c);
        
        spacer = new JLabel("                                      ");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 3;
        panel.add(spacer, c);
        
        submitUser = new JButton("Submit Change");
        submitUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                submitUserActionPerformed(evt);
            }
        });
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 3;
        c.gridy = 3;
        panel.add(submitUser, c);
        
        cancelSub = new JButton("Cancel");
        cancelSub.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                cancelSubActionPerformed(evt);
            }
        });
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 4;
        c.gridy = 3;
        panel.add(cancelSub, c);
        
        error = new JLabel(" ");
        error.setHorizontalAlignment(JLabel.CENTER);
        c.gridwidth = 5;
        c.gridx = 0;
        c.gridy = 4;
        panel.add(error, c);
        
    }
    private void submitUserActionPerformed(ActionEvent evt) {
        if (validateUserInput()) {
            User alpha = new User(userOld.getText(), pass.getPassword().toString());
            User beta = new User(userNew.getText(), pass.getPassword().toString());
            if(alpha.comparePassword(pass.getPassword().toString()) == true) {
                //MainFrame.project.removeUser(alpha);
                //MainFrame.project.addUseR(beta);
                hasResults = true;
                setVisible(false);  
            } else {
                error.setText("Password Is Incorrect");
            }
            
        }
               
    }
    
    private void cancelSubActionPerformed(ActionEvent evt) {
        userOldLabel.setVisible(false);
        userOld.setVisible(false);
        userNewLabel.setVisible(false);
        userNew.setVisible(false);
        passLabel.setVisible(false);
        pass.setVisible(false);
        spacer.setVisible(false);
        submitUser.setVisible(false);
        cancelSub.setVisible(false);
        error.setVisible(false);
        
        changeUserName.setVisible(true);
        changePass.setVisible(true);
        cancelMain.setVisible(true);
                
    }
    
    private void cancelSub2ActionPerformed(ActionEvent evt) {
        userNameLabel.setVisible(false);
        userName.setVisible(false);
        oldPassLabel.setVisible(false);
        oldPass.setVisible(false);
        newPassLabel.setVisible(false);
        newPass.setVisible(false);
        confirmPassLabel.setVisible(false);
        confirmPass.setVisible(false);
        spacer.setVisible(false);
        submitPass.setVisible(false);
        cancelSub2.setVisible(false);
        error.setVisible(false);
        
        changeUserName.setVisible(true);
        changePass.setVisible(true);
        cancelMain.setVisible(true);
    }
    
    private boolean validatePasswordInput() {
        String u = userName.getText();
        if(u.equals("")) {
            userName.setText("Enter your username");
            return false;
        }
        char[] p = oldPass.getPassword();
        if(p.length < 1) {
            error.setText("Enter your password");
            return false;
        }
        //Need to check that old password is correct for that username
        char[] n = newPass.getPassword();
        char[] c = confirmPass.getPassword();
        if((n.length < 1) || (c.length < 1)) {
            error.setText("Enter your new password");
            return false;
        }
        if(c.length != n.length) {
            error.setText("New Password lengths don't match");
            return false;
        }
        for(int i = 0; i < n.length; i++) {
            if(n[i] != c[i]) {
                error.setText("New Passwords don't match");
                return false;
            }
        }
        return true;
    }
    private boolean validateUserInput() {
        String n = userOld.getText();
        if(n.equals("")) {
            userOld.setText("Please enter a User Name");
        }
        String o = userNew.getText();
        if(o.equals("")) {
            userNew.setText("Please enter a User Name");
        }
        if(o.equals(n)) {
            userNew.setText("New name is same as old");
        }
        char[] p = pass.getPassword();
        if(p.length < 1) {
            error.setText("Enter your password");
            return false;
        }
        
        //Need to check that the password matches userOld's password
        if(n.equals("") || o.equals("") || o.equals(n)){
            return false;
        }
        return true;
    } 
}
