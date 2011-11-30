package model;

import java.util.List;
import java.util.LinkedList;

public class Project implements Nameable {
	private String localPath;
        private String name;
	private Folder mainFolder;
	private List<User> users;
	
        
        /**
         * Creates a new project
         * 
         * @param localPath - taken from file selection dialog
         * @param currentUser - the user creating the file
         */
	public Project(String name, String localPath, User currentUser){
		this.localPath = localPath;
                this.name = name;
                this.mainFolder = new Folder(localPath);
		this.users = new LinkedList<User>();
                this.addUser(currentUser);
                
	}
	
        /**
         * Use for logging in.
         * @param userName
         * @param pass
         * @return 
         */
	public boolean verifyUser(String userName, String pass){
		for(User u: users){
                    if((u.getName()).equals(name)){
                        return u.comparePassword(pass);
                    }
                }
                return false;
                
	}
	
	public void refreshRepository(){
		
	}
	
        /**
         * Return all taginstances in folders that match any tag in tags
         * @param folders
         * @param tags
         * @return 
         */
	public List<TagInstance> search(List<Folder> folders, List<Tag> tags) {
            return null;
        }
        
        /**
         * Return all comments by User
         * @param folders
         * @param comments
         * @return 
         */
        public List<Comment> search(List<Folder> folders, User user){
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
	
        /**
         * Create a new folder for sourcetexts
         * @param filepath - taken from gui selection
         * @param importLocation - taken from gui selection
         */
	public MarkedUpText importSourceText(String filePath, Folder importLocation){
            SourceText text = new SourceText(filePath);
            MarkedUpText newMarkedText = new MarkedUpText(text);
            importLocation.add(newMarkedText);
            return newMarkedText;
	}
        
        /**
         * Create a new folder for sourcetexts
         * @param parent - taken from gui selection
         * @param name  - taken from gui
         */
        public Folder createFolder(Folder parent, String name){
            Folder createdFolder = new Folder(name);
            parent.add(createdFolder);
            return createdFolder;
        }

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void rename(String name) {
		// TODO Auto-generated method stub
		
	}
        
        public Folder getMainFolder(){
            return mainFolder;
        }
}

