package model;

import java.util.Enumeration;
import java.util.List;
import javax.swing.tree.*;

/**
 * The RootTag is the root of the tag hierarchy.
 * @author adm07012
 */
public class RootTag extends TagType {
    private User owner;
    private List<TagType> children;
    
    /**
     * Return the owner of the hierarchy (No longer used.)
     * @return owner
     */
    public User getOwner() {
        return owner;
    }
    /**
     * Returns a list of all the children of the roottag
     * @return children
     */
    public List<TagType> getChildren() {
	return children;
    }
    
    /**
     * Constructor to initialize the roottag
     */
    public RootTag(){
        super("Tags");
    }
}
