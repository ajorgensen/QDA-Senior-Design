/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgit;

import java.io.*;
import model.cgitDirectory;

/**
 *
 * @author andrewjorgensen
 */
public class description {
    
    private String working_dir = "";
    
    public description(String working_dir)
    {
        this.working_dir = working_dir;
    }
    
    public String readDescription()
    {
        String description = "";
        try {
            BufferedReader in = new BufferedReader(new FileReader(this.working_dir + cgitDirectory.DESCRIPTION_PATH.getPath()));
            String str;
            while ((str = in.readLine()) != null)
            {
                description += str;
            }
            
            in.close();
            
        } catch (IOException e) {
            MyLogger.LogMessageToConsole(this, "Error reading description file from: " + this.working_dir + cgitDirectory.DESCRIPTION_PATH.getPath(), LogType.ERROR);
        }
        
        return description;
    }
    
    public boolean writeDescription(String new_description, boolean append)
    {
        boolean success = false;
        String description_path = this.working_dir + cgitDirectory.DESCRIPTION_PATH.getPath();
        
        try{
            BufferedWriter out = new BufferedWriter(new FileWriter(description_path, append));

            out.write(new_description);
            out.close();
        
        } catch (IOException e){
            MyLogger.LogMessageToConsole(this, "Error writing description file to: " + description_path, LogType.ERROR);
        }
        
        return success;
    }
    
}
