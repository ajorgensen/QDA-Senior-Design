package model;

import java.util.ArrayList;

public class BlobTree {
    
    ArrayList<BlobContainer> blobs;
    
    public BlobTree(ArrayList<BlobContainer> blobs)
    {
        this.blobs = blobs;
    }
    
    
    public ArrayList<BlobContainer> getBlobs()
    {
        return this.blobs;
    }
}

