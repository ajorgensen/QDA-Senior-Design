package cgit;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.FileStructure;
import model.cgitDirectory;

public class setup {

    /**
     * This function starts at the working directory and creates a new repository at that location. Please note this function does not do a branch and commit
     * 
     * @param working_dir
     * @return boolean indicating whether the directory was created successfully or not.
     */
    public static boolean setup_qda_directory(String working_dir) {
        boolean success = false;
        
        //check if the directory exists
        if(!new File(working_dir).exists())
        {
            return false;
        } 
        else if(new File(working_dir + cgitDirectory.QDA_PATH).exists()) //TODO more checking to make sure the directory structure is still O.K.
        {
            MyLogger.LogMessageToConsole(setup.class, "cgit directory already exists", LogType.DEBUG);
            return true;
        }

        ArrayList<FileStructure> directoryStructure = cgitDirectory.getDirectoryStructure();

        //walk through the array list and create each of the directories
        for (FileStructure handle : directoryStructure) {
            boolean opSuccess = true;
            if (handle.isFolder()) {
                opSuccess = cgitDirectory.createDirectory(working_dir + handle.getPath());
            } else {
                opSuccess = cgitDirectory.createFile(working_dir + handle.getPath());
            }

            if (opSuccess == true) {
                success = true;
            }
        }
        
        Branch.newBranch(working_dir, "master");
        
        /*
        try {
            //generate new master branch.
            Head.writeHead(working_dir, "master");
            Branch.commit(working_dir, "aj", "first commit");
        } catch (Exception ex) {
            Logger.getLogger(setup.class.getName()).log(Level.SEVERE, null, ex);
            success = false;
        }
         * 
         */

        return success;
    }

    /* Debugging purposes only */
    public static void main(String[] args) throws Exception {
        setup_qda_directory("/Volumes/DATA/Users/andrewjorgensen/temp/qda_project");
    }
}
