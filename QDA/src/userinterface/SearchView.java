/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import checkboxtree.TreeCheckingModel;
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
import javax.swing.tree.TreePath;
import model.MarkedUpText;
import model.TagInstance;
import model.TagType;

/**
 *
 * @author Brittany Nkounkou
 */
public class SearchView extends View {

    private MainFrame mainFrame;
    private TreeCheckingModel filesModel;
    private TreeCheckingModel tagsModel;
    private JButton search;
    private List<Entry<MarkedUpText, TagInstance>> searchResults;
    private JPanel results;

    public SearchView(MainFrame mf, String t, TreeCheckingModel fm, TreeCheckingModel tm) {
        super(t);
        mainFrame = mf;
        filesModel = fm;
        tagsModel = tm;
        searchResults = new ArrayList<Entry<MarkedUpText, TagInstance>>();
        initialize();
    }

    private void initialize() {
        //tools.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
        //tools.setLayout(new BoxLayout(tools, BoxLayout.Y_AXIS));

        //JLabel searchBox = new JLabel();
        //searchBox.setPreferredSize(new Dimension(245, 1));
        //searchBox.setMinimumSize(new Dimension(245, 1));
        //searchBox.setMaximumSize(new Dimension(245, 1));
        //searchBox.setAlignmentX((float) 1.0);
        //tools.add(searchBox);

        left.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        search = new JButton("Search");
        search.setAlignmentX((float) 1.0);
        left.add(search);

        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performSearch();
            }
        });

        results = new JPanel();
        results.setLayout(new BoxLayout(results, BoxLayout.Y_AXIS));
        results.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        
        center.setViewportView(results);
    }

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

    private void showResults() {
        results.removeAll();
        Filler filler = new Box.Filler(new Dimension(10, 10), new Dimension(10, 10), new Dimension(10, 10));
        filler.setAlignmentX((float)0.0);
        results.add(filler);
        
        for (int i = 0; i < searchResults.size(); i++) {
            Entry<MarkedUpText, TagInstance> emt = searchResults.get(i);
            TagInstance ti = emt.getValue();
            String tagName = ti.getTagType().getPathString();
            String taggedText = ti.getTaggedText();
            String filename = ti.getSourcePath();
            results.add(new SearchResultPanel(mainFrame, emt.getKey(), tagName, taggedText, filename));
            filler = new Box.Filler(new Dimension(10, 10), new Dimension(10, 10), new Dimension(10, 10));
            filler.setAlignmentX((float)0.0);
            results.add(filler);
        }
        center.setViewportView(results);
    }

    public TreeCheckingModel getFilesModel() {
        return filesModel;
    }

    public TreeCheckingModel getTagsModel() {
        return tagsModel;
    }
}
