/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

/**
 *
 * @author Brittany Nkounkou
 */
public class MarkUpDetail extends JScrollPane {

    protected MarkUpDetail(String text, String owner, int height, Color color, boolean underline) {
        JTextPane tp = new JTextPane();
        tp.setAlignmentX(Component.CENTER_ALIGNMENT);
        tp.setText(text + " (" + owner + ")");
        this.setPreferredSize(new Dimension(220, height));
        this.setMinimumSize(new Dimension(220, height));
        this.setMaximumSize(new Dimension(220, height));
        tp.setEditable(false);
        tp.setFocusable(false);

        StyledDocument doc = tp.getStyledDocument();
        Style defaultStyle = doc.addStyle("default", StyleContext.getDefaultStyleContext().
                getStyle(StyleContext.DEFAULT_STYLE));
        Style markUpStyle = doc.addStyle("markUp", defaultStyle);
        if (color != null) {
            StyleConstants.setForeground(markUpStyle, color);
        }
        StyleConstants.setUnderline(markUpStyle, underline);
        StyleConstants.setBold(markUpStyle, true);
        
        int last = doc.getLength() - (3 + owner.length());
        doc.setCharacterAttributes(0, last, markUpStyle, false);

        this.setViewportView(tp);
    }
}
