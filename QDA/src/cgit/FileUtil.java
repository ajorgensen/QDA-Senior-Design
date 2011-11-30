package cgit;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import cgit.MyLogger;

public class FileUtil {

    public static String readFile(String filepath) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filepath));

            String newLine = System.getProperty("line.separator");

            StringBuilder sb = new StringBuilder();
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                sb.append(line).append(newLine);
            }
            return sb.toString();
        } catch (Exception e) {
            return "";
        }
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
