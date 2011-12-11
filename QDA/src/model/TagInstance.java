package model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import cgit.MyLogger;
import cgit.LogType;

public class TagInstance extends MarkUp{
    private TagType tagType;
    private String sourceFileName;
    private MarkedUpText markedUpText;
    
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public TagInstance(String user, Date dateAdded, Date dateModified,
            TextSection selection, MarkedUpText markedUpText , TagType tagType) {
        super(user, dateAdded, dateModified, selection);
        this.tagType=tagType;
        this.sourceFileName = markedUpText.getSourceText().getPath();
        this.markedUpText = markedUpText;
        //TODO: TagInstances should know which markeduptext they belong to
    }

    public TagType getTagType() {
        return tagType;
    }
    
    public String getSourcePath()
    {
        return this.sourceFileName;
    }
    
    @Override
    public void delete() {
        //TODO
    }
    
    public boolean isEqualTo(TagInstance obj) {
        boolean equal = true;

        equal = obj.getOwner().equals(this.getOwner());
        equal = obj.getTagType().equals(this.getTagType());
        equal = obj.getDateAdded().equals(this.getDateAdded());

        return equal;
    }
   
}
