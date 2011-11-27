/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

public class Commit {

    String id;
    BlobTree blobTree;
    String commiter;
    Commit parent = null;
    String commit_message = "";

    Commit(BlobTree tree, String commiter, String commit_message) {
        this.blobTree = tree;
        this.commiter = commiter;
        this.commit_message = commit_message;
    }

    Commit(Commit parent, BlobTree tree, String commiter, String commit_message) {
        this.parent = parent;
        this.blobTree = tree;
        this.commiter = commiter;
        this.commit_message = commit_message;
    }
    
    public String getCommitMessage()
    {
        return commit_message;
    }
    
    public void setCommitMessage(String commit_message)
    {
        this.commit_message = commit_message;
    }

    public void setParent(Commit commit) {
        this.parent = commit;
    }

    public Commit getParent() {
        return this.parent;
    }

    public String getCommiter() {
        return this.commiter;
    }

    public BlobTree getBlobTree() {
        return this.blobTree;
    }
}
