package cgit;

import java.util.ArrayList;
import model.Blob;
import model.BlobContainer;
import model.BlobTree;
import model.cgitDirectory;

public class Branch {
    
    public static void newBranch(String working_dir, String branchName) throws Exception{
        //generate blobtree for comments and tags
        String comments_path = working_dir + cgitDirectory.COMMENTS_PATH.getPath();
        String tags_path = working_dir + cgitDirectory.TAGS_PATH.getPath();
        
        Blob comments = new Blob(FileUtil.readFile(comments_path));
        Blob tags =  new Blob(FileUtil.readFile(tags_path));
        
        BlobContainer comment_container = new BlobContainer(comments, "comments");
        BlobContainer tag_container = new BlobContainer(tags, "tags");
        
        ArrayList<BlobContainer> containers = new ArrayList<BlobContainer>();
        containers.add(comment_container);
        containers.add(tag_container);
        
        BlobTree tree = new BlobTree(containers);
        
        //get hash for blobtree
        String hash = tree.generateHash();
        
        //write the hash to the current head
        Head.writeHead(working_dir, hash);
        
        //write the objects
        Objects.writeBlobTree(working_dir, tree);
        
        //write the head to the heads folder
        String heads_path = working_dir + cgitDirectory.HEADS_PATH.getPath() + "/" + branchName;
        FileUtil.writeFile(false, heads_path, hash);
    }
    
    /* debug purposed only */
    public static void main(String [] args) throws Exception
    {
       Branch.newBranch("/Volumes/DATA/Users/andrewjorgensen/temp/qda_project", "newbranch");
    }
    
}
