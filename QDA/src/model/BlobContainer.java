package model;

public class BlobContainer{
    
    Blob blob;
    String filename;
    
    public BlobContainer(Blob blob, String filename)
    {
        this.blob = blob;
        this.filename = filename;
    }
    
    public Blob getBlob()
    {
        return this.blob;
    }
    
    public String getFilename()
    {
        return this.filename;
    }
    
}
