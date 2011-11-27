package cgit;

import java.util.logging.Level;
import java.util.logging.Logger;
import model.cgitDirectory;

public class Head {
    
    public static String readHead(String working_dir)
    {
        String headPath = working_dir + cgitDirectory.HEAD_PATH.getPath();
        try {
            return FileUtil.readFile(headPath);
        } catch (Exception ex) {
            Logger.getLogger(Head.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }
    
    public static void writeHead(String working_dir, String newHead)
    {
        String headPath = working_dir + cgitDirectory.HEAD_PATH.getPath();
        
        FileUtil.writeFile(false, headPath, newHead);
    }
    
    /* Debug only */
    public static void main(String [] args)
    {
        Head.writeHead("/Volumes/Data/users/andrewjorgensen/temp/qda_project", "123");
    }
    
}
