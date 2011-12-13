package cgit;

import java.io.File;
import java.util.ArrayList;
import model.Blob;
import model.BlobTree;
import model.Comment;
import model.cgitDirectory;
import model.Commit;
import model.TagInstance;

public class Branch {
    
    //REMEMBER a banch is just a named 
    /**
     * newBranch
     * 
     * This function creates a new branch and updates the head object to reflect the new branch.
     * Please note that current it does not do a commit after it makes the branch and simply creates a branch
     * reference for the last commit done. This is a design choice and will probably stay. This also helps separation of concern.
     * To overcome this make sure you do a commit before you create a new branch.
     * 
     * @param working_dir refers to the root directory where the .cgit folder lives. This is usually in the same place as the source text.
     * @param branchName the name that will be used to reference the branch
     */
    public static void newBranch(String working_dir, String branchName) {
        String hash = Branch.getCurrentCommitHash(working_dir);

        //write the head to the heads folder
        String heads_path = working_dir + cgitDirectory.HEADS_PATH.getPath() + File.separator + branchName;
        FileUtil.writeFile(false, heads_path, hash);

        Head.writeHead(working_dir, branchName);
    }
    
    /**
     * commit
     * 
     * This function handles writing all the object snapshots for the commit.
     * 
     * @param working_dir refers to the root directory where the .cgit folder lives. This is usually in the same place as the source text.
     * @param commiter the username of the person commiting their changes
     * @param message the message that is attached with the commit
     */
    public static void commit(String working_dir, String commiter, String message) {
        BlobTree tree = Branch.generateBlobTree(working_dir);

        //The parent of this commit will be the commit before it.
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
    
    /**
     * checkoutBranch
     * 
     * This function is used to switch the current branch to another branch.
     * 
     * @param working_dir
     * @param branchName the name of the branch we want to switch to
     */
    public static void checkoutBranch(String working_dir, String branchName)
    {
        //check to make sure the branch exists
        String [] availableBranches = Branch.getAllBranchNames(working_dir);
        
        boolean contains = false;
        for(String currentBranch : availableBranches)
        {
         if(currentBranch.equals(branchName))
         {
             contains = true;
             break;
         }
        }
        
        if(!contains)
        {
            return; // We should through an exception here or something but im being lazy
        } else {
            Head.writeHead(working_dir, branchName);
        } 
    }
    
    /**
     * checkoutCommit
     * 
     * This function is used to revert to an older commit on a branch. Please note currently i assume you know what you are doing and you are not reverting to a commit on another branch.
     * This would be bad. If I have time I will add more rigorous checking later but for not I just want to get this working.
     * 
     * So remember kids, if the commit hash is not on the same branch as the one that is currently checked out...DONT DO IT. If you must switch to a commit on another branch please switch
     * the branch first and then revert to the commit. That is all.
     * 
     * @param working_dir
     * @param commitHash the hash of the commit object that exits on the current branch
     */
    public static void checkoutCommit(String working_dir, String commitHash)
    {
        //make sure the commit hash is correct
        Commit newCommit = Commit.parseHash(commitHash, working_dir);
        
        if(newCommit == null)
            return; //again we should through an exception or return some error but for the moment im lazy
        
        Head.writeRefHead(working_dir, Branch.getCurrentBranchName(working_dir), commitHash);
    }

    /**
     * getCurrentBranchName
     * 
     * This function get the string name of the current working branch
     * 
     * @param working_dir refers to the root directory where the .cgit folder lives. This is usually in the same place as the source text.
     * @return the string name of the working branch
     */
    public static String getCurrentBranchName(String working_dir) {
        return Head.readHead(working_dir);
    }

    /**
     * getCurrentCommitHash
     * 
     * This returned the hash of the current commit object
     * 
     * @param working_dir refers to the root directory where the .cgit folder lives. This is usually in the same place as the source text.
     * @return string hash of the current commit object
     */
    public static String getCurrentCommitHash(String working_dir) {
        String hash = Head.readRefHead(working_dir, Branch.getCurrentBranchName(working_dir));

        return hash;
    }
    
    
    /**
     * getAllBranchNames
     * 
     * gets of the names of the branches available to checkout
     * 
     * @param working_dir refers to the root directory where the .cgit folder lives. This is usually in the same place as the source text.
     * @return string array of all of the branch names available for checkout
     */
    public static String [] getAllBranchNames(String working_dir)
    {
        String heads_directory = working_dir + cgitDirectory.HEADS_PATH.getPath();
        
        String [] fileNames = FileUtil.listFilesInDirectory(heads_directory);
        
        return fileNames;
    }
    
    /**
     * commitWalker
     * 
     * Goes from a starting commit and follows the parent to the root node. Current used for debug purposes
     * 
     * @param working_dir refers to the root directory where the .cgit folder lives. This is usually in the same place as the source text.
     * @param starting_commit 
     */
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
    
    /**
     * generateBlobTree
     * 
     * This function gets generates a BlobTree for the current comments and tags. 
     * This is used to take a snapshot of the current comments and tags so they can be stored as objects when a commit happens.
     * 
     * @param working_dir refers to the root directory where the .cgit folder lives. This is usually in the same place as the source text.
     * @return BlobTree object containing the current snapshot of the comments and tags
     */
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
    
    public static boolean hadCommentInPast(String working_dir)
    {
        //get the current commit object
        Commit current_commit = Commit.parseHash(Branch.getCurrentCommitHash(working_dir), working_dir);
        
        BlobTree current_blobtree = current_commit.getBlobTree();
        ArrayList<Blob> blobs = current_blobtree.getBlobs();
        
        for(Blob blob : blobs)
        {
            if(blob.getFilename() == "comments")
            {
                
            }
        }
        
        
        return false;
    }
    
    public static boolean hadTagInPast(String working_dir, TagInstance tag)
    {
        
        return false;
    }

    /* debug purposed only */
    public static void main(String[] args) throws Exception {
        //Branch.newBranch("/Volumes/DATA/Users/andrewjorgensen/temp/qda_project", "newbranch");
        String working_dir = "/Volumes/DATA/Users/andrewjorgensen/temp/qda_project";
        //Branch.commitWalker(working_dir, Commit.parseHash(Branch.getCurrentCommitHash(working_dir), working_dir));
        //Branch.getAllBranchNames(working_dir);
        
        Branch.hadCommentInPast(working_dir);
    }
}
