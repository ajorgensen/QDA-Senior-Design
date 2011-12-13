package model;

import cgit.HashObject;
import cgit.Objects;
import java.util.ArrayList;

public class Commit implements GitObject {

    String id;
    BlobTree blobTree;
    String commiter;
    Commit parent;
    String commit_message = "";

    public Commit(BlobTree tree, String commiter, String commit_message) {
        this.blobTree = tree;
        this.commiter = commiter;
        this.commit_message = commit_message;
    }

    public Commit(Commit parent, BlobTree tree, String commiter, String commit_message) {
        this.parent = parent;
        this.blobTree = tree;
        this.commiter = commiter;
        this.commit_message = commit_message;
    }

    public String getCommitMessage() {
        return commit_message;
    }

    public void setCommitMessage(String commit_message) {
        this.commit_message = commit_message;
    }

    public void setParent(Commit newParent)
    {
        this.parent = newParent;
    }

    public Commit getParent() {
        return this.parent;
    }
    
    public boolean hasParent()
    {
        if(this.parent != null)
            return true;
        else 
            return false;
    }
    
    public boolean hasParentWithHash(String hash)
    {
        boolean contains = false;
        
        if(this.parent.generateHash().equals(hash))
            contains = true;
        
        return contains;
    }

    public String getCommiter() {
        return this.commiter;
    }

    public BlobTree getBlobTree() {
        return this.blobTree;
    }

    public static Commit parseHash(String hash, String working_dir) {
        String data = Objects.readHash(hash, working_dir);

        return Commit.parseCommitContent(data, working_dir);
    }
    

    public static Commit parseCommitContent(String data, String working_dir) {
        
        if(data.isEmpty())
            return null;
        
        String[] data_lines = data.split("\n");

        BlobTree tree = null;
        Commit parent = null;
        String commiter = "";
        String commit_message = "";


        for (String line : data_lines) {
            int first_space = line.indexOf(" ");

            String param = line.substring(0, first_space);
            String content = line.substring(first_space + 1);

            if (param.equals("tree")) {
                tree = BlobTree.parseHash(content, working_dir);
            } else if (param.equals("parent")) {
                parent = Commit.parseHash(content, working_dir);
            } else if (param.equals("commiter")) {
                commiter = content;
            } else if (param.equals("message")) {
                commit_message = content;
            }
        }
        
        if(tree == null)
            return null;


        return new Commit(parent, tree, commiter, commit_message);
    }

    @Override
    public String generateHash() {
        return HashObject.hashString(this.generateRawContent());
    }

    @Override
    public String generateRawContent() {
        String content = "";


        content += "tree " + this.blobTree.generateHash() + "\n";

        if (this.parent != null) {
            content += "parent " + parent.generateHash() + "\n";
        }

        content += "commiter " + this.commiter + "\n";
        content += "message " + this.commit_message + "\n";

        return content;
    }

    public static void main(String[] args) {
        Commit.parseHash("434589f206ebbe5150d927bb91ec333c5857936e", "/Volumes/DATA/Users/andrewjorgensen/temp/qda_project");
    }
}
