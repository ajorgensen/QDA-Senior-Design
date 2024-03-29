package model;


import cgit.Branch;
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
         */
        public Project() {
            
        }
        
        /**
         * Constructor for project.
         * @param name
         * @param localPath
         * @param admin 
         */
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
        
        
        /**
         * Initializes cgit
         */
        private void initialize_cgit(){
            cgit.setup.setup_qda_directory(localPath);
        }
        
        /**
         * Returns the local path of a project
         * @return localpath
         */
        public String getLocalPath()
        {
            return this.localPath;
        }
        
        /**
         * Set the name of a project.
         * @param n 
         */
        public void setName(String n) {
            name = n;
        }
        
        /** 
         * Set the local path of a project.
         * @param l 
         */
        public void setLocalPath(String l) {
            localPath = l;
        }
        
        /**
         * Set the user of a project
         * @param u 
         */
        public void setUser(User u) {
            currUser = u;
        }
        
	/**
         * Get the project's root tag.
         * @return rootTag
         */  
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
	
        /**
         * Returns a list of the project's users.
         * @return users
         */
	public List<User> getUsers(){
		return users;
	}
        
        /**
         * Returns a list of the folders in a project.
         * @return folders
         */
        public List<Folder> getFolders2() {
            return folders;
        }
        
        /**
         * Gets the current user of the project
         * @return currUser
         */
        public User getCurrentUser(){
            return currUser;
        }
        
        /**
         * Sets the current user of a project
         * @param user
         * @return true if successful
         */
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
	
        /**
         * Add user to the project.
         * @param u 
         */
	public void addUser(User u){
            users.add(u);
	}
	
        /**
         * Remove a user from the project.
         * @param user 
         */
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
        
        /**
         * Gets a string array of the folder names in the project.
         * @return 
         */
        public String[] getFolders() {
            int num = folders.size();
            String[] alpha = new String[num];            
            for(int i = 0; i < num; i++) {
                alpha[i] = folders.get(i).getName();
            }
            return alpha;
        }
        
        /**
         * Gets a string list of the tags in a project
         * @return 
         */
        public String[] getTags() {
            int num = tags.size();
            String[] alpha = new String[num];
            for(int i = 0; i < num; i++) {
                alpha[i] = tags.get(i).getName();
            }
            return alpha;
        }
        
        /**
         * Find a folder with name
         * @param name
         * @return folder or null
         */
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
        
        /**
         * Find a TagType with name 
         * @param name
         * @return TagType or null
         */
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

        /**
         * Rename method (not implemented)
         * @param name 
         */
	@Override
	public void rename(String name) {
		// TODO Auto-generated method stub
		
	}
        
        /**
         * Get the main folder of the project.
         * @return mainFolder
         */
        public Folder getMainFolder(){
            return mainFolder;
        }
        
        /**
         * Get the current MarkedUpText
         * @return curText
         */
        private MarkedUpText getCurrentText(){
            return curText;
        }
        
        /**
         * Set the current MarkedUpText
         * @param newCurr 
         */
        public void setCurrentText(MarkedUpText newCurr){
            curText = newCurr;
        }
        
        /**
         * Add a taginstance to the selection of the current MarkedUpText 
         * @param tagName
         * @param selection
         * @return 
         */
        public boolean addTagInstance(String tagName, TextSection selection){
            TagType tag = this.getTagType(tagName);
            if(tag != null){
                return curText.addTag(tag, selection);
            }else{
                return false;
            }
        }
        
        /**
         * Return TagType with tagName
         * @param tagName
         * @return TagType or null
         */
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
        
        /**
         * Save the project
         */
        public void saveProject()
        {
            if(this.curText != null)
                curText.saveCurrentState();
        }
        
        /**
         * Commit the changes made to a project
         * @param commiter
         * @param message 
         */
       public void commitChanges(String commiter, String message)
       {
           if(curText != null)
               curText.commitChanges(commiter, message);
       }
       
       public void mergeProject(String ProjectA_Dir)
       {
           Branch.mergeProject(ProjectA_Dir, this.getLocalPath(), this.curText.getSourceText());
           
           List<Comment> newComments = Branch.mergeProjectsComments(ProjectA_Dir, this.getLocalPath(), this.curText.getSourceText());
           List<TagInstance> newTags = Branch.mergeProjectTags(ProjectA_Dir, this.getLocalPath(), this.curText.getSourceText());
           
           this.curText.setComments(newComments);
           this.curText.setTags(newTags);
       }
}

