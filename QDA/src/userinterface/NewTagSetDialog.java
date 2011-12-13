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
 *
 * @author dumnzzz-sager
 */
public class NewTagSetDialog extends AppDialog {
    
    private JLabel tagSetLabel;
    private JTextField tagSet;
    private JButton create;
    private JButton cancel;
    private JLabel spacer;
    private JLabel error;
    
     public NewTagSetDialog(MainFrame mf) {
        super(mf, "New Tag Set");
    }
    
    @Override
    protected void initComponents(Object[] args) {
        super.initComponents(args);
        
        setMinimumSize(new java.awt.Dimension(300, 150));
        
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        tagSetLabel = new JLabel("Folder Name:  ");
        tagSetLabel.setHorizontalAlignment(JLabel.RIGHT);
        c.weightx = 0.5;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        panel.add(tagSetLabel, c);
        
        tagSet = new JTextField();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 3;
        c.gridx = 1;
        c.gridy = 0;
        panel.add(tagSet, c);
        
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
        String l = tagSet.getText();
        if(l.equals("")) {
            error.setText("Enter Tag Set Name");
            return false;
        } else {
            return true;
        }
    }
    public String getTagSetName() {
        return tagSet.getText();
    }
}
