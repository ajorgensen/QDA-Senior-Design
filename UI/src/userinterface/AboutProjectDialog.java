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

/**
 *
 * @author dumnzzz-sager
 */
public class AboutProjectDialog extends AppDialog {
    
    private JLabel about1;
    private JLabel about2;
    private JLabel about3;
    private JLabel about4;
    private JLabel about5;
    private JLabel about6;
    private JLabel about7;
    private JLabel about8;
    private JLabel about9;
    private JLabel about10;
    private JLabel about11;
    private JLabel about12;
    private JLabel about13;
    
    private JLabel spacer;
    private JButton close;
    
    public AboutProjectDialog(MainFrame mf) {
        super(mf, "About");
    }
    
    @Override
    protected void initComponents() {
        super.initComponents();
        
        setMinimumSize(new java.awt.Dimension(420, 300));
        
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        about1 = new JLabel("Qualitative Data Analysis (QDA) is the investigation of qualitative data");
        about1.setHorizontalAlignment(JLabel.CENTER);
        c.weightx = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridheight = 1;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 0;
        panel.add(about1, c);
        
        about2 = new JLabel("(e.g. natural language text) for the purpose of finding and studying");
        about2.setHorizontalAlignment(JLabel.CENTER);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridheight = 1;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;
        panel.add(about2, c);
        
        about3 = new JLabel("patterns and relationships in the data. One QDA task is the tagging of ");
        about3.setHorizontalAlignment(JLabel.CENTER);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridheight = 1;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 2;
        panel.add(about3, c);
        
        about4 = new JLabel("texts, wherein passages from larger source texts are tagged as belonging");
        about4.setHorizontalAlignment(JLabel.CENTER);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridheight = 1;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 3;
        panel.add(about4, c);
        
        about5 = new JLabel("to certain categories. Another QDA task is to write a comment pertaining");
        about5.setHorizontalAlignment(JLabel.CENTER);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridheight = 1;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 4;
        panel.add(about5, c);
        
        about6 = new JLabel("to a passage of text. This tool will allow users to tag and comment");
        about6.setHorizontalAlignment(JLabel.CENTER);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridheight = 1;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 5;
        panel.add(about6, c);
        
        about7 = new JLabel("passages within source texts. In addition, users will be able to search");
        about7.setHorizontalAlignment(JLabel.CENTER);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridheight = 1;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 6;
        panel.add(about7, c);
        
        about8 = new JLabel("for passages based on tags. Furthermore, this tool will provide version");
        about8.setHorizontalAlignment(JLabel.CENTER);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridheight = 1;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 7;
        panel.add(about8, c);
        
        about9 = new JLabel("control for the employed QDA tasks, allowing researchers to collaborate");
        about9.setHorizontalAlignment(JLabel.CENTER);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridheight = 1;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 8;
        panel.add(about9, c);
        
        about10 = new JLabel("in a distributed fashion. Distributed version control includes users");
        about10.setHorizontalAlignment(JLabel.CENTER);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridheight = 1;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 9;
        panel.add(about10, c);
        
        about11 = new JLabel("maintaining their own repositories of source texts, tags, and comments, ");
        about11.setHorizontalAlignment(JLabel.CENTER);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridheight = 1;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 10;
        panel.add(about11, c);
        
        about12 = new JLabel("and merging their changes with others’, much like the distributed");
        about12.setHorizontalAlignment(JLabel.CENTER);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridheight = 1;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 11;
        panel.add(about12, c);
        
        about13 = new JLabel("version control system Git.");
        about13.setHorizontalAlignment(JLabel.CENTER);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridheight = 1;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 12;
        panel.add(about13, c);
        
        spacer = new JLabel("                     ");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 13;
        panel.add(spacer, c);
        
        close = new JButton("Close");
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                closeActionPerformed(evt);
            }
        });
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 13;
        panel.add(close, c);
        
        spacer = new JLabel("                     ");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 2;
        c.gridy = 13;
        panel.add(spacer, c);
    }
    private void closeActionPerformed(ActionEvent evt) {
        setVisible(false);
    }
}
