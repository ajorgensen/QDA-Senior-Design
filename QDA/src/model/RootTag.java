package model;

import java.util.Enumeration;
import java.util.List;
import javax.swing.tree.*;

public class RootTag extends Tag {
    private User owner;
    private List<Tag> children;
	
    public User getOwner() {
        return owner;
    }
    public List<Tag> getChildren() {
	return children;
    }
        
    public RootTag(User owner){
        super(owner.getName(),owner);
    }
}
