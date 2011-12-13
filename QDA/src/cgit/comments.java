package cgit;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import model.Comment;
import model.MarkedUpText;
import model.TextSection;
import model.cgitDirectory;

/**
 * 
 * @author andrewjorgensen
 */
public class comments {

    public static void saveComments(String working_dir, List<Comment> commentHolder) {
        String comment_path = working_dir + cgitDirectory.COMMENTS_PATH.getPath();

        //clear the file
        FileUtil.writeFile(false, comment_path, "");

        for (Comment comment : commentHolder) {
            FileUtil.writeFile(true, comment_path, comments.commentToString(comment));
        }
    }

    public static String commentToString(Comment comment) {
        String comment_string = "";
        SimpleDateFormat formatter = new SimpleDateFormat(comment.DATE_FORMAT);

        comment_string += comment.getOwner() + cgitDirectory.DELIMETER;
        comment_string += new StringBuilder(formatter.format(comment.getDateAdded())) + cgitDirectory.DELIMETER;
        comment_string += new StringBuilder(formatter.format(comment.getDateModified())) + cgitDirectory.DELIMETER;
        comment_string += Integer.toString(comment.getTextSection().getOffset()) + cgitDirectory.DELIMETER;
        comment_string += Integer.toString(comment.getTextSection().getLength()) + cgitDirectory.DELIMETER;
        comment_string += comment.getComment() + cgitDirectory.DELIMETER; //TODO we need to escape the new line characters so it doesnt break the parser when we pass them back in
        comment_string += comment.getSourcePath();

        return comment_string;
    }

    public static Comment parseComment(String comment_line, MarkedUpText markedUp) throws ParseException {
        //comment format: owner|date added|date modified|offset|length|comment|sourcePath

        String[] params = comment_line.split(cgitDirectory.DELIMETER);
        int i = 0;

        String owner;
        Date dateAdded;
        Date dateModified;
        TextSection section;
        String comment;
        String sourcePath;

        if (params[i] != null) { //i=0
            owner = params[i++];
        } else {
            return null;
        }

        if (params[i] != null) { //i=1
            dateAdded = new SimpleDateFormat(Comment.DATE_FORMAT).parse(params[i++]);
        } else {
            return null;
        }

        if (params[i] != null) { //i=2
            dateModified = new SimpleDateFormat(Comment.DATE_FORMAT).parse(params[i++]);
        } else {
            return null;
        }

        if (params[i] != null && params[i + 1] != null) { //i=3&4
            section = new TextSection(Integer.parseInt(params[i++]), Integer.parseInt(params[i++]));
        } else {
            return null;
        }

        if (params[i] != null) { //i=5
            comment = params[i++];
        } else {
            return null;
        }

        if (params[i] != null && params[i].equals(markedUp.getName())) { //i=5
            sourcePath = params[i++];
        } else {
            return null;
        }
        
        

        return new Comment(owner, dateAdded, dateModified, section, comment, markedUp);
    }

    public static ArrayList<Comment> loadCommentsForProject(MarkedUpText markUp) {
        ArrayList<Comment> commentHolder = new ArrayList<Comment>();
        String comment_path = markUp.getProject().getLocalPath() + cgitDirectory.COMMENTS_PATH.getPath();

        try {
            String data = FileUtil.readFile(comment_path);
            
            for(String line : data.split("\n"))
            {
                Comment tempComment = comments.parseComment(line, markUp);
                if(tempComment != null)
                {
                    commentHolder.add(tempComment);
                }
            }
        } catch (Exception e) {
            return null;
        }

        return null;
    }

    /*
    
    private String commentToString(Comment comment) {
    SimpleDateFormat formatter = new SimpleDateFormat(comment.DATE_FORMAT);
    
    return comment.getOwner() + cgitDirectory.DELIMETER
    + new StringBuilder(formatter.format(comment.getDateAdded())) + cgitDirectory.DELIMETER
    + new StringBuilder(formatter.format(comment.getDateModified())) + cgitDirectory.DELIMETER
    + Integer.toString(comment.getTextSection().getOffset()) + cgitDirectory.DELIMETER
    + Integer.toString(comment.getTextSection().getLength()) + cgitDirectory.DELIMETER
    + comment.getComment() + cgitDirectory.DELIMETER
    + comment.getSourcePath();
    }
     * 
     */
    public static void main(String[] args) {
        int i = 0;
        System.out.println("i++: " + i++);
        System.out.println("i++: " + i++);
        System.out.println("i++: " + i++);
        System.out.println("++i: " + ++i);
        System.out.println("i: " + i);
    }
}
