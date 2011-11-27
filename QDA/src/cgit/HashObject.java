package cgit;

import java.io.*;
import java.security.MessageDigest;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HashObject {

    private static String convertToHex(byte[] data) {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            int halfbyte = (data[i] >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                if ((0 <= halfbyte) && (halfbyte <= 9)) {
                    buf.append((char) ('0' + halfbyte));
                } else {
                    buf.append((char) ('a' + (halfbyte - 10)));
                }
                halfbyte = data[i] & 0x0F;
            } while (two_halfs++ < 1);
        }
        return buf.toString();
    }

    public static String hashString(String toHash) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] sha1hash = new byte[40];
            md.update(toHash.getBytes("iso-8859-1"), 0, toHash.length());
            sha1hash = md.digest();
            return convertToHex(sha1hash);
        } catch (Exception ex) {
            return "";
        }
    }

    public static String hashObject(String filename) {
        try {
            String file_data = FileUtil.readFile(filename);
            int filesize = (int) FileUtil.getFilesize(filename);

            String toEncrypt = "blob " + filesize + "\0" + file_data;

            return hashString(toEncrypt);
        } catch (Exception ex) {
            Logger.getLogger(HashObject.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }

    /* Debugging purposes only */
    public static void main(String[] args) throws Exception {

        System.out.println(hashObject("/Volumes/DATA/Users/andrewjorgensen/test.txt"));
        System.out.println(hashObject("/Volumes/DATA/Users/andrewjorgensen/Downloads/test.txt"));
        System.out.println(hashString("blob 0\0"));

    }
}