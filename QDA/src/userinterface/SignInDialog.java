/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import model.Project;
/**
 *
 * @author Brittany Nkounkou
 */
public class SignInDialog extends AppDialog{
    
    private JLabel pUserLabel;
    private JTextField pUser;
    private JLabel spacer;
    private JButton SignIn;
    private JButton Cancel;
    private JLabel error;
    
    public SignInDialog(MainFrame mf, Project p) {
        super(mf, "Sign In", new Object[]{p});
    }
    
    @Override
    protected void initComponents(Object[] args) {
        super.initComponents(args);
        
        setMinimumSize(new java.awt.Dimension(300, 150));
        
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        pUserLabel = new JLabel("User Name:   ");
        pUserLabel.setHorizontalAlignment(JLabel.RIGHT);
        c.weightx = 0.5;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        panel.add(pUserLabel, c);
        
        pUser = new JTextField();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 2;
        c.gridx = 1;
        c.gridy = 0;
        panel.add(pUser, c);
      
        
        spacer = new JLabel("                      ");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        panel.add(spacer, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 1;
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
        c.gridy = 1;
        panel.add(SignIn, c);
        
        error = new JLabel(" ");
        error.setHorizontalAlignment(JLabel.CENTER);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 2;
        panel.add(error, c);

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
            error.setText("User Name Required");
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

}
