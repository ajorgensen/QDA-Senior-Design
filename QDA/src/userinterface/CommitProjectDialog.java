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
 * This dialog allows the user to commit with a message.
 * @author dumnzzz-sager
 */
public class CommitProjectDialog extends AppDialog {

    private JLabel commitLabel;
    private JTextField commit;
    private JLabel spacer;
    private JButton enter;
    private JButton cancel;
    private JLabel error;
    
    /**
     * Creates a new CommitProjectDialog.
     * @param mf owning window
     */
    public CommitProjectDialog(MainFrame mf) {
        super(mf, "Commit");
    }
    
    /**
     * Initializes the components of a CommitProjectDialog.
     * @param args forwarded to super
     */
    @Override
    protected void initComponents(Object[] args) {
        super.initComponents(args);
        
        setMinimumSize(new java.awt.Dimension(420, 150));
        
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        commitLabel = new JLabel("Enter A Commit:");
        commitLabel.setHorizontalAlignment(JLabel.CENTER);
        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridheight = 1;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 0;
        panel.add(commitLabel, c);
        
        commit = new JTextField(40);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridheight = 1;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;
        panel.add(commit, c);
        
        spacer = new JLabel("         ");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 2;
        panel.add(spacer, c);
        
        enter = new JButton("Commit");
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                enterActionPerformed(evt);
            }
        });
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 2;
        panel.add(enter, c);
        
        cancel = new JButton("Cancel");
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 2;
        c.gridy = 2;
        panel.add(cancel, c);
        
        error = new JLabel(" ");
        c.fill = GridBagConstraints.HORIZONTAL;
        error.setHorizontalAlignment(JLabel.CENTER);
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 3;
        panel.add(error, c);
        
    }
    
    /**
     * Gets the inputted string of the commit.
     * @return the inputted string of the commit
     */
    public String getCommit()
    {
        return this.commit.getText();
    }
    
    /**
     * Validates then processes the commit.
     * @param evt unused
     */
    private void enterActionPerformed(ActionEvent evt) {
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
     * Ensures that the inputted text for the commit is not empty.
     * @return whether or not the commit text is empty
     */
    private boolean validateInput() {
        String l = commit.getText();
        if(l.equals("")) {
            error.setText("Commit Required");
            return false;
        }
        return true;
    }
}
