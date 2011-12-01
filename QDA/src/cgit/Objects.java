/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgit;

import model.Blob;
import model.BlobTree;
import model.Commit;
import model.cgitDirectory;

public class Objects {
    
    public static void writeBlob(String working_dir, Blob blob)
    {
        String object_path = working_dir + cgitDirectory.OBJECTS_PATH.getPath() + "/" + blob.generateHash();
        
        FileUtil.writeFile(false, object_path, blob.getContent());
    }
    
    public static void writeBlobTree(String working_dir, BlobTree tree)
    {
        //Write the tree object
        String blobtree = working_dir + cgitDirectory.OBJECTS_PATH.getPath() + "/" + tree.generateHash();
        String blobTreeData = "";
        
        for(Blob blob : tree.getBlobs())
        {
            String object_path = working_dir + cgitDirectory.OBJECTS_PATH.getPath() + "/" + blob.generateHash();
            
            blobTreeData += blob.generateHash() + " " + blob.getFilename() + "\n";
            
            FileUtil.writeFile(false, object_path, blob.getContent());
        }
        
        FileUtil.writeFile(false, blobtree, blobTreeData);
    }
    
    public static void writeCommit(String working_dir, Commit commit)
    {
        String path = working_dir + cgitDirectory.OBJECTS_PATH.getPath() + "/" + commit.generateHash();
        
        FileUtil.writeFile(false, path, commit.generateRawContent());
    }
    
    public static String readHash(String hash, String working_dir)
    {
        String path = working_dir + cgitDirectory.OBJECTS_PATH.getPath() + "/" + hash;
        
        return FileUtil.readFile(path);
    }
    
    /* For Debuging Purposed */
    public static void main(String [] args)
    {
        System.out.println(Objects.readHash("434589f206ebbe5150d927bb91ec333c5857936e", "/Volumes/DATA/Users/andrewjorgensen/temp/qda_project")); 
    }
}
