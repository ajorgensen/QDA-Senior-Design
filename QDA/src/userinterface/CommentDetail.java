/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

/**
 * This class displays the text and author of a comment.
 * @author Brittany Nkounkou
 */
public class CommentDetail extends MarkUpDetail{
    
    /**
     * Creates a new CommentDetail.
     * @param text the comment's text
     * @param owner the comment's author
     */
    public CommentDetail(String text, String owner) {
        super(text, owner, 100, null, false);
    }
}
