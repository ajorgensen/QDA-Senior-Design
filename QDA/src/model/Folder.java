package model;

import java.util.List;
import java.util.LinkedList;
import javax.swing.tree.*;

public class Folder extends DefaultMutableTreeNode implements Element, Nameable, Deletable {
	
	//private Folder parent;
        private String name;
	//private List<Element> children;
	
	public Folder(String name){
		super();
                this.name = name;
	}
        
	@Override
	public List<Tag> searchTags(List<Tag> tags, List<User> users) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comment> searchComments(List<User> users) {
		// TODO Auto-generated method stub
		return null;
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
        public String toString(){
            return name;
        }
}
