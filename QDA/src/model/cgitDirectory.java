/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andrewjorgensen
 */
public class cgitDirectory {

    public static final FileStructure QDA_PATH = new FileStructure(true, "/cgit");
    public static final FileStructure HEAD_PATH = new FileStructure(false, QDA_PATH.getPath() + "/HEAD");
    public static final FileStructure ORIG_HEAD_PATH = new FileStructure(false, QDA_PATH.getPath() + "/ORIG_HEAD");
    public static final FileStructure OBJECTS_PATH = new FileStructure(true, QDA_PATH.getPath() + "/objects");
    public static final FileStructure REFS_PATH = new FileStructure(true, QDA_PATH.getPath() + "/refs");
    public static final FileStructure HEADS_PATH = new FileStructure(true, REFS_PATH.getPath() + "/heads");
    public static final FileStructure TAGS_PATH = new FileStructure(false, REFS_PATH.getPath() + "/tags");
    public static final FileStructure COMMENTS_PATH = new FileStructure(false, REFS_PATH.getPath() + "/comments");
    public static final String DELIMETER = "|";

    public static boolean createDirectory(String path) {
        boolean success = false;
        String qdaDir = path;
        File qdaDirHandle = new File(qdaDir);

        //check to see if the folder already exists
        if (!qdaDirHandle.exists()) {
            System.out.println("Creating " + qdaDir);
            success = qdaDirHandle.mkdir();
        } else {
            System.out.println("Directory '" + path + "' is already initialized.");
        }

        return success;
    }

    public static boolean createFile(String path) {
        boolean success = false;

        File fileHandle = new File(path);

        try {
            fileHandle.createNewFile();
            System.out.println("Created new file: " + path);
            success = true;
        } catch (IOException ex) {
            Logger.getLogger(cgitDirectory.class.getName()).log(Level.SEVERE, null, ex);
        }

        return success;
    }

    public static ArrayList<FileStructure> getDirectoryStructure() {
        ArrayList<FileStructure> directoryList = new ArrayList<FileStructure>();

        directoryList.add(QDA_PATH);
        directoryList.add(HEAD_PATH);
        directoryList.add(ORIG_HEAD_PATH);
        directoryList.add(OBJECTS_PATH);
        directoryList.add(REFS_PATH);
        directoryList.add(HEADS_PATH);
        directoryList.add(TAGS_PATH);
        directoryList.add(COMMENTS_PATH);

        return directoryList;
    }
}
