/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgit;

import java.io.*;
import java.util.*;
import model.SingleComment;
import model.QdaDirectory;

/**
 *
 * @author andrewjorgensen
 */
public class comments {

    private String working_dir;
    private ArrayList<SingleComment> comments;
    private final String delimeter = "|";

    public comments(String working_dir) {
        this.working_dir = working_dir;
        this.comments = new ArrayList<SingleComment>();
        this.loadComments();
    }

    public void addComment(SingleComment comment) {
        this.comments.add(comment);
        this.saveComments();
    }

    public void removeComment(SingleComment comment) {
        this.comments.remove(comment);
        this.saveComments();
    }
    
    public ArrayList<SingleComment> getCommentsForFile(String filepath)
    {
        ArrayList<SingleComment> matchingComments = new ArrayList<SingleComment>();
        
        for(SingleComment comment : this.comments)
        {
            if(comment.getSourcePath().equals(filepath))
            {
                matchingComments.add(comment);
            }
        }
        
        return matchingComments;
    }
    
    public ArrayList<SingleComment> getComments()
    {
        return this.comments;
    }
    
    private void loadComments()
    {
        String comment_path = this.working_dir + QdaDirectory.COMMENTS_PATH.getPath();

        try {
            BufferedReader in = new BufferedReader(new FileReader(comment_path));
            String str;
            int line_num = 0;

            //read each line of the comment file and add it to the arraylist
            while ((str = in.readLine()) != null) {
                SingleComment comment = SingleComment.process_comment(str);
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
        String comment_path = this.working_dir + QdaDirectory.COMMENTS_PATH.getPath();

        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(comment_path, false));

            for (SingleComment comment : this.comments) {
                this.writeComment(out, comment);
            }

            out.close();
            MyLogger.LogMessageToConsole(this, "Wrote to: " + comment_path, LogType.DEBUG);
        } catch (IOException e) {
            MyLogger.LogMessageToConsole(this, "Error writing description file to: " + comment_path, LogType.ERROR);
        }
    }

    private void writeComment(BufferedWriter out, SingleComment comment) throws IOException {
        out.write(comment.toString() + "\n");
    }
}
