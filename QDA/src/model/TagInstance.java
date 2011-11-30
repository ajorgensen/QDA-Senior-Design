package model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import cgit.MyLogger;
import cgit.LogType;

public class TagInstance {
    private Tag tagType;
    private User user;
    private TextSection selectedText;
    private Date dateAdded;
    private Date dateModified;
    private String sourceFileName;

    
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public TagInstance(User user, Date dateAdded, Date dateModified,
            TextSection selection, String sourceFilePath, Tag tagType) {
        this.user = user;
        this.dateAdded = dateAdded;
        this.dateModified = dateModified;
        this.selectedText = selection;
        this.tagType=tagType;
        this.sourceFileName = sourceFilePath;
    }

    public User getUser() {
        return this.user;
    }


    public Tag getTagType() {
        return tagType;
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
}
