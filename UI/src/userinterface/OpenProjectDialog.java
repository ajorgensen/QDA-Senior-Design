/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author dumnzzz-sager
 */
public class OpenProjectDialog extends AppDialog{
    private JLabel locationLabel;
    private JTextField location;
    private JButton browse;
    private JLabel pUserLabel;
    private JTextField pUser;
    private JLabel passwordLabel;
    private JPasswordField password;
    private JLabel spacer;
    private JButton SignIn;
    private JButton Cancel;
    private JFileChooser chooser;
    private String choosertitle;
    
    public OpenProjectDialog(MainFrame mf) {
        super(mf, "Open Project");
        
    }
    @Override
    protected void initComponents() {
        super.initComponents();
        
        setMinimumSize(new java.awt.Dimension(400, 150));
        
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        locationLabel = new JLabel("Location ");
        locationLabel.setHorizontalAlignment(JLabel.RIGHT);
        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        panel.add(locationLabel, c);
        
        location = new JTextField();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 2;
        c.gridx = 1;
        c.gridy = 0;
        panel.add(location, c);
        
        browse = new JButton("Browse...");
        browse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                browseActionPerformed(evt);
            }
        });
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 3;
        c.gridy = 0;
        panel.add(browse, c);
        
        pUserLabel = new JLabel("User Name ");
        pUserLabel.setHorizontalAlignment(JLabel.RIGHT);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        panel.add(pUserLabel, c);
        
        pUser = new JTextField();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 3;
        c.gridx = 1;
        c.gridy = 1;
        panel.add(pUser, c);
        
        passwordLabel = new JLabel("Password ");
        passwordLabel.setHorizontalAlignment(JLabel.RIGHT);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 2;
        panel.add(passwordLabel, c);
        
        password = new JPasswordField();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 3;
        c.gridx = 1;
        c.gridy = 2;
        panel.add(password, c);
        
        spacer = new JLabel(" ");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 3;
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
        c.gridy = 3;
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
        c.gridy = 3;
        panel.add(Cancel, c);
    }
    
    private void browseActionPerformed(ActionEvent evt){
        //Need to make it open folder browser
        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle(choosertitle);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) { 
          System.out.println("getCurrentDirectory(): " 
             +  chooser.getCurrentDirectory());
          System.out.println("getSelectedFile() : " 
             +  chooser.getSelectedFile());
          }
            else {
          System.out.println("No Selection ");
          }

        File file = chooser.getSelectedFile();
        if(file == null) {
            location.setText("No Selection");
        } else {
            location.setText(file.toString());
        }
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
        }
        String l = location.getText();
        if(l.equals("")) {
            location.setText("Please enter a location");
        }
        if(n.equals("") || l.equals("")) {
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