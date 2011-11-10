/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

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
    
    public NewProjectDialog(MainFrame mf) {
        super(mf, "New Project");
    }
    
    @Override
    protected void initComponents() {
        super.initComponents();
        
        pNameLabel = new JLabel("Project Name:");
        pName = new JTextField();
        
        locationLabel = new JLabel("Location:");
        location = new JTextField();
        browse = new JButton("Browse...");
        browse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                browseActionPerformed(evt);
            }
        });
        
        adminLabel = new JLabel("Administrator:");
        admin = new JTextField();
        passwordLabel = new JLabel("Password:");
        password = new JPasswordField();
        repeatLabel = new JLabel("Repeat Password:");
        repeat = new JPasswordField();
        
        create = new JButton("Create");
        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                createActionPerformed(evt);
            }
        });
        
        cancel = new JButton("Cancel");
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });
        
        error = new JLabel();
        
        //TODO
    }
    
    private void browseActionPerformed(ActionEvent evt) {
        //TODO
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
            error.setText("Please enter a Project Name.");
            return false;
        }
        //TODO
        
        // check location
        String l = location.getText();
        if (l.equals("")) {
            error.setText("Please enter a Location.");
            return false;
        }
        //TODO
        
        // check admin
        String a = admin.getText();
        if (n.equals("")) {
            error.setText("Please enter an Administrator.");
            return false;
        }
        //TODO
        
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
