package model;

import java.util.Enumeration;
import java.util.List;
import javax.swing.tree.*;

public class RootTag extends TagType {
    private User owner;
    private List<TagType> children;
	
    public User getOwner() {
        return owner;
    }
    public List<TagType> getChildren() {
	return children;
    }
        
    public RootTag(){
        super("Tags");
    }
}
