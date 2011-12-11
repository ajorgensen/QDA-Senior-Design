package model;

import cgit.HashObject;
import cgit.Objects;
import java.util.ArrayList;

public class Commit implements GitObject {

    String id;
    BlobTree blobTree;
    String commiter;
    ArrayList<Commit> parents = new ArrayList<Commit>();
    String commit_message = "";

    public Commit(BlobTree tree, String commiter, String commit_message) {
        this.blobTree = tree;
        this.commiter = commiter;
        this.commit_message = commit_message;
    }

    public Commit(ArrayList<Commit> parents, BlobTree tree, String commiter, String commit_message) {
        this.parents = parents;
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

    public void addParent(Commit parent) {
        this.parents.add(parent);
    }

    public void removeParent(Commit parent) {
        this.parents.remove(parent);
    }

    public ArrayList<Commit> getParents() {
        return this.parents;
    }
    
    public boolean hasParents()
    {
        if(this.parents != null && this.parents.size() > 0)
            return true;
        else 
            return false;
    }
    
    public boolean hasParentWithHash(String hash)
    {
        boolean contains = false;
        
        for(Commit parent : this.parents)
        {
            if(parent.generateHash().equals(hash))
            {
                contains = true;
            }
        }
        
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
        ArrayList<Commit> parents = new ArrayList<Commit>();
        String commiter = "";
        String commit_message = "";


        for (String line : data_lines) {
            int first_space = line.indexOf(" ");

            String param = line.substring(0, first_space);
            String content = line.substring(first_space + 1);

            if (param.equals("tree")) {
                tree = BlobTree.parseHash(content, working_dir);
            } else if (param.equals("parent")) {
                parents.add(Commit.parseHash(content, working_dir));
            } else if (param.equals("commiter")) {
                commiter = content;
            } else if (param.equals("message")) {
                commit_message = content;
            }
        }
        
        if(tree == null)
            return null;


        return new Commit(parents, tree, commiter, commit_message);
    }

    @Override
    public String generateHash() {
        return HashObject.hashString(this.generateRawContent());
    }

    @Override
    public String generateRawContent() {
        String content = "";


        content += "tree " + this.blobTree.generateHash() + "\n";

        if (this.parents != null || this.parents.size() > 0) {
            for (Commit parent : parents) {
                content += "parent " + parent.generateHash() + "\n";
            }
        }

        content += "commiter " + this.commiter + "\n";
        content += "message " + this.commit_message + "\n";

        return content;
    }

    public static void main(String[] args) {
        Commit.parseHash("434589f206ebbe5150d927bb91ec333c5857936e", "/Volumes/DATA/Users/andrewjorgensen/temp/qda_project");
    }
}
