package cgit;

import java.util.logging.Level;
import java.util.logging.Logger;
import model.cgitDirectory;

/**
 * 
 * This object keeps track of the head for the .cgit directory.
 * The head is used as a pointer to the current branch that is checked out.
 * 
 * This object is not only responsible for read/write to the main head but also the 
 * head for each branch that points to the current commit object for each branch that has been created
 * 
 * @author andrewjorgensen
 */
public class Head {
    
    /**
     * readHead
     * 
     * This reads the head object in the root directory of the .cgit folder.
     * The head object holds the pointer to the current branch
     * 
     * @param working_dir
     * @return the branch name the is in the head
     */
    public static String readHead(String working_dir)
    {
        String headPath = working_dir + cgitDirectory.HEAD_PATH.getPath();
        try {
            return FileUtil.readFile(headPath);
        } catch (Exception ex) {
            Logger.getLogger(Head.class.getName()).log(Level.SEVERE, null, ex);
            return "master";
        }
    }
    
    /**
     * writeHead
     * 
     * Writes a new branch name to the head directory so we know what branch is currently checked out.
     * 
     * @param working_dir 
     * @param newHead the name of the current working branch
     */
    public static void writeHead(String working_dir, String newHead)
    {
        String headPath = working_dir + cgitDirectory.HEAD_PATH.getPath();
        
        FileUtil.writeFile(false, headPath, newHead);
    }
    
    /**
     * readRefHead
     * 
     * This function is used to read the commit that is currently in use by the branch.
     * 
     * @param working_dir
     * @param branch_name name of the branch as it appears in the branch folder
     * @return hash of the commit object that is associated with this branch
     */
    public static String readRefHead(String working_dir, String branch_name)
    {
        String path = working_dir + cgitDirectory.HEADS_PATH.getPath() + "/" + branch_name;
        
        return FileUtil.readFile(path);
    }
    
    
    /**
     * writeRefHead
     * 
     * This function write the commit hash to the branch head so we know what commit each branch is on
     * 
     * 
     * @param working_dir
     * @param branch_name
     * @param hash commit object hash
     */
    public static void writeRefHead(String working_dir, String branch_name, String hash)
    {
        String path = working_dir + cgitDirectory.HEADS_PATH.getPath() + "/" + branch_name;
        
        FileUtil.writeFile(false, path, hash);
    }
    
    /* Debug only */
    public static void main(String [] args)
    {
        Head.writeHead("/Volumes/Data/users/andrewjorgensen/temp/qda_project", "123");
    }
    
}
