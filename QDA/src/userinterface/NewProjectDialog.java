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
 * This dialog allows the user to create a new project.
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
    private JFileChooser chooser2;
    private String choosertitle;
    private String chooser2title;
    private JButton browse2;
    private JLabel sourceLabel;
    private JTextField source;
    
    /**
     * Create a NewProjectDialog.
     * @param mf owning window
     */
    public NewProjectDialog(MainFrame mf) {
        super(mf, "New Project");
    }
    
    /**
     * Initializes th components of a NewProjectDialog.
     * @param args forwarded to super
     */
    @Override
    protected void initComponents(Object[] args) {
        super.initComponents(args);
        
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
        
        locationLabel = new JLabel("Folder Location:  ");
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
        
        sourceLabel = new JLabel("Source Text Location:  ");
        c.fill = GridBagConstraints.HORIZONTAL;
        locationLabel.setHorizontalAlignment(JLabel.RIGHT);
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 2;
        panel.add(sourceLabel, c);
        
        source = new JTextField(20);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 3;
        c.gridx = 1;
        c.gridy = 2;
        panel.add(source,c);
        
        browse2 = new JButton("Browse...");
        browse2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                browse2ActionPerformed(evt);
            }
        });
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 4;
        c.gridy = 2;
        panel.add(browse2, c);
        
//        adminLabel = new JLabel("Administrator:  ");
//        c.fill = GridBagConstraints.HORIZONTAL;
//        adminLabel.setHorizontalAlignment(JLabel.RIGHT);
//        c.gridwidth = 1;
//        c.gridx = 0;
//        c.gridy = 3;
//        panel.add(adminLabel, c);
//        
//        admin = new JTextField(20);
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.gridwidth = 4;
//        c.gridx = 1;
//        c.gridy = 3;
//        panel.add(admin, c);
//        
//        passwordLabel = new JLabel("Password:  ");
//        c.fill = GridBagConstraints.HORIZONTAL;
//        passwordLabel.setHorizontalAlignment(JLabel.RIGHT);
//        c.gridwidth = 1;
//        c.gridx = 0;
//        c.gridy = 4;
//        panel.add(passwordLabel, c);
//        
//        password = new JPasswordField(20);
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.gridwidth = 4;
//        c.gridx = 1;
//        c.gridy = 4;
//        panel.add(password, c);
//        
//        repeatLabel = new JLabel("Repeat Password:  ");
//        c.fill = GridBagConstraints.HORIZONTAL;
//        repeatLabel.setHorizontalAlignment(JLabel.RIGHT);
//        c.gridwidth = 1;
//        c.gridx = 0;
//        c.gridy = 5;
//        panel.add(repeatLabel, c);
//        
//        repeat = new JPasswordField(20);
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.gridwidth = 4;
//        c.gridx = 1;
//        c.gridy = 5;
//        panel.add(repeat, c);
        
        spacer = new JLabel("                                                   ");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 2;
        c.gridy = 6;
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
        c.gridy = 6;
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
        c.gridy = 6;
        panel.add(cancel, c);
        
       
        error = new JLabel();
        
        //TODO
    }
    
    /**
     * Allows the user to browse for a folder location for the new project.
     * @param evt unused
     */
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
    
    /**
     * Allows the user to bows for an intial SourceText to import into the new project.
     * @param evt unused
     */
    private void browse2ActionPerformed(ActionEvent evt) {
        chooser2 = new JFileChooser();
        chooser2.setCurrentDirectory(new java.io.File("."));
        chooser2.setDialogTitle(chooser2title);
        chooser2.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser2.setAcceptAllFileFilterUsed(false);
        if (chooser2.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) { 
          System.out.println("getCurrentDirectory(): " 
             +  chooser2.getCurrentDirectory());
          System.out.println("getSelectedFile() : " 
             +  chooser2.getSelectedFile());
          }
            else {
          System.out.println("No Selection ");
          }

        File file = chooser2.getSelectedFile();
        if(file == null) {
            source.setText("No Selection");
        } else {
            source.setText(file.toString());
        }
    }
    
    /**
     * Validates the input and closes the dialog.
     * @param evt unused
     */
    private void createActionPerformed(ActionEvent evt) {
        if (validateInput()) {
          //  User userAdmin = new User(admin.getText(), password.getPassword().toString());
         //   Project p = new Project(pName.getText(), location.getText(), userAdmin);
            hasResults = true;
            setVisible(false);
        }
    }
    
    /**
     * Closes the dialog.
     * @param evt unused
     */
    private void cancelActionPerformed(ActionEvent evt) {
        setVisible(false);
    }
    
    /**
     * Validates the input parameters in creating a new project.
     * @return whether or not he input is valid
     */
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
        
        // check source location
        String m = source.getText();
        if (m.equals("")) {
            source.setText("Please enter a source text.");
        }
       
        
        // check admin
//        String a = admin.getText();
//        if (a.equals("")) {
//            admin.setText("Please enter an Administrator.");
//        }
//        if(n.equals("") || l.equals("") || a.equals("")) {
//            return false;
//        }
//        
//        // check passwords
//        char[] p = password.getPassword();
//        char[] r = repeat.getPassword();
//        if (p.length == 0) {
//            error.setText("Please enter a Password.");
//            return false;
//        }
//        if (p.length != r.length) {
//            error.setText("Passwords don't match.");
//            return false;
//        }
//        for (int i = 0; i < p.length; i++) {
//            if (p[i] != r[i]) {
//                error.setText("Passwords don't match.");
//                return false;
//            }
//            p[i] = 0;
//            r[i] = 0;
//        }
//        
        return true;
    }
    
    /**
     * Gets the inputted project name
     * @return the inputted project name
     */
    public String getProjectName() {
        if (hasResults) {
            return pName.getText();
        }
        else {
            return null;
        }
    }
    
    /**
     * Gets the inputed project path
     * @return the inputted project path 
     */
    public String getProjectPath() {
        if (hasResults) {
            return location.getText();
        }
        else {
            return null;
        }
    }
    
    /**
     * Gets the inputed SurceText filepath.
     * @return the inputted SourceText filepath
     */
    public String getSourcePath() {
        if (hasResults) {
            return source.getText();
        }
        else {
            return null;
        }
    }
    
    /**
     * For future implementation, an admin will be created with a new project.
     * @return the inputted admin username
     */
    public String getAdministrator() {
        if (hasResults) {
            return admin.getText();
        }
        else {
            return null;
        }
    }
    
    /**
     * FOr future implementation, a password will be created for the project admin.
     * @return the inputted password
     */
    public char[] getPassword() {
        if (hasResults) {
            return password.getPassword();
        }
        else {
            return null;
        }
    }
}
