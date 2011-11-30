/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import cgit.HashObject;
import cgit.Objects;

public class Blob implements GitObject{
    
    private String content;
    private String filename;
    
    public Blob(String content, String filename)
    {
        this.content = content;
    }
    
    public String getContent()
    {
        return this.content;
    }
    
    public String getFilename()
    {
        return this.filename;
    }
    
    public int contentSize()
    {
        return content.length();
    }
    
    public static Blob parseHash(String hash, String working_dir, String filename)
    {
        return new Blob(Objects.readHash(hash, working_dir), filename);
    }
    
    @Override
    public String generateHash()
    {
        return HashObject.hashString("blob " + Integer.toString(content.length()) + "\0" + content);
    }

    @Override
    public String generateRawContent() {
        return this.content;
    }
    
}
