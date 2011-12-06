/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import javax.swing.JLabel;

/**
 *
 * @author Brittany Nkounkou
 */
public class MessageDialog extends AppDialog{
    
    public MessageDialog(MainFrame mf, String errorMessage) {
        super(mf, "Message", new Object[]{errorMessage});
    }
    
    @Override
    protected void initComponents(Object[] args) {
        super.initComponents(args);
        panel.add(new JLabel((String) args[0]));
    }
}
