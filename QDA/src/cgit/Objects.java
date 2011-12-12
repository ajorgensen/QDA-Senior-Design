package cgit;

import model.Blob;
import model.BlobTree;
import model.Commit;
import model.cgitDirectory;
import java.io.File;

/**
 * This class is to handle the interactions between objects and the file system.
 * Anything that inherits from GitObjecct should be included in here.
 * 
 * TODO: Look into the possibility of further abstracting this class to simply handle the interaction between gitObject and the filesystem so we dont need special cases
 * 
 * @author andrewjorgensen
 */
public class Objects {
    
    /**
     * writeBlob
     * 
     * Handles writing the blob object to disk. The blob object is just static text and the object is written based on this hash.
     * 
     * @param working_dir
     * @param blob object to be written to disk
     */
    public static void writeBlob(String working_dir, Blob blob)
    {
        String object_path = working_dir + cgitDirectory.OBJECTS_PATH.getPath() + File.separator + blob.generateHash();
        
        FileUtil.writeFile(false, object_path, blob.getContent());
    }
    
    /**
     * writeBlobTree
     * 
     * This function will take an entire BlobTree and walk through it and write it to disk
     * 
     * @param working_dir
     * @param tree object which holds the blob objects
     */
    public static void writeBlobTree(String working_dir, BlobTree tree)
    {
        //Write the tree object
        String blobtree = working_dir + cgitDirectory.OBJECTS_PATH.getPath() + File.separator + tree.generateHash();
        String blobTreeData = "";
        
        for(Blob blob : tree.getBlobs())
        {
            String object_path = working_dir + cgitDirectory.OBJECTS_PATH.getPath() + File.separator + blob.generateHash();
            
            blobTreeData += blob.generateHash() + " " + blob.getFilename() + "\n";
            
            FileUtil.writeFile(false, object_path, blob.getContent());
        }
        
        FileUtil.writeFile(false, blobtree, blobTreeData);
    }
    
    /**
     * writeCommit
     * 
     * This writes the commit object to the objects folder. Each commit is a flat file object like blob is.
     * 
     * @param working_dir
     * @param commit object
     */
    public static void writeCommit(String working_dir, Commit commit)
    {
        String path = working_dir + cgitDirectory.OBJECTS_PATH.getPath() + File.separator + commit.generateHash();
        
        FileUtil.writeFile(false, path, commit.generateRawContent());
    }
    
    /**
     * readHash
     * 
     * This will look at the object in question and read the contents of the file at the sepcific hash
     * 
     * @param hash
     * @param working_dir
     * @return String with the contents of the file.
     */
    public static String readHash(String hash, String working_dir)
    {
        String path = working_dir + cgitDirectory.OBJECTS_PATH.getPath() + File.separator + hash;
        
        return FileUtil.readFile(path);
    }
    
    /* For Debuging Purposed */
    public static void main(String [] args)
    {
        System.out.println(Objects.readHash("434589f206ebbe5150d927bb91ec333c5857936e", "/Volumes/DATA/Users/andrewjorgensen/temp/qda_project")); 
    }
}
