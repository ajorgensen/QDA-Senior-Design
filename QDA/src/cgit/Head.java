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
    
    public static String readRefHead(String working_dir, String branch_name)
    {
        String path = working_dir + cgitDirectory.HEADS_PATH.getPath() + "/" + branch_name;
        
        return FileUtil.readFile(path);
    }
    
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
