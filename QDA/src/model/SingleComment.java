package model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import cgit.MyLogger;
import cgit.LogType;

public class SingleComment {

    private String user;
    private String comment;
    private TextSection selectedText;
    private Date dateAdded;
    private Date dateModified;
    private String sourceFileName;
    
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    protected SingleComment(String user, Date dateAdded, Date dateModified, TextSection selection, String comment, String sourceFilePath) {
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
    
    public String toString()
    {
        
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        
        return  this.user + QdaDirectory.DELIMETER + 
                new StringBuilder(formatter.format(dateAdded)) + QdaDirectory.DELIMETER + 
                new StringBuilder(formatter.format(dateModified)) + QdaDirectory.DELIMETER +
                Integer.toString(this.selectedText.getStartingPosition()) + QdaDirectory.DELIMETER +
                Integer.toString(this.selectedText.getStartingLineNumber()) + QdaDirectory.DELIMETER +
                Integer.toString(this.selectedText.getEndingPosition()) + QdaDirectory.DELIMETER +
                Integer.toString(this.selectedText.getEndingLineNumber()) + QdaDirectory.DELIMETER +
                this.comment + QdaDirectory.DELIMETER +
                sourceFileName;
    }

    public static final SingleComment process_comment(String comment_string) {
        String[] comment_params = comment_string.split("\\" + QdaDirectory.DELIMETER);

        //TODO: Error checking on split....did we actually get anything back?
        if (comment_params.length != 9) {
            return null;
        }

        //Format will be:
        // username|dateAdded|dateModified|startingPos|startingLine|endingPos|endingLine|comment|filepath
        try {

            String uname;
            Date dateAdded;
            Date dateModified;
            int startingPos;
            int startingLine;
            int endingPos;
            int endingLine;
            String comment_text;
            String sourceFilePath;
            
            uname = comment_params[0];
            dateAdded = new SimpleDateFormat(DATE_FORMAT).parse(comment_params[1]);
            dateModified = new SimpleDateFormat(DATE_FORMAT).parse(comment_params[2]);
            startingPos = Integer.parseInt(comment_params[3]);
            startingLine = Integer.parseInt(comment_params[4]);
            endingPos = Integer.parseInt(comment_params[5]);
            endingLine = Integer.parseInt(comment_params[6]);
            comment_text = comment_params[7];
            sourceFilePath = comment_params[8];

            return new SingleComment(uname, dateAdded, dateModified, new TextSection(startingPos, startingLine, endingPos, endingLine), comment_text, sourceFilePath);
        } catch (java.text.ParseException e) {
            MyLogger.LogMessageToConsole(null, "Error parsing date", LogType.ERROR);
            return null;
        }
    }

    public static final SingleComment new_comment(String uname, TextSection selectedText, String comment_text, String sourceFilePath) {
        Date now = new Date();
        return new SingleComment(uname, now, now, selectedText, comment_text, sourceFilePath);
    }
}
