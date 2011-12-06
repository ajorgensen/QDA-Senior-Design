package model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import cgit.MyLogger;
import cgit.LogType;

public class Comment extends MarkUp{

    private String comment;
    private String sourceFileName;
    
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public Comment(int userId, Date dateAdded, Date dateModified, TextSection selection, String comment, String sourceFilePath){
        super(userId, dateAdded, dateModified, selection);
        this.comment = comment;
        this.sourceFileName = sourceFilePath;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
        modified(new Date());
    }

    public String getSourcePath()
    {
        return this.sourceFileName;
    }
    
    @Override
    public void delete() {
        //TODO
    }
    
    public static Comment generateNewComment(int userId, TextSection selected, String comment, String sourceFilePath)
    {
        Date now = new Date();
        return new Comment(userId, now, now, selected, comment, sourceFilePath);
    }
}
