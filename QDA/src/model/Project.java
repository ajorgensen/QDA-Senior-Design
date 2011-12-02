package model;

import java.util.HashSet;
import java.util.List;
import java.util.LinkedList;
import model.RootTag;

/**
 * Project 
 * The project class should be an adapter for most of the functions used by the 
 * GUI.
 * This includes: importing source texts, adding tags to texts, adding
 * comments to texts, modifying the tag hierarchy, adding users, etc. 
 * 
 * The text currently being viewed by the GUI should be kept track of using the 
 * Project class, using get/setCurrentText() methods. This text is a 
 * MarkedUpText.
 * 
 * 
 * 
 * @author Alan, David
 */
public class Project implements Nameable {
	private String localPath;
        private String name;
	private Folder mainFolder;
	private List<User> users;
        private User currUser;
	private RootTag rootTag;
        private List<TagType> tags;
        private MarkedUpText curText;
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
                this.currUser = currentUser;
                this.rootTag = new RootTag();
                this.tags=new LinkedList<TagType>();
                tags.add(this.rootTag);
                this.curText = null;
	}
	  
        public RootTag getRootTag() {
            return rootTag;
        }
        
        /**
         * Adds tag with newName to tag hierarchy with parent as its parent.
         * Does not add the tag if one already exists with the same name.
         * Returns true if successful, false if not.
         * @param parent
         * @param newName
         * @return 
         */
        public boolean addTagType(TagType parent, String newName) {
            for(TagType t: tags) {
                if(t.getName().equals(newName)) return false;
            }
            TagType newTag = new TagType(newName);
            parent.add(newTag);
            tags.add(newTag);
            return true;
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
	public List<TagInstance> search(List<Folder> folders, List<TagType> tags) {
            HashSet<TagInstance> s = new HashSet<TagInstance>();
            for(Folder f: folders) {
                s.addAll(f.searchTags(tags));
            }
            return new LinkedList<TagInstance>(s);
        }
        
        /**
         * Return all comments by User
         * @param folders
         * @param comments
         * @return 
         */
        public List<Comment> search(List<Folder> folders, User user){
            HashSet<Comment> s = new HashSet<Comment>();
            for(Folder f: folders) {
                LinkedList<User> l = new LinkedList<User>();
                l.add(user);
                s.addAll(f.searchComments(l));
            }
            return new LinkedList<Comment>(s);
	}
	
	public List<User> getUsers(){
		return users;
	}
        
        public User getCurrentUser(){
            return currUser;
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
            MarkedUpText newMarkedText = new MarkedUpText(text, this);
            importLocation.add(newMarkedText);
            
            if(curText == null){
                this.curText = newMarkedText;
            }
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
        
        private MarkedUpText getCurrentText(){
            return curText;
        }
        
        public void setCurrentText(MarkedUpText newCurr){
            curText = newCurr;
        }
        
        public boolean addTagInstance(String tagName, TextSection selection){
            TagType tag = this.getTagType(tagName);
            if(tag != null){
                return curText.addTag(tag, selection);
            }else{
                return false;
            }
        }
        
        public TagType getTagType(String tagName){
            for(TagType t: tags) {
               if(t.getName().equals(tagName)){
                    return t;
               } 
            }
            return null;
        }
        
        /**
         * Returns a string representation of the current source text.
         * @return 
         */
        public String getCurrSourceText(){
            return curText.getSourceText().getText();
        }
}

