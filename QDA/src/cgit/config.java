/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgit;

import java.io.File;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import model.cgitDirectory;

/**
 *
 * @author andrewjorgensen
 */
public class config {
    
    String working_dir = "";

    public config(String working_dir) {
        
        this.working_dir = working_dir;
        this.readConfig(working_dir + cgitDirectory.CONFIG_PATH.getPath());
    }

    private void readConfig(String config_location) {     
        Properties p = new Properties();
        try {
            p.load(new FileInputStream(config_location));
            MyLogger.LogMessageToConsole(this, "Config file loaded correctly...", LogType.DEBUG);
            
            MyLogger.LogMessageToConsole(this, "Test Var: " + p.getProperty("testvar"), LogType.DEBUG);
        } catch (IOException ex) {
            MyLogger.LogMessageToConsole(this, "Error reading config file from: " + config_location, LogType.ERROR);
        }
    }
}
