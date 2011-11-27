package model;

import java.util.ArrayList;
import cgit.HashObject;

public class BlobTree {

    ArrayList<BlobContainer> blobs;

    public BlobTree(ArrayList<BlobContainer> blobs) {
        this.blobs = blobs;
    }

    public ArrayList<BlobContainer> getBlobs() {
        return this.blobs;
    }

    public String generateHash() {
        String toHash = "";

        for (BlobContainer bc : blobs) {
            toHash += "blob " + Integer.toString(bc.getBlob().contentSize()) + "\0" + bc.getBlob().getContent() + "\n";
        }

        return HashObject.hashString(toHash);
    }
}
