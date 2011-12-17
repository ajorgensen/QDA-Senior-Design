/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import java.awt.Color;

/**
 * This class is used to display the details of a Tag Instance.
 * @author Brittany Nkounkou
 */
public class TagDetail extends MarkUpDetail{
    
    /**
     * Creates a new TagDetail
     * @param text tagpath
     * @param owner tag author
     * @param color tag color
     */
    public TagDetail(String text, String owner, Color color) {
        super(text, owner, 45, color, true);
    }
}
