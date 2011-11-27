/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cgit;

import model.Blob;
import model.BlobContainer;
import model.BlobTree;
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
        
        for(BlobContainer container : tree.getBlobs())
        {
            String object_path = working_dir + cgitDirectory.OBJECTS_PATH.getPath() + "/" + container.getBlob().generateHash();
            
            blobTreeData += container.getBlob().generateHash() + " " + container.getFilename() + "\n";
            
            FileUtil.writeFile(false, object_path, container.getBlob().getContent());
        }
        
        FileUtil.writeFile(false, blobtree, blobTreeData);
    }
    
    /* For Debuging Purposed */
    public static void main(String [] args)
    {
        Blob blob = new Blob("hello world!");
        Objects.writeBlob("/Volumes/DATA/Users/andrewjorgensen/temp/qda_project", blob);
    }
}
