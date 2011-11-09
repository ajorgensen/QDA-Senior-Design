package model;

import java.util.List;

public class RootTag {
	private User owner;
	private List<Tag> children;
	
	public User getOwner() {
		return owner;
	}
	public List<Tag> getChildren() {
		return children;
	}
}
