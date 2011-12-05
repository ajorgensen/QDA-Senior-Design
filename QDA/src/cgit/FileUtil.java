package cgit;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import cgit.MyLogger;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class FileUtil {

    public static String readFile(String filepath) {
        try {
            FileInputStream stream = new FileInputStream(new File(filepath));
            FileChannel fc = stream.getChannel();
            MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
            /* Instead of using default, pass in a decoder. */
            stream.close();
            return Charset.defaultCharset().decode(bb).toString();
        } catch (Exception e ){
            return "";
        }
    }

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

    public static long getFilesize(String filepath) throws Exception {
        File file = new File(filepath);

        if (!file.exists()) {
            throw new Exception();
        }

        return file.length();
    }
}
