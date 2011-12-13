/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.Box.Filler;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.CompoundBorder;
import model.MarkedUpText;
import model.TagInstance;

/**
 *
 * @author Brittany Nkounkou
 */
public class SearchResultPanel extends JPanel{
    
    MainFrame mainFrame;
    MarkedUpText markedUpText;
    
    public SearchResultPanel(MainFrame mf, MarkedUpText mut, String tagName, String taggedText, String filepath) {
        mainFrame = mf;
        markedUpText = mut;
        
        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        this.setAlignmentX((float)0.0);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        JLabel tagNameLabel = new JLabel(tagName);
        tagNameLabel.setAlignmentX((float)0.0);
        this.add(tagNameLabel);
        
        this.add(new Box.Filler(new Dimension(10, 10), new Dimension(10, 10), new Dimension(10, 10)));
        
        JTextArea taggedTextArea = new JTextArea(taggedText);
        taggedTextArea.setAlignmentX((float)0.0);
        this.add(taggedTextArea);
        
        this.add(new Box.Filler(new Dimension(10, 10), new Dimension(10, 10), new Dimension(10, 10)));
        
        JPanel file = new JPanel();
        file.setAlignmentX((float)0.0);
        
        JLabel filepathLabel = new JLabel(filepath);
        filepathLabel.setAlignmentY((float)1.0);
        filepathLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        file.add(filepathLabel);
        
        file.add(Box.createHorizontalGlue());
        
        JButton viewFile = new JButton("View");
        viewFile.setAlignmentY((float)1.0);
        viewFile.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.openSourceTextView(markedUpText);
            }          
        });
        file.add(viewFile);
        
        this.add(file);
        
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
    }
}
