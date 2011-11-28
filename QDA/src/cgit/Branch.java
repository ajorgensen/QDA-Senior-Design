package cgit;

import java.util.ArrayList;
import model.Blob;
import model.BlobContainer;
import model.BlobTree;
import model.cgitDirectory;
import model.Commit;

public class Branch {

    public static void newBranch(String working_dir, String branchName) throws Exception {
        //generate blobtree for comments and tags
        BlobTree tree = Branch.generateBlobTree(working_dir);

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

    public static void commit(String working_dir, String commiter, String message) { 
        
        
    }

    private static BlobTree generateBlobTree(String working_dir) {
        String comments_path = working_dir + cgitDirectory.COMMENTS_PATH.getPath();
        String tags_path = working_dir + cgitDirectory.TAGS_PATH.getPath();
        
        Blob comments;
        Blob tags;
        try {
            comments = new Blob(FileUtil.readFile(comments_path));
            tags = new Blob(FileUtil.readFile(tags_path));
        } catch (Exception e) {
            return null;
        }

        BlobContainer comment_container = new BlobContainer(comments, "comments");
        BlobContainer tag_container = new BlobContainer(tags, "tags");

        ArrayList<BlobContainer> containers = new ArrayList<BlobContainer>();
        containers.add(comment_container);
        containers.add(tag_container);

        return new BlobTree(containers);
    }

    /* debug purposed only */
    public static void main(String[] args) throws Exception {
        Branch.newBranch("/Volumes/DATA/Users/andrewjorgensen/temp/qda_project", "newbranch");
    }
}
