/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgit;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import cgit.MyLogger;

public class FileUtil {

    public static String readFile(String filepath) throws Exception {

        BufferedReader in = new BufferedReader(new FileReader(filepath));
        String data = "";
        String str;
        int line_num = 0;

        //read each line of the comment file and add it to the arraylist
        while ((str = in.readLine()) != null) {
            data += str;
        }
        in.close();

        return data;

    }

    public static void writeFile(boolean append, String filepath, String data) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(filepath, append));
            
            out.append(data);
            out.close();
            
            MyLogger.LogMessageToConsole(null, "Wrote to: " + filepath, LogType.DEBUG);
        } catch (Exception e) {
            MyLogger.LogMessageToConsole(null, "Error writing description file to: " + filepath, LogType.ERROR);
        }
    }

    public static long getFilesize(String filepath) throws Exception {
        File file = new File(filepath);

        if (!file.exists()) {
            throw new Exception();
        }

        return file.length();
    }
}
