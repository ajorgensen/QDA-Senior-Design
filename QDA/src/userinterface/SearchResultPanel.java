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
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.CompoundBorder;
import model.MarkedUpText;

/**
 * This class displays h content of a search result.
 * @author Brittany Nkounkou
 */
public class SearchResultPanel extends JPanel{
    
    /**
     * The mainFrame of which this SearchResultPanel is a part.
     */
    MainFrame mainFrame;
    
    /**
     * The markedUpText that this SearchResultPanel references.
     */
    MarkedUpText markedUpText;
    
    /**
     * Creates a new SearchResultPanel.
     * @param mf owning window
     * @param mut referenced markedUpText
     * @param tagName name of the mark up tag
     * @param taggedText text that has bee tagged
     * @param filepath the filepath of the markedUpText
     */
    public SearchResultPanel(MainFrame mf, MarkedUpText mut, String tagName, String taggedText, String filepath) {
        mainFrame = mf;
        markedUpText = mut;
        
        this.setBackground(new Color(240, 240, 240));
        this.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.DARK_GRAY),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setLayout(null);
        
        JLabel filepathLabel = new JLabel(filepath);
        filepathLabel.setBounds(10, 10, 450, 15);
        this.add(filepathLabel);
        
        JLabel tagNameLabel = new JLabel(tagName);
        tagNameLabel.setBounds(10, 30, 450, 15);
        this.add(tagNameLabel);

        JTextArea taggedTextArea = new JTextArea(taggedText);
        taggedTextArea.setEditable(false);
        taggedTextArea.setLineWrap(true);
        taggedTextArea.setWrapStyleWord(true);
        
        JScrollPane taggedTextPane = new JScrollPane();
        taggedTextPane.setPreferredSize(new Dimension(300, 100));
        taggedTextPane.setMinimumSize(new Dimension(300, 100));
        taggedTextPane.setMaximumSize(new Dimension(300, 100));
        taggedTextPane.setBounds(100, 45, 300, 100);
        taggedTextPane.setViewportView(taggedTextArea);
        this.add(taggedTextPane);
        
        JButton viewFile = new JButton("View");
        viewFile.setBounds(420, 145, 70, 30);
        viewFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.openSourceTextView(markedUpText);
            }          
        });
        this.add(viewFile);
        
        this.setPreferredSize(new Dimension(500, 185));
        this.setMinimumSize(new Dimension(500, 185));
        this.setMaximumSize(new Dimension(500, 185));
    }
}
