package model;
import java.util.Enumeration;
import javax.swing.tree.*;
import java.util.List;
import java.util.LinkedList;

/**
 * TagType class is used for a type of a tag. Each type of tag has a TagType 
 * object. It extends DefaultMutableTreeNode for tree functionality. This is how
 * the tag hierarchy is built.
 * 
 * @author adm07012
 */
public class TagType extends DefaultMutableTreeNode implements Nameable, Deletable{
	private String name;
	
        /**
         * Create a TagType with name.
         * 
         * @param name 
         */
	public TagType(String name){
            super();
            this.name = name.replace("/","");
	}
	/**
         * Returns the name
         * @return name
         */
	@Override
	public String getName() {
		return name;
	}
        /**
         * Rename method (not implemented)
         * @param name 
         */
	@Override
	public void rename(String name) {
		// TODO Auto-generated method stub
		
	}
        /**
         * Delete method(not implemented)
         */
	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}
        
        /**
         * Return the string representation of a TagType
         * @return - the name of the TagType
         */
        @Override
        public String toString() {
            return name;
        }
        
        /**
         * 
         * @return - the path of this tag in the hierarchy as a string 
         */
        public String getPathString() {
            StringBuilder path = new StringBuilder(name);
            TagType curr = (TagType)getParent();
            
            while (curr != null) {
                path.insert(0,"/");
                path.insert(0, curr.getName());
                curr = (TagType)curr.getParent();
            }
            
            return path.toString();
        }
        
        /**
         * (Not implemented)
         * @param path 
         */
        public void parsePathString(String path)
        {
            
        }
       
        //public void add(Tag newChild){
        //    
        //}
}
