package model;
import java.util.Enumeration;
import javax.swing.tree.*;
import java.util.List;

public class Tag extends DefaultMutableTreeNode implements Nameable, Ownable, Deletable{
	private String name;
	private User owner;
	
	public Tag(String name, User owner){
            super();
            this.name = name;
            this.owner = owner;
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void rename(String name) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public User getOwner() {
		return owner;
	}
	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}
        
        @Override
        public String toString() {
            return name;
        }
       
        //public void add(Tag newChild){
        //    
        //}
}
