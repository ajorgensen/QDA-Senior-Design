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
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import model.Project;

/**
 *
 * @author dumnzzz-sager
 */
public class NewTagDialog extends AppDialog {
    
    private JLabel tagLabel;
    private JTextField tag;
    private JLabel spacer;
    private JComboBox box;
    private JButton add;
    private JButton cancel;
    private JLabel error;
    private JFileChooser chooser;
    private String choosertitle;
    private Project p;
    
    public NewTagDialog(MainFrame mf, Project p) {
        super(mf, "New Tag");
        this.p = p;
        
    }
    
 @Override
    protected void initComponents(Object[] args) {
        super.initComponents(args);
        
        setMinimumSize(new java.awt.Dimension(420, 240));
        

        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        tagLabel = new JLabel("Tag Name:  ");
        tagLabel.setHorizontalAlignment(JLabel.RIGHT);
        c.weightx = 0.5;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        panel.add(tagLabel, c);
        
        tag = new JTextField();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 1;
        c.gridy = 0;
        panel.add(tag, c);
        
//       // String[] beta = p.getFolders();
//        String[] beta = {"tag1", "tag2", "tag3", "tag4"};
//        for(int i = 0; i < beta.length; i++) {
//            System.out.println("Folder" + i + " = " + beta[i]);
//        }
        String[] beta = {"Tags"};
        box = new JComboBox(beta);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 4;
        c.gridx = 0;
        c.gridy = 2;
        panel.add(box, c);

        spacer = new JLabel("                                                           ");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 3;
        panel.add(spacer, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 3;
        panel.add(spacer, c);
        
        add = new JButton("Add Tag");
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                addActionPerformed(evt);
            }
        });
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 2;
        c.gridy = 3;
        panel.add(add, c);
        
        cancel = new JButton("Cancel");
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 3;
        c.gridy = 3;
        panel.add(cancel, c);
        
        error = new JLabel(" ");
        error.setHorizontalAlignment(JLabel.CENTER);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 4;
        c.gridx = 0;
        c.gridy = 4;
        panel.add(error, c);
        
        

        
    }
    


    
    private void addActionPerformed(ActionEvent evt) {
        if (validateInput()) {
            hasResults = true;
            setVisible(false);
        }
    }
    
    private void cancelActionPerformed(ActionEvent evt) {
        setVisible(false);
    }
    
    public String getTagName() {
        return tag.getText();
    }
    public String getComboTag() {
        return box.getSelectedItem().toString();
    }
    private boolean validateInput() {
        String l = tag.getText();
        if(l.equals("")) {
            error.setText("Tag Name Required");
            return false;
        }
        return true;
    }
    
}
