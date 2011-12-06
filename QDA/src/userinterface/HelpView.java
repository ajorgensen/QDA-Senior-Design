/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import java.awt.Color;
import javax.swing.JLabel;

/**
 *
 * @author Brittany Nkounkou
 */
public class HelpView extends View {
    public HelpView() {
        super("Help");
        
        center.setLayout(new javax.swing.BoxLayout(center, javax.swing.BoxLayout.Y_AXIS));
        center.add(new JLabel("Help is on the way..."));
    }
}
