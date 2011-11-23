package cgit;

import java.io.File;
import java.util.ArrayList;
import model.FileStructure;
import model.cgitDirectory;

public class setup {

    public static boolean setup_qda_directory(String working_dir) {
        boolean success = false;

        ArrayList<FileStructure> directoryStructure = cgitDirectory.getDirectoryStructure();

        //walk through the array list and create each of the directories
        for(FileStructure handle : directoryStructure)
        {
            boolean opSuccess = true;
            if(handle.isFolder())
            {
                opSuccess = cgitDirectory.createDirectory(working_dir + handle.getPath());
            } else {
                opSuccess = cgitDirectory.createFile(working_dir + handle.getPath());
            }
            
            if(opSuccess == true)
            {
                success = true;
            }
        }

        return success;
    }
}
