package cgit;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import model.Blob;
import model.BlobTree;
import model.Comment;
import model.cgitDirectory;
import model.Commit;
import model.SourceText;
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
        Commit parent = null;
        String parent_hash = Branch.getCurrentCommitHash(working_dir);

        if (!parent_hash.isEmpty()) {
            parent = Commit.parseHash(parent_hash, working_dir);
        }

        Commit new_commit = new Commit(parent, tree, commiter, message);

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
    public static void checkoutBranch(String working_dir, String branchName) {
        //check to make sure the branch exists
        String[] availableBranches = Branch.getAllBranchNames(working_dir);

        boolean contains = false;
        for (String currentBranch : availableBranches) {
            if (currentBranch.equals(branchName)) {
                contains = true;
                break;
            }
        }

        if (!contains) {
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
    public static void checkoutCommit(String working_dir, String commitHash) {
        //make sure the commit hash is correct
        Commit newCommit = Commit.parseHash(commitHash, working_dir);

        if (newCommit == null) {
            return; //again we should through an exception or return some error but for the moment im lazy
        }
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
    public static String[] getAllBranchNames(String working_dir) {
        String heads_directory = working_dir + cgitDirectory.HEADS_PATH.getPath();

        String[] fileNames = FileUtil.listFilesInDirectory(heads_directory);

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
    public static void commitWalker(String working_dir, Commit starting_commit) {

        String message = "\ncommit " + starting_commit.generateHash() + "\n" + starting_commit.generateRawContent();
        MyLogger.LogMessageToConsole(Branch.class, message, LogType.DEBUG);

        if (starting_commit.hasParent()) {
            Branch.commitWalker(working_dir, starting_commit.getParent());
        }
    }

    public static boolean hadCommentInPast(String working_dir, Comment comment, SourceText text) {
        Commit currentCommit = Commit.parseHash(Branch.getCurrentCommitHash(working_dir), working_dir);
        
        if(currentCommit == null || !currentCommit.hasParent())
            return false;

        //check the current commit
        return checkCommitTreeForComment(currentCommit.getParent(), comment, text);
    }

    private static boolean checkCommitTreeForComment(Commit currentCommit, Comment comment, SourceText text) {
        if(currentCommit == null || comment == null || text == null)
            return false;
        
        Blob commentBlob = currentCommit.getBlobTree().getBlobWithFilename("comments");
        boolean exists = false;

        if (commentBlob == null) {
            return false;
        }

        List<Comment> commentsForCommit = comments.parseCommentFile(commentBlob.getContent(), text);

        for (Comment pastComment : commentsForCommit) {
            if (pastComment.isEqualTo(comment)) {
                return true;
            }
        }

        if (currentCommit.hasParent() && !exists) {
            return checkCommitTreeForComment(currentCommit.getParent(), comment, text);
        }

        return exists;
    }

    public static boolean hadTagInPast(String working_dir, TagInstance tag, SourceText text) {
        Commit currentCommit = Commit.parseHash(Branch.getCurrentCommitHash(working_dir), working_dir);

        return checkCommitTreeForTag(currentCommit.getParent(), tag, text);
    }

    private static boolean checkCommitTreeForTag(Commit currentCommit, TagInstance tag, SourceText text) {
        Blob tagBlob = currentCommit.getBlobTree().getBlobWithFilename("tags");
        boolean exists = false;

        if (tagBlob == null) {
            return false;
        }

        List<TagInstance> tagsForCommit = tags.parseTagFile(tagBlob.getContent(), text);

        for (TagInstance pastTag : tagsForCommit) {
            if (pastTag.isEqualTo(tag)) {
                return true;
            }
        }

        if (currentCommit.hasParent() && !exists) {
            return checkCommitTreeForTag(currentCommit.getParent(), tag, text);
        }

        return exists;
    }

    /**
     * This will merge project A onto project B. That is project B should be the current project you are working on and project A should be the project you are importing.
     * This function will have to be called once per source text per project
     * 
     * @param projectA_dir The project you are merging from
     * @param projectB_dir The project you are merging to
     * @param text The source text so we know what comments to merge.
     */
    public static List<Comment> mergeProjectsComments(String projectA_dir, String projectB_dir, SourceText text) {
        String ACommentPath = projectA_dir + cgitDirectory.COMMENTS_PATH.getPath();
        String BCommentPath = projectB_dir + cgitDirectory.COMMENTS_PATH.getPath();

        List<Comment> AComments = comments.loadCommentsForSourceText(projectA_dir, text.getContentHash());
        List<Comment> BComments = comments.loadCommentsForSourceText(projectB_dir, text.getContentHash());

        List<Comment> toRemove = new LinkedList<Comment>();

        //First we loop through B and check the comments
        for (Comment BComment : BComments) {
            boolean inA = false;
            for (Comment AComment : AComments) {
                if(BComment.isEqualTo(AComment)){
                    inA = true;
                }
            }
            
            //Check if project A had B's comment anywhere in its past
            if(!inA && Branch.hadCommentInPast(projectA_dir, BComment, text))
            {
                //remove it because it was deleted from As history
                toRemove.add(BComment);
            }
        }
        
        //remove the entries from B
        BComments.removeAll(toRemove);
        
        List<Comment> toAdd = new LinkedList<Comment>();
        
        //Loop through A to check the comments
        for(Comment AComment : AComments)
        {
            boolean inB = false;
            for(Comment BComment : BComments)
            {
                if(BComment.isEqualTo(AComment))
                {
                    inB = true;
                }
            }
            
            if(!inB)
            {
                toAdd.add(AComment);
            }
        }
        
        //add comments to B
        BComments.addAll(toAdd);
        
        //BComments should now be all merged with AComments
        return BComments;
    }
    
    public static List<TagInstance> mergeProjectTags(String projectA_dir, String projectB_dir, SourceText text) {
        String ATagsPath = projectA_dir + cgitDirectory.TAGS_PATH.getPath();
        String BTagsPath = projectB_dir + cgitDirectory.TAGS_PATH.getPath();

        List<TagInstance> ATags = tags.loadTagsForSourceText(projectA_dir, text.getContentHash());
        List<TagInstance> BTags = tags.loadTagsForSourceText(projectB_dir, text.getContentHash());
        
        if(ATags == null || BTags == null)
            return new LinkedList<TagInstance>();

        List<TagInstance> toRemove = new LinkedList<TagInstance>();

        //First we loop through B and check the comments
        for (TagInstance BTag : BTags) {
            boolean inA = false;
            for (TagInstance ATag : ATags) {
                if(BTag.isEqualTo(ATag)){
                    inA = true;
                }
            }
            
            //Check if project A had B's comment anywhere in its past
            if(!inA && Branch.hadTagInPast(projectA_dir, BTag, text))
            {
                //remove it because it was deleted from As history
                toRemove.add(BTag);
            }
        }
        
        //remove the entries from B
        BTags.removeAll(toRemove);
        
        List<TagInstance> toAdd = new LinkedList<TagInstance>();
        
        //Loop through A to check the comments
        for(TagInstance ATag : ATags)
        {
            boolean inB = false;
            for(TagInstance BTag : BTags)
            {
                if(BTag.isEqualTo(ATag))
                {
                    inB = true;
                }
            }
            
            if(!inB)
            {
                toAdd.add(ATag);
            }
        }
        
        //add comments to B
        BTags.addAll(toAdd);
        
        //BComments should now be all merged with AComments
        return BTags;
    }
    
    public static void mergeProject(String projectA_dir, String projectB_dir, SourceText text)
    {
        Branch.mergeProjectsComments(projectA_dir, projectB_dir, text);
        Branch.mergeProjectTags(projectA_dir, projectB_dir, text);
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

    /* debug purposed only */
    public static void main(String[] args) throws Exception {
        //Branch.newBranch("/Volumes/DATA/Users/andrewjorgensen/temp/qda_project", "newbranch");
        String working_dir = "/Volumes/DATA/Users/andrewjorgensen/temp/qda_project";
        //Branch.commitWalker(working_dir, Commit.parseHash(Branch.getCurrentCommitHash(working_dir), working_dir));
        //Branch.getAllBranchNames(working_dir);
    }
}
