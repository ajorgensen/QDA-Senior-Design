package cgit;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import model.Comment;
import model.TextSection;
import model.cgitDirectory;

public class comments {

    private String working_dir;
    private ArrayList<Comment> comments;
    private final String delimeter = "|";

    public comments(String working_dir) {
        this.working_dir = working_dir;
        this.comments = new ArrayList<Comment>();
        this.loadComments();
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
        this.saveComments();
    }

    public void removeComment(Comment comment) {
        this.comments.remove(comment);
        this.saveComments();
    }

    public ArrayList<Comment> getCommentsForFile(String filepath) {
        ArrayList<Comment> matchingComments = new ArrayList<Comment>();

        for (Comment comment : this.comments) {
            if (comment.getSourcePath().equals(filepath)) {
                matchingComments.add(comment);
            }
        }

        return matchingComments;
    }

    public ArrayList<Comment> getComments() {
        return this.comments;
    }

    private void loadComments() {
        String comment_path = this.working_dir + cgitDirectory.COMMENTS_PATH.getPath();

        try {
            BufferedReader in = new BufferedReader(new FileReader(comment_path));
            String str;
            int line_num = 0;

            //read each line of the comment file and add it to the arraylist
            while ((str = in.readLine()) != null) {
                Comment comment = this.process_comment(str);
                if (comment != null) {
                    comments.add(comment);
                } else {
                    MyLogger.LogMessageToConsole(this, "Error reading comment on line " + line_num, LogType.ERROR);
                }
            }
            in.close();
        } catch (IOException e) {
            MyLogger.LogMessageToConsole(this, "Error reading description file from: " + comment_path, LogType.ERROR);
        }
    }

    private void saveComments() {
        String comment_path = this.working_dir + cgitDirectory.COMMENTS_PATH.getPath();

        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(comment_path, false));

            for (Comment comment : this.comments) {
                this.writeComment(out, comment);
            }

            out.close();
            MyLogger.LogMessageToConsole(this, "Wrote to: " + comment_path, LogType.DEBUG);
        } catch (IOException e) {
            MyLogger.LogMessageToConsole(this, "Error writing description file to: " + comment_path, LogType.ERROR);
        }
    }

    private void writeComment(BufferedWriter out, Comment comment) throws IOException {
        out.write(this.commentToString(comment) + "\n");
    }

    public static final Comment process_comment(String comment_string) {
        String[] comment_params = comment_string.split("\\" + cgitDirectory.DELIMETER);

        //TODO: Error checking on split....did we actually get anything back?
        if (comment_params.length != 9) {
            return null;
        }

        //Format will be:
        // username|dateAdded|dateModified|startingPos|startingLine|endingPos|endingLine|comment|filepath
        try {

            String uname;
            Date dateAdded;
            Date dateModified;
            int offset;
            int length;
            String comment_text;
            String sourceFilePath;

            uname = comment_params[0];
            dateAdded = new SimpleDateFormat(Comment.DATE_FORMAT).parse(comment_params[1]);
            dateModified = new SimpleDateFormat(Comment.DATE_FORMAT).parse(comment_params[2]);
            offset = Integer.parseInt(comment_params[3]);
            length = Integer.parseInt(comment_params[4]);
            comment_text = comment_params[7];
            sourceFilePath = comment_params[8];

            return new Comment(uname, dateAdded, dateModified, new TextSection(offset, length), comment_text, sourceFilePath);
        } catch (java.text.ParseException e) {
            MyLogger.LogMessageToConsole(null, "Error parsing date", LogType.ERROR);
            return null;
        }
    }

    public static final Comment new_comment(String uname, TextSection selectedText, String comment_text, String sourceFilePath) {
        Date now = new Date();
        return new Comment(uname, now, now, selectedText, comment_text, sourceFilePath);
    }

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
}
