package model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import cgit.MyLogger;
import cgit.LogType;

public class TagInstance {
    private TagType tagType;
    private User user;
    private TextSection selectedText;
    private Date dateAdded;
    private Date dateModified;
    private String sourceFileName;
    private MarkedUpText markedUpText;
    
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public TagInstance(User user, Date dateAdded, Date dateModified,
            TextSection selection, MarkedUpText markedUpText , TagType tagType) {
        this.user = user;
        this.dateAdded = dateAdded;
        this.dateModified = dateModified;
        this.selectedText = selection;
        this.tagType=tagType;
        this.sourceFileName = markedUpText.getSourceText().getPath();
        this.markedUpText = markedUpText;
        //TODO: TagInstances should know which markeduptext they belong to
    }

    public User getUser() {
        return this.user;
    }


    public TagType getTagType() {
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
    
    public String getTaggedText(){
        return markedUpText.getSourceText().getSelection(selectedText);
    }
    
    public String getSourcePath()
    {
        return this.sourceFileName;
    }
    
    public void print(){
        System.out.println("Tag name: " + tagType.getName());
        System.out.print(this.getTaggedText());
    }
    
}
