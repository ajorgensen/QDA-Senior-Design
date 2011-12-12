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

    public static final String delimeter = "|";

    /**
     * This function will handle writing the tags to disk.
     * 
     * @param working_dir is the directory that contains the project we are working on.
     * @param tagHolder the list of tags we want to save
     */
    private static void saveTags(String working_dir, ArrayList<TagInstance> tagHolder) {
        String tag_path = working_dir + cgitDirectory.TAGS_PATH.getPath();

        //clear the tag file out
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
    public static TagInstance parseTag(String data, Project project) throws ParseException {
        //TODO error checking

        //format is user|data added|date modified|offset|length|tag type|tag path|project name
        String[] tag_parameters = data.split(cgitDirectory.DELIMETER);

        String user;
        Date dateAdded;
        Date dateModified;
        TextSection selection;
        SourceText sourceText;
        MarkedUpText markedUpText;
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
            tagType = new TagType(tag_parameters[4]);
        } else {
            return null;
        }

        if (tag_parameters[6] != null) {
            sourceText = new SourceText(tag_parameters[5]);
        } else {
            return null;
        }
        
        if(tag_parameters[7] != null && !project.getName().equals(tag_parameters[7]))
        {
          return null;
        }
        

        return new TagInstance(user, dateAdded, dateModified, selection, new MarkedUpText(sourceText, project), tagType);
    }

    /**
     * This will load all the tags for a specific project
     * 
     * @param project is a reference to the project we want to load the tags from
     * @return an ArrayList of TagInstances
     */
    public static ArrayList<TagInstance> loadTagsForProject(Project project) {
        String tagPath = project.getLocalPath() + cgitDirectory.TAGS_PATH.getPath();
        ArrayList<TagInstance> tagHolder = new ArrayList<TagInstance>();


        try {
            String tag_data = FileUtil.readFile(tagPath);

            for (String tag_line : tag_data.split("\n")) {
                TagInstance currTag = tags.parseTag(tag_line, project);
                
                if(currTag != null)
                    tagHolder.add(currTag);
                
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
        tagString += tag.getMarkedUpText().getProject().getName();

        return tagString;
    }

    public static void main(String[] args) {
    }
}
