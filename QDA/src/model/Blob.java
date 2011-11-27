/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import cgit.HashObject;

public class Blob {
    
    private String content;
    
    public Blob(String content)
    {
        this.content = content;
    }
    
    public String getContent()
    {
        return this.content;
    }
    
    public int contentSize()
    {
        return content.length();
    }
    
    public String generateHash()
    {
        return HashObject.hashString("blob " + Integer.toString(content.length()) + "\0" + content);
    }
    
}
