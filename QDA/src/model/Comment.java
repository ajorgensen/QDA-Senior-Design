package model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import cgit.MyLogger;
import cgit.LogType;

public class Comment {

    private String user;
    private String comment;
    private TextSection selectedText;
    private Date dateAdded;
    private Date dateModified;
    private String sourceFileName;
    
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public Comment(String user, Date dateAdded, Date dateModified, TextSection selection, String comment, String sourceFilePath) {
        this.user = user;
        this.comment = comment;
        this.dateAdded = dateAdded;
        this.dateModified = dateModified;
        this.selectedText = selection;
        this.comment = comment;
        this.sourceFileName = sourceFilePath;
    }

    public String getUser() {
        return this.user;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
        this.dateModified = new Date();
    }

    public Date getDateAdded() {
        return this.dateAdded;
    }

    public Date getDateModified() {
        return this.dateModified;
    }

    public TextSection getTextSection()
    {
        return selectedText;
    }
    
    public String getSourcePath()
    {
        return this.sourceFileName;
    }
    
    public static Comment generateNewComment(String user, TextSection selected, String comment, String sourceFilePath)
    {
        Date now = new Date();
        return new Comment(user, now, now, selected, comment, sourceFilePath);
    }
}
