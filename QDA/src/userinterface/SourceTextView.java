/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import checkboxtree.TreeCheckingEvent;
import checkboxtree.TreeCheckingListener;
import checkboxtree.TreeCheckingModel;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import javax.swing.tree.TreePath;
import model.Comment;
import model.MarkedUpText;
import model.TagInstance;
import model.TagType;
import model.TextSection;

/**
 *
 * @author Brittany Nkounkou
 */
public class SourceTextView extends View {
    
    private static final Color commentColor = new Color(225,225,225);
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
    
    private MainFrame mainFrame;
    private MarkedUpText markedUpText;
    private TreeCheckingModel tagsModel;
    private JButton tag;
    private JButton comment;
    private JCheckBox viewComments;
    private JTextPane text;
    private Style defaultStyle;
    private Style commentStyle;
    private HashMap<String, Integer> tagToColorIndex;
    private int prevColorIndex;
    private int vertScrollPos;
    private int selectionStart;
    private int selectionEnd;
    
    public MarkedUpText getMarkedUpText() {
        return markedUpText;
    }
    
    public TreeCheckingModel getTagsModel() {
        return tagsModel;
    }
    
    public SourceTextView(MainFrame mf, MarkedUpText mut, TreeCheckingModel tm) {
        super(mut.toString());
        mainFrame = mf;
        markedUpText = mut;
        tagsModel = tm;
        prevColorIndex = -1;
        vertScrollPos = 0;
        selectionStart = -1;
        selectionEnd = -1;
        
        initializeColorIndices();
        
        tagsModel.addTreeCheckingListener(new TreeCheckingListener() {
            @Override
            public void valueChanged(TreeCheckingEvent e) {
                initializeCenter();
            } 
        });
        
        initializeTools();
        initializeLeft();
        initializeCenter();
        center.getVerticalScrollBar().setValue(0);
    }
    
    private void initializeTools() {
        tools.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        tools.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
        
        tag = new JButton("Tag");
        tag.setEnabled(false);
        
        comment = new JButton("Comment");
        comment.setEnabled(false);
        
        viewComments = new JCheckBox("View Comments");
        viewComments.setSelected(true);
        viewComments.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                initializeCenter();
            }
        });
        
        tools.add(tag);
        tools.add(comment);
        tools.add(viewComments);
    }
    
    private void initializeLeft() {
        /*left.setLayout(new OverlayLayout(left));
        JPanel markUps = new JPanel();
        markUps.setBackground(Color.RED);
        JPanel option = new JPanel();
        option.setBackground(Color.YELLOW);
        left.add(option);
        left.add(markUps);*/
        
        
        //left.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        left.add(new JLabel("TODO"));
    }
    
    private void initializeCenter() {
        vertScrollPos = center.getVerticalScrollBar().getValue();
        text = new JTextPane();
        
        text.setBorder(BorderFactory.createEmptyBorder(70, 10, 70, 10));
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
                Object o = e.getOppositeComponent();
                if (tag != o && comment != o) {
                    tag.setEnabled(false);
                    comment.setEnabled(false);
                }
                else if (tag == o)
                    addTag();
                else
                    addComment();
            }
        });
        refreshTextContent();
        
        center.setViewportView(text);
        //TODO: start at the top of the page
    }
    
    private void initializeColorIndices() {
        tagToColorIndex = new HashMap<String, Integer>();
        List<TagInstance> tags = markedUpText.getTags();
        for (int i = 0; i < tags.size(); i++) {
            TagInstance ti = tags.get(i);
            String styleName = "t"+ti.getTagType().getPathString();
            if (!tagToColorIndex.containsKey(styleName)) {
                tagToColorIndex.put(styleName, nextColorIndex());
            }
        }
    }
    
    private void refreshTextContent() {
        text.setText(markedUpText.getSourceText().getText());
        StyledDocument doc = text.getStyledDocument();
        //text.setStyledDocument(doc);
        doc.addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {}
            @Override
            public void removeUpdate(DocumentEvent e) {}
            @Override
            public void changedUpdate(DocumentEvent e) {
                center.getVerticalScrollBar().setValue(vertScrollPos);
            }
        });
        
        defaultStyle = doc.addStyle("default", StyleContext.getDefaultStyleContext().
                getStyle(StyleContext.DEFAULT_STYLE));
        
        commentStyle = doc.addStyle("comment", defaultStyle);
        StyleConstants.setBackground(commentStyle, commentColor);
        
        // comments
        if (viewComments.isSelected()) {
            List<Comment> comments = markedUpText.getComments();
            for (int i = 0; i < comments.size(); i++) {
                Comment c = comments.get(i);
                TextSection ts = c.getTextSection();
                int offset = ts.getOffset();
                int length = ts.getLength();
                Style style;
                /*if (boldPosition >= offset && boldPosition < offset+length) {
                    style = doc.addStyle("bcomment", commentStyle);
                    StyleConstants.setItalic(style, true);
                }
                else {*/
                    style = commentStyle;
                //}
                doc.setCharacterAttributes(offset, length, style, false);
                //TODO: display actual comment on left
            }
        }
        
        // tags
        
        TreePath[] tp = tagsModel.getCheckingPaths();
        
        HashSet<String> activeStyleNames = new HashSet<String>(tp.length);
        for (int i = 0; i < tp.length; i++) {
            activeStyleNames.add("t"+((TagType)tp[i].getLastPathComponent()).getPathString());
        }
        
        List<TagInstance> tags = markedUpText.getTags();
        for (int i = 0; i < tags.size(); i++) {
            TagInstance ti = tags.get(i);
            String styleName = "t" + ti.getTagType().getPathString();
            
            if (activeStyleNames.contains(styleName)) {
                
                int colorIndex;
                if (tagToColorIndex.containsKey(styleName)) {
                    colorIndex = tagToColorIndex.get(styleName);
                } else {
                    colorIndex = nextColorIndex();
                    tagToColorIndex.put(styleName, colorIndex);
                }
                
                Style tagStyle = doc.addStyle(styleName, defaultStyle);
                StyleConstants.setForeground(tagStyle, tagColors[colorIndex]);
                StyleConstants.setUnderline(tagStyle, true);
                
                TextSection ts = ti.getTextSection();
                int offset = ts.getOffset();
                int length = ts.getLength();
                
                Style style;
                /*if (boldPosition >= offset && boldPosition < offset+length) {
                    style = doc.addStyle("b"+styleName, tagStyle);
                    StyleConstants.setItalic(style, true);
                }
                else {*/
                    style = tagStyle;
                //}
                
                doc.setCharacterAttributes(offset, length, style, false);
                //TODO: display tag label on left (and user?)
            }
        }
    }
    
    private int nextColorIndex() {
        prevColorIndex += 1;
        if (prevColorIndex == tagColors.length) {
            prevColorIndex = 0;
        }
        return prevColorIndex;
    }
    
    private void textSelected(CaretEvent evt) {
        //Get the location in the text
        int dot = evt.getDot();
        int mark = evt.getMark();
        
        if (dot == mark) {// no selection
            selectionStart = -1;
            selectionEnd = -1;
            comment.setEnabled(false);
            tag.setEnabled(false);
            /*AttributeSet as = text.getStyledDocument().getCharacterElement(dot).getAttributes().copyAttributes();
            if (as.containsAttribute(StyleConstants.Background, commentColor) ||
                as.containsAttribute(StyleConstants.Underline, true)) {
                initializeCenter(dot);
            }*/
        }
        else {
            if (dot < mark) {
                selectionStart = dot;
                selectionEnd = mark;
            } else {
                selectionStart = mark;
                selectionEnd = dot;
            }
            comment.setEnabled(true);
            tag.setEnabled(true);
        }
    }
    
    private void addTag() {
        int offset = selectionStart;
        int length = selectionEnd-selectionStart;
        
        try {
            String commentedText = text.getText(offset, length);
            AddTagDialog atd = new AddTagDialog(mainFrame, commentedText, mainFrame.getBlankTagTree());
            atd.setVisible(true);
            
            if (atd.hasResults()) {
                TagType tt = atd.getSelectedTagType();
                markedUpText.addTag(tt, new TextSection(offset, length));
            }
            
        } catch (BadLocationException ex) {
            (new ErrorDialog(mainFrame,"Invalid selection")).setVisible(true);
        } finally {
            initializeCenter();
        }
    }
    
    private void addComment() {
        int offset = selectionStart;
        int length = selectionEnd-selectionStart;
        
        try {
            String commentedText = text.getText(offset, length);
            AddCommentDialog acd = new AddCommentDialog(mainFrame, commentedText);
            acd.setVisible(true);
            
            if (acd.hasResults()) {
                String c = acd.getCommentText();
                markedUpText.addComment(c, new TextSection(offset, length));
            }
        } catch (BadLocationException ex) {
            (new ErrorDialog(mainFrame,"Invalid selection")).setVisible(true);
        } finally {
            initializeCenter();
        }
    }
}
