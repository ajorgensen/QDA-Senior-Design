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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import org.openide.awt.TabbedPaneFactory;
/**
 *
 * @author Brittany Nkounkou
 */
public class NewProjectDialog extends AppDialog{
    private JLabel pNameLabel;
    private JTextField pName;
    private JLabel locationLabel;
    private JTextField location;
    private JButton browse;
    private JLabel adminLabel;
    private JTextField admin;
    private JLabel passwordLabel;
    private JPasswordField password;
    private JLabel repeatLabel;
    private JPasswordField repeat;
    private JButton create;
    private JButton cancel;
    private JLabel error;
    private JLabel spacer;
    private JFileChooser chooser;
    private String choosertitle;
    
    public NewProjectDialog(MainFrame mf) {
        super(mf, "New Project");
    }
    
    @Override
    protected void initComponents() {
        super.initComponents();
        
        setMinimumSize(new java.awt.Dimension(420, 240));
        

        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        pNameLabel = new JLabel("Project Name:  ");
        pNameLabel.setHorizontalAlignment(JLabel.RIGHT);
        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        panel.add(pNameLabel, c);
        
        pName = new JTextField(20);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.0;
        c.gridwidth = 4;
        c.gridx = 1;
        c.gridy = 0;
        panel.add(pName, c);
        
        locationLabel = new JLabel("Location:  ");
        c.fill = GridBagConstraints.HORIZONTAL;
        locationLabel.setHorizontalAlignment(JLabel.RIGHT);
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        panel.add(locationLabel, c);
        
        location = new JTextField(20);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 3;
        c.gridx = 1;
        c.gridy = 1;
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
        c.gridx = 4;
        c.gridy = 1;
        panel.add(browse, c);
        
        
        adminLabel = new JLabel("Administrator:  ");
        c.fill = GridBagConstraints.HORIZONTAL;
        adminLabel.setHorizontalAlignment(JLabel.RIGHT);
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 2;
        panel.add(adminLabel, c);
        
        admin = new JTextField(20);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 4;
        c.gridx = 1;
        c.gridy = 2;
        panel.add(admin, c);
        
        passwordLabel = new JLabel("Password:  ");
        c.fill = GridBagConstraints.HORIZONTAL;
        passwordLabel.setHorizontalAlignment(JLabel.RIGHT);
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 3;
        panel.add(passwordLabel, c);
        
        password = new JPasswordField(20);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 4;
        c.gridx = 1;
        c.gridy = 3;
        panel.add(password, c);
        
        repeatLabel = new JLabel("Repeat Password:  ");
        c.fill = GridBagConstraints.HORIZONTAL;
        repeatLabel.setHorizontalAlignment(JLabel.RIGHT);
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 4;
        panel.add(repeatLabel, c);
        
        repeat = new JPasswordField(20);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 4;
        c.gridx = 1;
        c.gridy = 4;
        panel.add(repeat, c);
        
        spacer = new JLabel("                                                   ");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 2;
        c.gridy = 5;
        panel.add(spacer, c);
        
        create = new JButton("Create");
        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                createActionPerformed(evt);
            }
        });
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 3;
        c.gridy = 5;
        panel.add(create, c);
        
        cancel = new JButton("Cancel");
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 4;
        c.gridy = 5;
        panel.add(cancel, c);
        
       
        error = new JLabel();
        
        //TODO
    }
    
    private void browseActionPerformed(ActionEvent evt) {
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
    
    private void createActionPerformed(ActionEvent evt) {
        if (validateInput()) {
            hasResults = true;
            setVisible(false);
        }
    }
    
    private void cancelActionPerformed(ActionEvent evt) {
        setVisible(false);
    }
    
    private boolean validateInput() {
        // check project name
        String n = pName.getText();
        if (n.equals("")) {
            pName.setText("Please enter a Project Name.");
        }
        
        
        // check location
        String l = location.getText();
        if (l.equals("")) {
            location.setText("Please enter a Location.");
        }
       
        
        // check admin
        String a = admin.getText();
        if (a.equals("")) {
            admin.setText("Please enter an Administrator.");
        }
        if(n.equals("") || l.equals("") || a.equals("")) {
            return false;
        }
        
        // check passwords
        char[] p = password.getPassword();
        char[] r = repeat.getPassword();
        if (p.length == 0) {
            error.setText("Please enter a Password.");
            return false;
        }
        if (p.length != r.length) {
            error.setText("Passwords don't match.");
            return false;
        }
        for (int i = 0; i < p.length; i++) {
            if (p[i] != r[i]) {
                error.setText("Passwords don't match.");
                return false;
            }
            p[i] = 0;
            r[i] = 0;
        }
        
        return true;
    }
    
    public String getProjectName() {
        if (hasResults) {
            return pName.getText();
        }
        else {
            return null;
        }
    }
    
    public String getProjectPath() {
        if (hasResults) {
            return location.getText();
        }
        else {
            return null;
        }
    }
    
    public String getAdministrator() {
        if (hasResults) {
            return admin.getText();
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
