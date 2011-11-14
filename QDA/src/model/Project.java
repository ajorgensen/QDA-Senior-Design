package model;

import java.util.List;
import java.util.LinkedList;

public class Project implements Nameable {
	private String localPath;
	private Folder mainFolder;
	private List<User> users;
	
	public Project(String localPath, Folder mainFolder, List<User> users){
		this.localPath = localPath;
		this.mainFolder = mainFolder;
		this.users = users;
	}
	
	public void signIn(User user){
		
	}
	
	public void refreshRepository(){
		
	}
	
	public List<MarkUp> search(List<Folder> folders, 
			List<Tag> tags, 
			List<User> comments){
		return null;
	}
	
	public List<User> getUsers(){
		return users;
	}
	
	public void addUser(User user){
		users.add(user);
	}
	
	public void removeUser(User user){
		users.remove(user);
	}
	
	public void importSourceText(String filePath, Folder importLocation){
		
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
}

