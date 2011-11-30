package cgit;

import java.util.ArrayList;
import model.Blob;
import model.BlobTree;
import model.cgitDirectory;
import model.Commit;

public class Branch {

    //REMEMBER a banch is just a named commit
    public static void newBranch(String working_dir, String branchName) throws Exception {
        /*
         * psuedo:
         * 
         * update HEAD
         * 
         */
        
        Head.writeHead(working_dir, branchName);
        
        String hash = Branch.getCurrentBranchHash();
        
        //write the head to the heads folder
        String heads_path = working_dir + cgitDirectory.HEADS_PATH.getPath() + "/" + branchName;
        FileUtil.writeFile(false, heads_path, hash);
    }

    public static void commit(String working_dir, String commiter, String message) { 
        
        
    }
    
    public static String getCurrentBranchName(){
        return "";
    }
    
    public static String getCurrentBranchHash(){
        return "";
    }

    private static BlobTree generateBlobTree(String working_dir) {
        String comments_path = working_dir + cgitDirectory.COMMENTS_PATH.getPath();
        String tags_path = working_dir + cgitDirectory.TAGS_PATH.getPath();
        
        Blob comments = new Blob(FileUtil.readFile(comments_path), "comments");;
        Blob tags = new Blob(FileUtil.readFile(tags_path), "tags");
        
        ArrayList<Blob> blobs = new ArrayList<Blob>();
        blobs.add(comments);
        blobs.add(tags);

        return new BlobTree(blobs);
    }

    /* debug purposed only */
    public static void main(String[] args) throws Exception {
        Branch.newBranch("/Volumes/DATA/Users/andrewjorgensen/temp/qda_project", "newbranch");
    }
}
