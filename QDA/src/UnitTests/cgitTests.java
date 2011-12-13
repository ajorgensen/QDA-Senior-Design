/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UnitTests;

import model.*;
import cgit.*;
import java.util.LinkedList;
import java.util.List;

public class cgitTests {
    
    public static void main(String [] args)
    {
        String working_dir = "/Volumes/DATA/Users/andrewjorgensen/temp/QDA2";
        
        //Setup the cgit directory
        setup.setup_qda_directory(working_dir);
        
        Branch.commit(working_dir, "aj", "first commit");
        
        Comment comment = Comment.generateNewComment("aj", new TextSection(0, 10), "comment");
        
        List<Comment> comment_holder = new LinkedList<Comment>();
        
        comment_holder.add(comment);
        
        comments.saveComments(working_dir, comment_holder, new SourceText("/Volumes/DATA/Users/andrewjorgensen/temp/QDA2/SourceDoc.txt"));
        
        Branch.commit(working_dir, "aj", "second commit");
        
        comment_holder.remove(comment);
        
        comment = Comment.generateNewComment("user2", new TextSection(0,10), "comment 2");
        
        comment_holder.add(comment);
        
        comments.saveComments(working_dir, comment_holder, new SourceText("/Volumes/DATA/Users/andrewjorgensen/temp/QDA2/SourceDoc1.txt"));
        
        Branch.commit(working_dir, "aj", "third commit");
        
        List<Comment> comments_holder = comments.loadCommentsForSourceText(working_dir, new SourceText("/Volumes/DATA/Users/andrewjorgensen/temp/QDA2/SourceDoc1.txt"));
    
        for(Comment currComment : comment_holder)
        {
            System.out.println(currComment.getComment());
        }
    }  
}
