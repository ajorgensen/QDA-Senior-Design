/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

public abstract class Blob {
    
    private String content;
    
    Blob(String content)
    {
        this.content = content;
    }
    
    public String getContent()
    {
        return this.content;
    }
    
}
