/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgit;

import java.io.File;
import java.util.ArrayList;
import model.FileStructure;
import model.QdaDirectory;

/**
 *
 * @author andrewjorgensen
 */
public class setup {

    public static boolean setup_qda_directory(String working_dir) {
        boolean success = false;

        ArrayList<FileStructure> directoryStructure = QdaDirectory.getDirectoryStructure();

        //walk through the array list and create each of the directories
        for(FileStructure handle : directoryStructure)
        {
            boolean opSuccess = true;
            if(handle.isFolder())
            {
                opSuccess = QdaDirectory.createDirectory(working_dir + handle.getPath());
            } else {
                opSuccess = QdaDirectory.createFile(working_dir + handle.getPath());
            }
            
            if(opSuccess == true)
            {
                success = true;
            }
        }

        return success;
    }
}
