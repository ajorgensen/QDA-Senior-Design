package model;


import java.util.HashSet;
import java.util.List;
import java.util.LinkedList;
import java.util.Enumeration;
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
        private List<Folder> folders;
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
        public Project() {
            
        }
	public Project(String name, String localPath, User admin){                
		this.localPath = localPath;
                this.name = name;
                this.mainFolder = new Folder(localPath);
		this.users = new LinkedList<User>();
                this.folders = new LinkedList<Folder>();
                addUser(admin);
                this.currUser = null;
                this.rootTag = new RootTag();
                this.tags=new LinkedList<TagType>();
                tags.add(this.rootTag);
                this.curText = null;
                
                this.initialize_cgit();
	}
        
        
        
        private void initialize_cgit(){
            cgit.setup.setup_qda_directory(localPath);
        }
        
        public String getLocalPath()
        {
            return this.localPath;
        }
        public void setName(String n) {
            name = n;
        }
        public void setLocalPath(String l) {
            localPath = l;
        }
        public void setUser(User u) {
            currUser = u;
        }
        
	  
        public RootTag getRootTag() {
            return rootTag;
        }
        
        /**
         * Adds tag with newName to tag hierarchy with parent as its parent.
         * Does not add the tag if one already exists with the same name.
         * Returns tag if successful, null if not.
         * @param parent
         * @param newName
         * @return 
         */
        public TagType addTagType(TagType parent, String newName) {
            //for(TagType t: tags) {
            //This was for disallowing multiple tags of the same name
            //We'll allow them for now.
            //    if(t.getName().equals(newName)) return null;
            //}
            TagType newTag = new TagType(newName);
            parent.add(newTag);
            tags.add(newTag);
            return newTag;
        }
        
        /**
         * Adds any tags in path that do not already exist.
         * Eg if path is is Tags/Animal/Mammal/Dog, and only
         * Tags/Animal exists, it will add both Mammal and Dog.
         * @param path
         * @returns the last node (TagType) in path
         */
        public TagType addTagType(String path) {
            TagType parent = getRootTag();
            //List<TagType> children = new LinkedList<TagType>();//parent.getChildren();
            String soughtTag;
            path=path.substring(path.indexOf("/")+1);//skip root tag
            do {
                //List<TagType> children = parent.getChildren();
                Enumeration children = parent.children();
                if(!path.contains("/")) {
                    soughtTag=path;
                    path="";
                } else {
                    soughtTag = path.substring(0,path.indexOf("/"));
                    path=path.substring(path.indexOf("/")+1);
                }
                //System.out.println("Seeking " + soughtTag);
                //System.out.println("Remaining path is " + path);
                boolean matched = false;
                //System.out.println(children.size());
                //for(TagType t : children) {
                while(children.hasMoreElements()) {
                    TagType t = (TagType) children.nextElement();
                    if(t.getName().equals(soughtTag)) {
                        parent=t;
                        matched=true;
                    }
                }
                if(!matched) {
                    parent=addTagType(parent,soughtTag);
                }
            } while(path!="");
            
            return parent;
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
        public List<Folder> getFolders2() {
            return folders;
        }
        
        public User getCurrentUser(){
            return currUser;
        }
        
        public boolean setCurrentUser(User user) {
            for (int i = 0; i < users.size(); i++) {
                User u = users.get(i);
                if (u == user) {
                    currUser = u;
                    return true;
                }
            }
            currUser = null;
            return false;
        }
	
	public void addUser(User u){
            users.add(u);
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
            folders.add(createdFolder);
            return createdFolder;
        }
        
        public String[] getFolders() {
            int num = folders.size();
            String[] alpha = new String[num];            
            for(int i = 0; i < num; i++) {
                alpha[i] = folders.get(i).getName();
            }
            return alpha;
        }
        
        public String[] getTags() {
            int num = tags.size();
            String[] alpha = new String[num];
            for(int i = 0; i < num; i++) {
                alpha[i] = tags.get(i).getName();
            }
            return alpha;
        }

        public Folder findFolder(String name) {
            String n = name;
            for(int i = 0; i < folders.size(); i++) {
                if(folders.get(i).getName().equals(n)) {
                    return folders.get(i);
                } else {
                    
                }
            }
            return null;
        }
        
        public TagType findTag(String name) {
            String n = name;
            for(int i = 0; i < tags.size(); i++) {
                if(tags.get(i).getName().equals(n)) {
                    return tags.get(i);
                } else {
                    
                }
            }
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

