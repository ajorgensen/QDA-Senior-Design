package cgit;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import cgit.MyLogger;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class FileUtil {

    /**
     * readFile
     * 
     * This function takes a filepath as an argument and returns the contents of the file in a string object.
     * 
     * NOTE: If the file cannot be read it simply returns a blank string.
     * TODO: Have this function throw an exception if it cannot read a file. But personally I hate exception chains.
     * 
     * @param filepath is self explanatory
     * @return string with the file contents
     */
    public static String readFile(String filepath) {
        try {
            FileInputStream stream = new FileInputStream(new File(filepath));
            FileChannel fc = stream.getChannel();
            MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
            /* Instead of using default, pass in a decoder. */
            stream.close();
            return Charset.defaultCharset().decode(bb).toString();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * writeFile
     * 
     * Write the contents of a file to the filepath specified. 
     * 
     * @param append determines whether the contents will be appended to the end of the file or whether it should overwrite the file completely
     * @param filepath 
     * @param data the data that will be written to the file
     */
    public static void writeFile(boolean append, String filepath, String data) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(filepath, append));

            out.write(data);
            out.close();

            MyLogger.LogMessageToConsole(FileUtil.class, "Wrote to: " + filepath, LogType.DEBUG);
        } catch (Exception e) {
            MyLogger.LogMessageToConsole(FileUtil.class, "Error writing description file to: " + filepath, LogType.ERROR);
        }
    }

    /**
     * listFileInDirectory
     * 
     * Returns a string array of the file names within a directory
     * 
     * @param directory_path
     * @return String[] with the filenames in it
     */
    public static String[] listFilesInDirectory(String directory_path) {

        String files;
        File folder = new File(directory_path);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {

            if (listOfFiles[i].isFile()) {
                files = listOfFiles[i].getName();
            }
        }


        int num_files = listOfFiles.length;
        String[] fileNames = new String[num_files];

        for (int i = 0; i < listOfFiles.length; i++) {
            fileNames[i] = listOfFiles[i].getName();
        }

        return fileNames;
    }

    /**
     * 
     * gets the filesize of the file specified
     * 
     * @param filepath
     * @return long length of the file
     * @throws Exception if the file cannot be read
     */
    public static long getFilesize(String filepath) throws Exception {
        File file = new File(filepath);

        if (!file.exists()) {
            throw new Exception();
        }

        return file.length();
    }

    /**
     * Copy file from one locaiton to another
     * @param from location
     * @param to location
     */
    public static void copyFile(String from, String to) {
        try {            
            File inputFile = new File(from);
            File outputFile = new File(to);

            FileReader in = new FileReader(inputFile);
            FileWriter out = new FileWriter(outputFile);
            int c;

            while ((c = in.read()) != -1) {
                out.write(c);
            }

            in.close();
            out.close();
        } catch (Exception e) {
            MyLogger.LogMessageToConsole(FileUtil.class, e.toString(), LogType.ERROR);
        }
    }
}
