/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author andrewjorgensen
 */
public class FileStructure {
    
    private ArrayList<FileStructure> children = new ArrayList<FileStructure>();
    private FileStructure parent;
    private String path;
    private boolean folder = true;
    
    public FileStructure(boolean folder, String path){
        this.folder = folder;
        this.path = path;
    }
    
    public FileStructure(boolean folder, String path, FileStructure parent){
        this.folder = folder;
        this.path = path;
        this.parent = parent;
    }
    
    public FileStructure(boolean folder, String path, FileStructure parent, ArrayList<FileStructure> children){
        this.folder = folder;
        this.path = path;
        this.parent = parent;
        this.children = children;
    }
    
    public void addChild(FileStructure child){
        this.children.add(child);
    }
    
    public void addChildren(ArrayList<FileStructure> children)
    {
        this.children.addAll(children);
    }
    
    public void removeChild(FileStructure child)
    {
        this.children.remove(child);
    }
    
    public void removeChildren(ArrayList<FileStructure> children){
        this.children.removeAll(children);
    }
    
    public FileStructure getParent(){
        return this.parent;
    }
    
    public ArrayList<FileStructure> getChildren(){
        return this.children;
    }
    
    public String getPath(){
        return this.path;
    }
    
    public boolean hasChildren(){
        if(this.children != null && !this.children.isEmpty())
            return true;
        else
            return false;
    }
    
    public void setFoler(boolean val)
    {
        folder = val;
    }
    
    public boolean isFolder()
    {
        return folder;
    }
}
