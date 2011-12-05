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
import javax.swing.JTextField;
/**
 *
 * @author dumnzzz-sager
 */
public class SaveAsProjectDialog extends AppDialog {
    private JLabel locationLabel;
    private JTextField location;
    private JButton browse;
    private JButton save;
    private JButton cancel;
    private JFileChooser chooser;
    private String choosertitle;
    private JLabel spacer;
    
    public SaveAsProjectDialog(MainFrame mf) {
        super(mf, "Save As");
    }
    @Override
    protected void initComponents(Object[] args) {
        super.initComponents(args);
        
        setMinimumSize(new java.awt.Dimension(400, 150));
        
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        locationLabel = new JLabel("New Save Location ");
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
        
        spacer = new JLabel("                               ");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        panel.add(spacer, c);
        
        save = new JButton("Save As");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                saveAsActionPerformed(evt);
            } 
        });
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 1;
        panel.add(save, c);
        
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
    }
    
    private void browseActionPerformed(ActionEvent evt){
        
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
    
    private void cancelActionPerformed(ActionEvent evt) {
        setVisible(false);
    }
    
    private void saveAsActionPerformed(ActionEvent evt) {
        if (validateInput()) {
            hasResults = true;
            setVisible(false);
        }
    }
    
    private boolean validateInput() {
        String l = location.getText();
        if(l.equals("")) {
            location.setText("Please enter a location");
            return false;
        }
        return true;
    }
    
    public String getSaveLocation() {
        if(hasResults) {
            return location.getText();
        }
        else {
            return null;
        }
    }
}
