/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UnitTests;

import model.*;
import cgit.*;

public class cgitTests {
    
    public static void main(String [] args)
    {
        String working_dir = "/Volumes/DATA/Users/andrewjorgensen/temp/qda_project";
        
        //Setup the cgit directory
        setup.setup_qda_directory(working_dir);
        
        //Add some comments
        comments comments = new comments(working_dir);
        Comment comment = comments.new_comment("aj", new TextSection(0,10), "comment", "SourceText1.txt");
        
        comments.addComment(comment);
        
        comment = comments.new_comment("ryan", new TextSection(0,10), "1134", "SourceText1.txt");
        
        comments.addComment(comment);
        
        //Commit changes
        Branch.commit(working_dir, "aj", "first commit");
        
        //some more comments
        comment = comments.new_comment("hemal", new TextSection(0,10), "1134", "SourceText1.txt");
        
        Branch.commit(working_dir, "aj", "second commit");
        
        Branch.newBranch(working_dir, "new_branch");
        
        comment = comments.new_comment("hemal", new TextSection(0,10), "hello world", "SourceText1.txt");
        comments.addComment(comment);
        
        Branch.commit(working_dir, "hemal", "new commit");
    }  
}
