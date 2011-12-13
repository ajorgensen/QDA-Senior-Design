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
    private String sourceHash;
    
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public TagInstance(String user, Date dateAdded, Date dateModified,
            TextSection selection, TagType tagType, String sourceTextHash) {
        super(user, dateAdded, dateModified, selection);
        this.tagType=tagType;
        
        this.sourceHash = sourceTextHash;
        //TODO: TagInstances should know which markeduptext they belong to
    }
    
    public String getSourceHash()
    {
        return this.sourceHash;
    }

    public TagType getTagType() {
        return tagType;
    }
    
    public String getSourcePath()
    {
        return this.sourceFileName;
    }
    
    public static TagInstance generateNewTag(String user, TextSection section, TagType tagType, String sourceTextHash)
    {
        Date now = new Date();
        
        return new TagInstance(user, now, now, section, tagType, sourceTextHash);
    }
    
    @Override
    public void delete() {
        //TODO
    }
    
    public boolean isEqualTo(TagInstance obj) {
        
        if(!obj.getOwner().equals(this.getOwner()))
            return false;
        
        if(!obj.getTagType().equals(this.getTagType()))
            return false;
        
        return true;
    }
}
