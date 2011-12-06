package model;
import java.util.Enumeration;
import javax.swing.tree.*;
import java.util.List;

public class TagType extends DefaultMutableTreeNode implements Nameable, Deletable{
	private String name;
	
	public TagType(String name){
            super();
            this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}
	@Override
	public void rename(String name) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}
        
        @Override
        public String toString() {
            return name;
        }
        
        public String getPathString() {
            StringBuilder path = new StringBuilder(name);
            TagType curr = (TagType)getParent();
            
            while (curr != null) {
                path.insert(0, curr.getName());
                curr = (TagType)curr.getParent();
            }
            
            return path.toString();
        }
       
        //public void add(Tag newChild){
        //    
        //}
}
