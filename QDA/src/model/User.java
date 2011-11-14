package model;

public class User {
	private String password;
	private RootTag tagTree;
	
	public User(String password, RootTag tagTree){
		this.password = password;
		this.tagTree = tagTree;
	}
}
