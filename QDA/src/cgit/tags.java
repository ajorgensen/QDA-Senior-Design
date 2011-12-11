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

    
    private static void saveTags(Project project, ArrayList<TagInstance> tagHolder) {
        String tag_path = project.getLocalPath() + cgitDirectory.TAGS_PATH.getPath();

        FileUtil.writeFile(false, tag_path, "");

        for (TagInstance currTag : tagHolder) {
            FileUtil.writeFile(true, tag_path, tags.tagToString(currTag) + "\n");
        }
    }

    public static TagInstance parseTag(String data, Project project) throws ParseException {
        //TODO error checking

        //format is user|data added|date modified|offset|length|tag type|tag path|project name
        String[] tag_parameters = data.split("|");

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

    public static String tagToString(TagInstance tag) {
        String tagString = "";
        SimpleDateFormat formatter = new SimpleDateFormat(tag.DATE_FORMAT);

        tagString += tag.getOwner() + cgitDirectory.DELIMETER;
        tagString += new StringBuilder(formatter.format(tag.getDateAdded())) + cgitDirectory.DELIMETER;
        tagString += new StringBuilder(formatter.format(tag.getDateModified())) + cgitDirectory.DELIMETER;
        tagString += Integer.toString(tag.getTextSection().getOffset()) + cgitDirectory.DELIMETER;
        tagString += Integer.toString(tag.getTextSection().getLength()) + cgitDirectory.DELIMETER;
        tagString += tag.getTagType().getName() + cgitDirectory.DELIMETER;
        tagString += tag.getSourcePath() + cgitDirectory.DELIMETER;
        tagString += tag.getMarkedUpText().getProject().getName();

        return tagString;
    }

    public static void main(String[] args) {
    }
}
