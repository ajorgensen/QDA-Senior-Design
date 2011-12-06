/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
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
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JLabel message = new JLabel((String) args[0]);
        message.setAlignmentX((float)0.5);
        panel.add(message);
        
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        JButton ok = new JButton("OK");
        ok.setAlignmentX((float)0.5);
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        panel.add(ok);
    }
}
