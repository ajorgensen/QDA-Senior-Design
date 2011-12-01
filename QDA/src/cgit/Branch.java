package cgit;

import java.util.ArrayList;
import model.Blob;
import model.BlobTree;
import model.cgitDirectory;
import model.Commit;

public class Branch {

    //REMEMBER a banch is just a named commit
    public static void newBranch(String working_dir, String branchName) {
        String hash = Branch.getCurrentCommitHash(working_dir);

        //write the head to the heads folder
        String heads_path = working_dir + cgitDirectory.HEADS_PATH.getPath() + "/" + branchName;
        FileUtil.writeFile(false, heads_path, hash);

        Head.writeHead(working_dir, branchName);
    }

    public static void commit(String working_dir, String commiter, String message) {
        BlobTree tree = Branch.generateBlobTree(working_dir);

        //The parent of this commit will be the commit before it
        ArrayList<Commit> parents = new ArrayList<Commit>();
        String parent_hash = Branch.getCurrentCommitHash(working_dir);
        
        if (!parent_hash.isEmpty()) {
            Commit parent_commit = Commit.parseHash(parent_hash, working_dir);
            parents.add(parent_commit);
        }

        Commit new_commit = new Commit(parents, tree, commiter, message);

        //Write the blob tree
        Objects.writeBlobTree(working_dir, tree);

        //write the commit object
        Objects.writeCommit(working_dir, new_commit);

        //Update the branch head
        Head.writeRefHead(working_dir, Branch.getCurrentBranchName(working_dir), new_commit.generateHash());
    }

    public static String getCurrentBranchName(String working_dir) {
        return Head.readHead(working_dir);
    }

    public static String getCurrentCommitHash(String working_dir) {
        String hash = Head.readRefHead(working_dir, Branch.getCurrentBranchName(working_dir));

        return hash;
    }
    
    public static String [] getAllBranchNames(String working_dir)
    {
        String heads_directory = working_dir + cgitDirectory.HEADS_PATH.getPath();
        
        String [] fileNames = FileUtil.listFilesInDirectory(heads_directory);
        
        return fileNames;
    }
    
    public static void commitWalker(String working_dir, Commit starting_commit)
    {   
        
        String message = "\ncommit " + starting_commit.generateHash() + "\n" + starting_commit.generateRawContent();
        MyLogger.LogMessageToConsole(Branch.class, message, LogType.DEBUG);
        
        if(starting_commit.hasParents())
        {
            for(Commit parent : starting_commit.getParents())
            {
                Branch.commitWalker(working_dir, parent);
            }
        }
    }

    private static BlobTree generateBlobTree(String working_dir) {
        String comments_path = working_dir + cgitDirectory.COMMENTS_PATH.getPath();
        String tags_path = working_dir + cgitDirectory.TAGS_PATH.getPath();

        Blob comments = new Blob(FileUtil.readFile(comments_path), "comments");
        Blob tags = new Blob(FileUtil.readFile(tags_path), "tags");

        ArrayList<Blob> blobs = new ArrayList<Blob>();
        blobs.add(comments);
        blobs.add(tags);

        return new BlobTree(blobs);
    }

    /* debug purposed only */
    public static void main(String[] args) throws Exception {
        //Branch.newBranch("/Volumes/DATA/Users/andrewjorgensen/temp/qda_project", "newbranch");
        String working_dir = "/Volumes/DATA/Users/andrewjorgensen/temp/qda_project";
        //Branch.commitWalker(working_dir, Commit.parseHash(Branch.getCurrentCommitHash(working_dir), working_dir));
        Branch.getAllBranchNames(working_dir);
    }
}
