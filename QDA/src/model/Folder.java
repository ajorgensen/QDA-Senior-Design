package model;

import java.util.List;

public class Folder extends Element {
	
	private Folder parent;
	private List<Element> children;
	
	public Folder(Folder parent, List<Element> children){
		this.parent = parent;
		this.children = children;
	}

	public void setParent(Folder newParent){
		parent = newParent;
	}

	public Folder getParent(){
		return parent;
	}
	
	public void addChild(Element child){
		children.add(child);
	}
	
	public void removeChild(Element child){
		children.remove(child);
	}
	
	public List<Element> getChildren(){
		return children;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void rename(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}
}
