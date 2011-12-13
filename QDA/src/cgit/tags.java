/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import model.MarkedUpText;
import model.Project;
import model.SourceText;
import model.TagInstance;
import model.TagType;
import model.TextSection;
import model.cgitDirectory;

public class tags {

    public static final String delimeter = "|";

    /**
     * This function will handle writing the tags to disk.
     * 
     * @param working_dir is the directory that contains the project we are working on.
     * @param tagHolder the list of tags we want to save
     */
    public static void saveTags(String working_dir, List<TagInstance> tagHolder) {
        String tag_path = working_dir + cgitDirectory.TAGS_PATH.getPath();
        
        if(tagHolder.isEmpty())
            return;
        
        List<TagInstance> allTags = tags.getAllTags(working_dir);
        List<TagInstance> tagsForText = tags.loadTagsForSourceText(working_dir, tagHolder.get(0).getSourceHash());
        
        List<TagInstance> toRemove = new LinkedList<TagInstance>();
        
        //Remove all the comments for this project
       for(TagInstance currTag : allTags)
       {
           for(TagInstance sourceTag : tagsForText)
           {
               if(currTag.isEqualTo(sourceTag))
               {
                   toRemove.add(currTag);
               }
           }
       }
       
       allTags.removeAll(toRemove);
       
       //add all the comments back in
       allTags.addAll(tagHolder);
      
       //clear the file out
        FileUtil.writeFile(false, tag_path, "");

        //write all the tags to refs/tags
        for (TagInstance currTag : tagHolder) {
            FileUtil.writeFile(true, tag_path, tags.tagToString(currTag) + "\n");
        }
    }

    /**
     * This will parse a tag string into a TagInstance. It needs to know about the project to check to see if the tag is belongs to a project.
     * Future revisions of this will allow for the project to be optional and it will return a TagInstance with an empty project.
     * 
     * @param  data is properly formatted tag data
     * @param project the reference to the current project
     * @return a TagInstance from the parsed text
     * @throws ParseException 
     */
    public static TagInstance parseTag(String data) throws ParseException {
        //TODO error checking

        //format is user|data added|date modified|offset|length|tag type|tag path|source path
        String[] tag_parameters = data.split(cgitDirectory.SPLITDELIMETER);

        String user;
        Date dateAdded;
        Date dateModified;
        TextSection selection;
        String sourceHash;
        TagType tagType;

        if (tag_parameters[0] != null) {
            user = tag_parameters[0];
        } else {
            return null;
        }

        if (tag_parameters[1] != null) {
            dateAdded = new SimpleDateFormat(TagInstance.DATE_FORMAT).parse(tag_parameters[1]);
        } else {
            return null;
        }

        if (tag_parameters[2] != null) {
            dateModified = new SimpleDateFormat(TagInstance.DATE_FORMAT).parse(tag_parameters[2]);
        } else {
            return null;
        }

        if (tag_parameters[3] != null && tag_parameters[4] != null) {
            selection = new TextSection(Integer.parseInt(tag_parameters[3]), Integer.parseInt(tag_parameters[4]));
        } else {
            return null;
        }

        if (tag_parameters[5] != null) {
            tagType = new TagType(tag_parameters[5]);
        } else {
            return null;
        }
        
        if (tag_parameters[6] != null) {
            tagType.parsePathString(tag_parameters[6]);
        } else {
            return null;
        }

        if (tag_parameters[7] != null) {
            sourceHash = tag_parameters[7];
        } else {
            return null;
        }

        return new TagInstance(user, dateAdded, dateModified, selection, tagType, sourceHash);
    }

    /**
     * This will load all the tags for a specific project
     * 
     * @param project is a reference to the project we want to load the tags from
     * @return an ArrayList of TagInstances
     */
    public static List<TagInstance> loadTagsForSourceText(String working_dir, String sourceTextHash) {
        String tagPath = working_dir + cgitDirectory.TAGS_PATH.getPath();
        List<TagInstance> tagHolder = new LinkedList<TagInstance>();

        try {
            String tag_data = FileUtil.readFile(tagPath);

            for (String tag_line : tag_data.split("\n")) {
                String[] params = tag_line.split(cgitDirectory.SPLITDELIMETER);
                String commentTextHash = params[params.length - 1];
                
                
                TagInstance currTag = tags.parseTag(tag_line);

                if (currTag != null && commentTextHash.equals(sourceTextHash)) {
                    tagHolder.add(currTag);
                }

            }
        } catch (Exception e) {

            return null;
        }

        return tagHolder;
    }

    /**
     * Used to convert the TagInstance to a string so we can save it to the tag file
     * 
     * @param tag the instance for the tag we wont to turn into a string
     * @return the string version of the TagInstance
     */
    public static String tagToString(TagInstance tag) {
        String tagString = "";
        SimpleDateFormat formatter = new SimpleDateFormat(tag.DATE_FORMAT);

        tagString += tag.getOwner() + cgitDirectory.DELIMETER;
        tagString += new StringBuilder(formatter.format(tag.getDateAdded())) + cgitDirectory.DELIMETER;
        tagString += new StringBuilder(formatter.format(tag.getDateModified())) + cgitDirectory.DELIMETER;
        tagString += Integer.toString(tag.getTextSection().getOffset()) + cgitDirectory.DELIMETER;
        tagString += Integer.toString(tag.getTextSection().getLength()) + cgitDirectory.DELIMETER;
        tagString += tag.getTagType().getName() + cgitDirectory.DELIMETER;
        tagString += tag.getTagType().getPathString() + cgitDirectory.DELIMETER;
        tagString += tag.getSourceHash();

        return tagString;
    }

    static List<TagInstance> parseTagFile(String content, SourceText text) {
        
        List<TagInstance> tagHolder = new LinkedList<TagInstance>();
        
        for(String line : content.split("\n"))
        {
            try{
            TagInstance tempTag = tags.parseTag(line);
            
            String [] params = line.split(cgitDirectory.SPLITDELIMETER);
            String hash = params[params.length-1];
            
            if(tempTag != null && hash.equals(text.getContentHash()))
            {
                tagHolder.add(tempTag);
            }
            } catch (Exception e){}
        }
        
        return tagHolder;
    }

    private static List<TagInstance> getAllTags(String working_dir) {
       String path = working_dir + cgitDirectory.TAGS_PATH.getPath();
        
        String tag_data = FileUtil.readFile(path);
        List<TagInstance> tag_holder = new LinkedList<TagInstance>();
        
        for(String line : tag_data.split("\n"))
        {
            try{
            TagInstance temp = tags.parseTag(line);
            if(temp != null)
            {
                tag_holder.add(temp);
            }
            } catch (Exception e){ MyLogger.LogMessageToConsole(tags.class, e.getMessage(), LogType.ERROR);
            }
        }
        
        return tag_holder;
    }
}
