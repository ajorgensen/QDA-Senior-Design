package model;

import java.util.List;

public class Tag implements Nameable, Ownable, Deletable {
	private Tag parent;
	private List<Tag> children;
	private String name;
	private User owner;
	
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
}
