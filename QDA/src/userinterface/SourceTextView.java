/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import checkboxtree.TreeCheckingModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.HashMap;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.border.CompoundBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import model.Comment;
import model.MarkedUpText;
import model.TagInstance;
import model.TextSection;

/**
 *
 * @author Brittany Nkounkou
 */
public class SourceTextView extends View {
    
    private static final Color[] tagColors = {
    new Color(136,0,0),
    new Color(0,136,0),
    new Color(0,0,136),
    new Color(0,136,136),
    new Color(136,0,136),
    new Color(136,136,0),
    new Color(255,102,0),
    new Color(0,255,102),
    new Color(102,0,255),
    new Color(0,102,255),
    new Color(255,0,102),
    new Color(102,255,0)};
    
    private MarkedUpText markedUpText;
    private TreeCheckingModel tagsModel;
    private JButton addComment;
    private JButton addTag;
    private JTextPane text;
    private Style defaultStyle;
    private Style commentStyle;
    private HashMap<String, Style> tagStyles;
    private int prevColorIndex;
    private int selectionStart;
    private int selectionEnd;
    
    public MarkedUpText getMarkedUpText() {
        return markedUpText;
    }
    
    public TreeCheckingModel getTagsModel() {
        return tagsModel;
    }
    
    public SourceTextView(MarkedUpText mut, TreeCheckingModel tm) {
        super(mut.toString());
        markedUpText = mut;
        tagsModel = tm;
        prevColorIndex = -1;
        selectionStart = -1;
        selectionEnd = -1;
        
        initializeLeft();
        initializeCenter();
        initializeRight();
    }
    
    private void initializeLeft() {
        addComment = new JButton("Add Comment");
        addComment.setEnabled(false);
        //TODO: set size
        addComment.setAlignmentX((float)1.0);
        
        addTag = new JButton("Add Tag");
        addTag.setEnabled(false);
        //TODO: set size
        addTag.setAlignmentX((float)1.0);
        
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        left.add(addComment);
        left.add(addTag);
    }
    
    private void initializeCenter() {
        center.setBackground(Color.LIGHT_GRAY);        
        center.setLayout(new BorderLayout());
        
        text = new JTextPane();
        initializeTextContent();
        text.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        text.setEditable(false);
        text.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                textSelected(e);
            }
        });
        text.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (addComment != e.getOppositeComponent())
                    addComment.setEnabled(false);
                if (addTag != e.getOppositeComponent())
                    addTag.setEnabled(false);
            }
        });
        
        center.add(text, BorderLayout.CENTER);
    }
    
    private void initializeTextContent() {
        text.setText(markedUpText.getSourceText().getText());
        StyledDocument doc = text.getStyledDocument();
        
        defaultStyle = doc.addStyle("default", StyleContext.getDefaultStyleContext().
                getStyle(StyleContext.DEFAULT_STYLE));
        
        commentStyle = doc.addStyle("comment", defaultStyle);
        StyleConstants.setBackground(commentStyle, new Color(225,225,225));
        
        // mark up comments
        List<Comment> comments = markedUpText.getComments();
        for (int i = 0; i < comments.size(); i++) {
            Comment c = comments.get(i);
            TextSection ts = c.getTextSection();
            doc.setCharacterAttributes(ts.getOffset(), ts.getLength(), commentStyle, false);
            //TODO: add actual comment in display
        }
        
        // mark up tags
        tagStyles = new HashMap<String, Style>();
        List<TagInstance> tags = markedUpText.getTags();
        for (int i = 0; i < tags.size(); i++) {
            Style tagStyle;
            TagInstance ti = tags.get(i);
            String tagPath = "t"+ti.getTagType().getPathString();
            if (tagStyles.containsKey(tagPath)) {
                tagStyle = tagStyles.get(tagPath);
            } else {
                tagStyle = doc.addStyle(tagPath, defaultStyle);
                StyleConstants.setForeground(tagStyle, nextColor());
                StyleConstants.setUnderline(tagStyle, true);
            }
            TextSection ts = ti.getTextSection();
            doc.setCharacterAttributes(ts.getOffset(), ts.getLength(), tagStyle, false);
        }
    }
    
    private void initializeRight() {
        
    }
    
    private Color nextColor() {
        prevColorIndex += 1;
        if (prevColorIndex == tagColors.length) {
            prevColorIndex = 0;
        }
        return tagColors[prevColorIndex];
    }
    
    private void textSelected(CaretEvent evt) {
        //Get the location in the text
        int dot = evt.getDot();
        int mark = evt.getMark();
        
        if (dot == mark) {// no selection
            selectionStart = -1;
            selectionEnd = -1;
            addComment.setEnabled(false);
            addTag.setEnabled(false);
        }
        else {
            if (dot < mark) {
                selectionStart = dot;
                selectionEnd = mark;
            } else {
                selectionStart = mark;
                selectionEnd = dot;
            }
            addComment.setEnabled(true);
            addTag.setEnabled(true);
        }
    }
}
