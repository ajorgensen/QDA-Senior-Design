package model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import cgit.MyLogger;
import cgit.LogType;

/**
 * Comment class. A comment that has been added to a MarkedUpText by a user.  
 * @author adm07012
 */
public class Comment extends MarkUp {

    private String comment;
    private String sourceFileName;
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private String sourceHash = "";
    
    /**
     * Constructor for a comment.
     * @param user
     * @param dateAdded
     * @param dateModified
     * @param selection
     * @param comment
     * @param sourceTextHash 
     */
    public Comment(String user, Date dateAdded, Date dateModified, TextSection selection, String comment, String sourceTextHash) {
        super(user, dateAdded, dateModified, selection);
        this.comment = comment;
        this.sourceHash= sourceTextHash;
    }
    
    /**
     * Returns the sourcehash of this comment.
     * @return sourcehash
     */
    public String getSourceHash()
    {
        return this.sourceHash;
    }

    /**
     * Returns the comment string
     * @return comment
     */
    public String getComment() {
        
        
        return this.comment;
    }
    
    /**
     * Sets the comment string
     * @param comment 
     */
    public void setComment(String comment) {
        this.comment = comment;
        modified(new Date());
    }

    /**
     * Returns the source file name
     * @return source file name
     */
    public String getSourcePath() {
        return this.sourceFileName;
    }

    @Override
    public void delete() {
        //TODO
    }
    
    /**
     * Generates a new comment
     * @param user
     * @param selected
     * @param comment
     * @param sourceTextHash
     * @return new Comment
     */
    public static Comment generateNewComment(String user, TextSection selected, String comment, String sourceTextHash) {
        Date now = new Date();
        return new Comment(user, now, now, selected, comment, sourceTextHash);
    }
    
    /**
     * Checks if this comment is equal to obj
     * @param obj
     * @return 
     */
    public boolean isEqualTo(Comment obj) {
        boolean equal = true;

        equal = obj.getOwner().equals(this.getOwner());
        equal = obj.getComment().equals(this.getComment());
        equal = obj.getDateAdded().equals(this.getDateAdded());

        return equal;
    }
}
