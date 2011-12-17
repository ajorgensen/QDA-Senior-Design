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
import javax.swing.JTextField;
/**
 * This Dialog is used to create a new folder in the repository.
 * @author dumnzzz-sager
 */
public class CreateFolderDialog extends AppDialog {
    
    private JLabel folderLabel;
    private JTextField folder;
    private JButton create;
    private JButton cancel;
    private JLabel spacer;
    private JLabel error;
    
    /**
     * Creates a new CreateFolderDialog.
     * @param mf owning window
     */
     public CreateFolderDialog(MainFrame mf) {
        super(mf, "Create Folder");
    }
    
     /**
      * Initializes the components o a CreateFolerDialog.
      * @param args forwards to super
      */
    @Override
    protected void initComponents(Object[] args) {
        super.initComponents(args);
        
        setMinimumSize(new java.awt.Dimension(300, 150));
        
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        folderLabel = new JLabel("Folder Name:  ");
        folderLabel.setHorizontalAlignment(JLabel.RIGHT);
        c.weightx = 0.5;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        panel.add(folderLabel, c);
        
        folder = new JTextField();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 3;
        c.gridx = 1;
        c.gridy = 0;
        panel.add(folder, c);
        
        spacer = new JLabel("            ");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        panel.add(spacer, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 2;
        panel.add(spacer, c);
        
        create = new JButton("Create");
        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                createActionPerformed(evt);
            } 
        });
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 1;
        panel.add(create, c);
        
        cancel = new JButton("Cancel");
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 3;
        c.gridy = 1;
        panel.add(cancel, c);
        
        error = new JLabel(" ");
        error.setHorizontalAlignment(JLabel.CENTER);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 4;
        c.gridx = 0;
        c.gridy = 2;
        panel.add(error, c);
    }
    
    /**
     * Validates the input then closes the dialog.
     * @param evt unused
     */
    private void createActionPerformed(ActionEvent evt) {
        if (validateInput()) {
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
     * Ensures that the input text is not empty.
     * @return whether or note input text is empty
     */
    private boolean validateInput() {
        String l = folder.getText();
        if(l.equals("")) {
            error.setText("Enter A Folder Name");
            return false;
        } else {
            return true;
        }
    }
    
    /**
     * Gets the inputted folder name
     * @return the inputted folder name
     */
    public String getFolderName() {
        return folder.getText();
    }
}
