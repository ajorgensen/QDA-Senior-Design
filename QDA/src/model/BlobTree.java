package model;

import java.util.ArrayList;
import cgit.HashObject;
import cgit.Objects;

public class BlobTree implements GitObject{

    ArrayList<Blob> blobs;

    public BlobTree(ArrayList<Blob> blobs) {
        this.blobs = blobs;
    }

    public ArrayList<Blob> getBlobs() {
        return this.blobs;
    }
    
    public static BlobTree parseHash(String hash, String working_dir)
    {
        String data = Objects.readHash(hash, working_dir);
        
        String [] data_lines = data.split("\n");
        
        
        ArrayList<Blob> blobs = new ArrayList<Blob>();
        for(String lines : data_lines)
        {
            String [] params = lines.split(" ");
            blobs.add(Blob.parseHash(params[0], working_dir, params[1]));
        }
        
        return new BlobTree(blobs);
    }
    
    public Blob getBlobWithFilename(String filename)
    {
        for(Blob blob : blobs)
        {
            if(blob.getFilename().equals(filename))
            {
                return blob;
            }
        }
        
        return null;
    }

    @Override
    public String generateHash() {
        String toHash = "";

        for (Blob bc : blobs) {
            toHash += "blob " + Integer.toString(bc.contentSize()) + "\0" + bc.getContent() + "\n";
        }

        return HashObject.hashString(toHash);
    }

    @Override
    public String generateRawContent() {
        String content = "";
        
        for(Blob blob : blobs)
        {
            content += blob.generateHash() + " " + blob.getFilename() + "\n";
        }
        
        return content;
    }
}
