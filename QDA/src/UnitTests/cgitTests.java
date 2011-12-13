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
        String working_dir = "/Volumes/DATA/Users/andrewjorgensen/temp/qda_project";
        
        //Setup the cgit directory
        setup.setup_qda_directory(working_dir);
       
        List<Comment> comment_holder = new LinkedList<Comment>();
        
        Comment comment = Comment.generateNewComment("aj", new TextSection(0,10), "comment", new MarkedUpText(new SourceText("/Volumes/DATA/Users/andrewjorgensen/temp/SourceText.txt"), new Project("project1", working_dir)));
            
        Branch.newBranch(working_dir, "master");
        Branch.commit(working_dir, "aj", "first commit");
        
       comment_holder.add(comment);
       
       comments.saveComments(working_dir, comment_holder, new Project("project1", working_dir));
       
       Branch.commit(working_dir, "aj", "second commit");
        
    }  
}
