package model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import cgit.MyLogger;
import cgit.LogType;

/**
 * An instance of a tag. Whenever a MarkedUpText is tagged with a TagType, a 
 * TagInstance is made for that tag.
 * @author adm07012
 */
public class TagInstance extends MarkUp{
    private TagType tagType;
    private String sourceFileName;
    private String sourceHash;
    
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    
    /**
     * 
     * @param user
     * @param dateAdded
     * @param dateModified
     * @param selection
     * @param tagType
     * @param sourceTextHash 
     */
    public TagInstance(String user, Date dateAdded, Date dateModified,
            TextSection selection, TagType tagType, String sourceTextHash) {
        super(user, dateAdded, dateModified, selection);
        this.tagType=tagType;
        
        this.sourceHash = sourceTextHash;
        //TODO: TagInstances should know which markeduptext they belong to
    }
    
    /**
     * 
     * @return sourceHash
     */
    public String getSourceHash()
    {
        return this.sourceHash;
    }
    /**
     * 
     * @return - the TagType 
     */
    public TagType getTagType() {
        return tagType;
    }
    
    /**
     * 
     * @return - sourceFileName
     */
    public String getSourcePath()
    {
        return this.sourceFileName;
    }
    
    /**
     * 
     * @param user
     * @param section
     * @param tagType
     * @param sourceTextHash
     * @return 
     */
    public static TagInstance generateNewTag(String user, TextSection section, TagType tagType, String sourceTextHash)
    {
        Date now = new Date();
        
        return new TagInstance(user, now, now, section, tagType, sourceTextHash);
    }
    
    /**
     * Delete method (not implemented)
     */
    @Override
    public void delete() {
        //TODO
    }
    
    /**
     * Checks if this TagInstance is equal to obj
     * @param obj
     * @return true if equal
     */
    public boolean isEqualTo(TagInstance obj) {
        
        if(!obj.getOwner().equals(this.getOwner()))
            return false;
        
        if(!obj.getTagType().equals(this.getTagType()))
            return false;
        
        return true;
    }
}
