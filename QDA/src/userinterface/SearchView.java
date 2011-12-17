/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import checkboxtree.TreeCheckingModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.Box.Filler;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.CompoundBorder;
import javax.swing.tree.TreePath;
import model.MarkedUpText;
import model.TagInstance;
import model.TagType;
import model.TextSection;

/**
 * This class displays to the user a 'Search' tab in the views area.
 * @author Brittany Nkounkou
 */
public class SearchView extends View {

    /**
     * The upper panel, holding the search button.
     */
    private JPanel north;
    
    /**
     * The center area, holding the search results.
     */
    private JScrollPane center;
    
    private MainFrame mainFrame;
    private TreeCheckingModel filesModel;
    private TreeCheckingModel tagsModel;
    private JButton search;
    private List<Entry<MarkedUpText, TagInstance>> searchResults;
    private JPanel results;

    /**
     * Creates a new SearchView.
     * @param mf owning window
     * @param t tab title
     * @param fm the tree of files to be checked
     * @param tm the tree of tags to be checked
     */
    public SearchView(MainFrame mf, String t, TreeCheckingModel fm, TreeCheckingModel tm) {
        super(t);
        mainFrame = mf;
        filesModel = fm;
        tagsModel = tm;
        searchResults = new ArrayList<Entry<MarkedUpText, TagInstance>>();
        initialize();
    }

    /**
     * Initializes the contents of a SearchView.
     */
    private void initialize() {
        north = new JPanel();
        
        add(north, BorderLayout.PAGE_START);
        north.setBackground(new Color(250, 250, 250));
        north.setLayout(new BoxLayout(north, BoxLayout.X_AXIS));
        north.setBorder(new CompoundBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.DARK_GRAY),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        
        center = new JScrollPane();
        center.setBackground(Color.WHITE);
        center.setBorder(BorderFactory.createEmptyBorder());    
        add(center, BorderLayout.CENTER);

        search = new JButton("Search");
        search.setAlignmentY((float) 0.0);
        north.add(search);

        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performSearch();
            }
        });

        results = new JPanel();
        results.setLayout(new BoxLayout(results, BoxLayout.Y_AXIS));
        results.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        
        center.setViewportView(results);
    }

    /**
     * Performs the search according to the checks made in the two trees.
     */
    private void performSearch() {
        searchResults.retainAll(new ArrayList<TagType>());

        TreePath[] checkedTags = tagsModel.getCheckingPaths();
        List<TagType> tagsToSearch = new ArrayList<TagType>(checkedTags.length);
        for (int i = 0; i < checkedTags.length; i++) {
            tagsToSearch.add((TagType) checkedTags[i].getLastPathComponent());
        }

        TreePath[] checkedFiles = filesModel.getCheckingPaths();
        for (int i = 0; i < checkedFiles.length; i++) {
            Object o = checkedFiles[i].getLastPathComponent();
            if (o instanceof MarkedUpText) {
                MarkedUpText mut = (MarkedUpText) o;
                List<TagInstance> tags = mut.searchTags(tagsToSearch);
                for (int j = 0; j < tags.size(); j++) {
                    searchResults.add(new AbstractMap.SimpleEntry<MarkedUpText, TagInstance>(mut, tags.get(j)));
                }
            }
        }

        showResults();
    }

    /**
     * Shows the results of the search in 'center'.
     */
    private void showResults() {
        results.removeAll();
        Filler filler = new Box.Filler(new Dimension(10, 10), new Dimension(10, 10), new Dimension(10, 10));
        filler.setAlignmentX((float)0.0);
        results.add(filler);
        
        for (int i = 0; i < searchResults.size(); i++) {
            Entry<MarkedUpText, TagInstance> emt = searchResults.get(i);
            MarkedUpText mut = emt.getKey();
            TagInstance ti = emt.getValue();
            
            String tagName = ti.getTagType().getPathString();
            String taggedText = getTaggedText(mut, ti.getTextSection());
            String filename = mut.getName();
            
            results.add(new SearchResultPanel(mainFrame, mut, tagName, taggedText, filename));
            filler = new Box.Filler(new Dimension(10, 10), new Dimension(10, 10), new Dimension(10, 10));
            filler.setAlignmentX((float)0.0);
            results.add(filler);
        }
        center.setViewportView(results);
    }
    
    /**
     * Gets the text that the tag has been applied to.
     * @param markedUpText the text file from which the text comes from
     * @param textSection the offset and length of the tagged text
     * @return 
     */
    private String getTaggedText(MarkedUpText markedUpText, TextSection textSection) {
        // This method is tricky because JTextPanes seem to consider newline characters as having a length of 2..
        
        int offset = textSection.getOffset();
        int length = textSection.getLength();
        int start = offset;
        int end = offset + length; 
        
        String text = markedUpText.getSourceText().getText();
        int lastNewLine = text.indexOf('\n', 0);
        
        while (lastNewLine > -1 && lastNewLine < end) {
            if (lastNewLine < start) {
                start++;
                offset++;
                end++;
            }
            else if (lastNewLine == start) {
                start++;
                offset += 2;
                length += 2;
                end++;
            }
            else {
                length++;
                end++;
            }
            lastNewLine = text.indexOf('\n', lastNewLine+1);
        }
        
        if (text.charAt(start-1) == '\n') {
            offset -= 1;
            length -= 2;
        }

        return text.substring(offset, offset+length);
    }

    /**
     * Gets the treeModel of the files to be searched
     * @return the files to be searched
     */
    public TreeCheckingModel getFilesModel() {
        return filesModel;
    }

    /**
     * Gets the treeMoel of the tags to b searched
     * @return the tags o be searched
     */
    public TreeCheckingModel getTagsModel() {
        return tagsModel;
    }
}