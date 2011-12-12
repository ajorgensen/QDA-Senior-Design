/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import model.MarkedUpText;
import model.Project;
import model.SourceText;
import model.TagInstance;
import model.TagType;
import model.TextSection;
import model.cgitDirectory;

public class tags {
    
    private String working_dir;
    private ArrayList<TagInstance> tagHolder;
    public static final String delimeter = "|";
    
    public tags(String working_dir)
    {
        this.working_dir = working_dir;
        this.tagHolder = new ArrayList<TagInstance>();
        this.loadTags();
    }
    
    public void addTag(TagInstance tag)
    {
        this.tagHolder.add(tag);
        this.saveTags();
    }
    
    public void removeTag(TagInstance tag)
    {
        this.tagHolder.remove(tag);
        this.saveTags();
    }
    
    private void saveTags()
    {
        String tag_path = this.working_dir + cgitDirectory.TAGS_PATH.getPath();
        
        FileUtil.writeFile(false, tag_path, "");
        
        for(TagInstance currTag : this.tagHolder)
        {
            FileUtil.writeFile(true, tag_path, tags.tagToString(currTag) + "\n");
        }
    }
    
    private void loadTags()
    {
        String tagPath = working_dir + cgitDirectory.TAGS_PATH.getPath();
        
        try{
            String tag_data = FileUtil.readFile(tagPath);
            
            for(String tag_line : tag_data.split("\n"))
            {
                tagHolder.add(tags.parseTag(tag_line));
            }
        } catch (Exception e)
        {
            
            return;
        }
    }
    
    public static TagInstance parseTag(String data) throws ParseException
    {
        //TODO error checking
        
        //format is user|data added|date modified|offset|length|tag type|tag path
        String [] tag_parameters = data.split("|");
        
        String user;
        Date dateAdded;
        Date dateModified;
        TextSection selection;
        SourceText sourceText;
        Project project;
        MarkedUpText markedUpText;
        TagType tagType;
        
        if(tag_parameters[0] != null)
            user = tag_parameters[0];
        else
            return null;
        
        if(tag_parameters[1] != null)
            dateAdded = new SimpleDateFormat(TagInstance.DATE_FORMAT).parse(tag_parameters[1]);
        else
            return null;
        
        if(tag_parameters[2] != null)
            dateModified = new SimpleDateFormat(TagInstance.DATE_FORMAT).parse(tag_parameters[2]);
        else
            return null;
        
        if(tag_parameters[3] != null && tag_parameters[4] != null)
            selection = new TextSection(Integer.parseInt(tag_parameters[3]), Integer.parseInt(tag_parameters[4]));
        else
            return null;
        
        if(tag_parameters[4] != null)
            tagType = new TagType(tag_parameters[4]);
        else
            return null;
        
        if(tag_parameters[5] != null)
            sourceText = new SourceText(tag_parameters[5]); 
        
        
        return null;
    }
    
    public static String tagToString(TagInstance tag)
    {
        String tagString = "";
        SimpleDateFormat formatter = new SimpleDateFormat(tag.DATE_FORMAT);
        
        tagString += tag.getOwner() + cgitDirectory.DELIMETER;
        tagString += new StringBuilder(formatter.format(tag.getDateAdded())) + cgitDirectory.DELIMETER;
        tagString += new StringBuilder(formatter.format(tag.getDateModified())) + cgitDirectory.DELIMETER;
        tagString += Integer.toString(tag.getTextSection().getOffset()) + cgitDirectory.DELIMETER;
        tagString += Integer.toString(tag.getTextSection().getLength()) + cgitDirectory.DELIMETER;
        tagString += tag.getTagType().getName() + cgitDirectory.DELIMETER;
        tagString += tag.getTagType().getPathString();
        
        return tagString;
    }
    
    public static void main(String[] args)
    {
    }
    
}
