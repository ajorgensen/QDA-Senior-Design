package cgit;

import java.io.*;
import java.security.MessageDigest;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HashObject {

    /**
     * convertToHex
     * 
     * This function takes a byte array and converts it to a hashed hex string.
     * This is used to take the data returned from the sha1 and turn it into a string that is easier to handle
     * 
     * @param data
     * @return string with the sha1 hash of the data
     */
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

    /**
     * hashString
     * 
     * This will take a string and then produce a sha1 hash of that string
     * 
     * @param toHash is the string to be hashed
     * @return sha1 hash of the string passed to the function
     */
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

    /**
     * hashObject
     * 
     * This function is used to hash Blob objects as a specific filepath. It reads the content
     * of the file and then produces a unique hash based on the the following algorith:
     * 
     * "blob " + filesize + "\0" + file_data
     * 
     * @param filepath
     * @return the hash string of the object
     */
    public static String hashObject(String filepath) {
        try {
            String file_data = FileUtil.readFile(filepath);
            int filesize = (int) FileUtil.getFilesize(filepath);

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