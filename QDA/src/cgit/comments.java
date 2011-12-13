package cgit;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import model.Comment;
import model.MarkedUpText;
import model.SourceText;
import model.TextSection;
import model.cgitDirectory;

/**
 * 
 * @author andrewjorgensen
 */
public class comments {

    public static void saveComments(String working_dir, List<Comment> commentHolder) {
        String comment_path = working_dir + cgitDirectory.COMMENTS_PATH.getPath();
        
        if(commentHolder.size() == 0)
            return;
        
        List<Comment> allComments = comments.getAllComments(working_dir);
        List<Comment> commentsForText = comments.loadCommentsForSourceText(working_dir, commentHolder.get(0).getSourceHash());
        
        List<Comment> toRemove = new LinkedList<Comment>();
        
        //Remove all the comments for this project
       for(Comment currComment : allComments)
       {
           for(Comment sourceComment : commentsForText)
           {
               if(currComment.isEqualTo(sourceComment))
               {
                   toRemove.add(currComment);
               }
           }
       }
       
       allComments.removeAll(toRemove);
       
       //add all the comments back in
       allComments.addAll(commentHolder);
      
       //clear the file out
        FileUtil.writeFile(false, comment_path, "");

        //write all the comments back to the file
        for (Comment comment : allComments) {
            FileUtil.writeFile(true, comment_path, comments.commentToString(comment) + "\n");
        }
    }

    public static String commentToString(Comment comment) {
        String comment_string = "";
        SimpleDateFormat formatter = new SimpleDateFormat(Comment.DATE_FORMAT);

        comment_string += comment.getOwner() + cgitDirectory.DELIMETER;
        comment_string += new StringBuilder(formatter.format(comment.getDateAdded())) + cgitDirectory.DELIMETER;
        comment_string += new StringBuilder(formatter.format(comment.getDateModified())) + cgitDirectory.DELIMETER;
        comment_string += Integer.toString(comment.getTextSection().getOffset()) + cgitDirectory.DELIMETER;
        comment_string += Integer.toString(comment.getTextSection().getLength()) + cgitDirectory.DELIMETER;
        comment_string += comment.getComment() + cgitDirectory.DELIMETER; //TODO we need to escape the new line characters so it doesnt break the parser when we pass them back in
        comment_string += comment.getSourceHash();

        return comment_string;
    }

    public static Comment parseComment(String comment_line) throws ParseException {
        //comment format: owner|date added|date modified|offset|length|comment|sourcePath

        String[] params = comment_line.split(cgitDirectory.SPLITDELIMETER);
        int i = 0;

        String owner;
        Date dateAdded;
        Date dateModified;
        TextSection section;
        String comment;
        String sourceHash;

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
        
        if (params[i] != null) { //i=6
            sourceHash = params[i++];
        } else {
            return null;
        }

        return new Comment(owner, dateAdded, dateModified, section, comment, sourceHash);
    }

    public static List<Comment> loadCommentsForSourceText(String working_dir, String sourceTextHash) {
        List<Comment> commentHolder = new LinkedList<Comment>();
        String comment_path = working_dir + cgitDirectory.COMMENTS_PATH.getPath();

        try {
            String data = FileUtil.readFile(comment_path);

            for (String line : data.split("\n")) {
                
                String[] params = line.split(cgitDirectory.SPLITDELIMETER);
                String commentTextHash = params[params.length - 1];

                Comment tempComment = comments.parseComment(line);
                if (tempComment != null && commentTextHash.equals(sourceTextHash)) {
                    commentHolder.add(tempComment);
                }
            }
        } catch (Exception e) {
            MyLogger.LogMessageToConsole(comments.class, e.getMessage(), LogType.ERROR);
        }

        return commentHolder;
    }
    
    public static List<Comment> parseCommentFile(String fileContents, SourceText text)
    {
        List<Comment> commentHolder = new LinkedList<Comment>();
        for(String line : fileContents.split("\n"))
        {
            try{
                Comment parsedComment = comments.parseComment(line);
                
                String [] params = line.split(cgitDirectory.SPLITDELIMETER);
                String hash = params[params.length-1];
                
                if(parsedComment != null && hash.equals(text.getContentHash()))
                    commentHolder.add(parsedComment);
            
            } catch (Exception e){MyLogger.LogMessageToConsole(comments.class, "Error parsing comment file", LogType.DEBUG);
            }
        }
        
        return commentHolder;
    }
    
    public static List<Comment> getAllComments(String working_dir)
    {
        String path = working_dir + cgitDirectory.COMMENTS_PATH.getPath();
        
        String comment_data = FileUtil.readFile(path);
        List<Comment> comment_holder = new LinkedList<Comment>();
        
        for(String line : comment_data.split("\n"))
        {
            try{
            Comment temp = comments.parseComment(line);
            if(temp != null)
            {
                comment_holder.add(temp);
            }
            } catch (Exception e){}
        }
        
        return comment_holder;
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
}
