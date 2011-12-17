package model;

import cgit.Branch;
import cgit.tags;
import java.util.LinkedList;
import java.util.List;
import javax.swing.tree.*;
import java.util.Date;

/**
 * MarkedUpText is a container for a SourceText and all of the MarkUp(tags and
 * comments) applied to that source text. It extends DefaultMutableTreeNode so 
 * it can have tree functionality. A MarkedUpText will always be a leaf node 
 * with a Folder object as a parent. 
 * 
 * @author adm07012
 */
public class MarkedUpText extends DefaultMutableTreeNode implements Element, Nameable, Deletable {
	private List<Comment> comments;
        private List<TagInstance> tagInstances;
	private SourceText sourceText;
        private String name;
        private Project project;
	
        /**
         * 
         * @param sourceText
         * @param folder 
         */
	public MarkedUpText(SourceText sourceText, Project project){
            super(null,false);
            this.project = project;
            this.tagInstances = new LinkedList<TagInstance>();
            this.comments = new LinkedList<Comment>();
            this.sourceText = sourceText;
            this.name = sourceText.getPath();
            
            this.loadTags();
            this.loadComments();
	}
        
        /**
         * Load the tags for this text.
         */
        public void loadTags()
        {
            List<TagInstance> tagHolder = tags.loadTagsForSourceText(project.getLocalPath(), sourceText.getContentHash());
            
            if(tagHolder != null && !tagHolder.isEmpty())
                tagInstances.addAll(tagHolder);
        }
        
        /**
         * Load the comments for this text. 
         */
        public void loadComments()
        {
            
            List<Comment> commentHolder = cgit.comments.loadCommentsForSourceText(project.getLocalPath(), sourceText.getContentHash());
            
            if(commentHolder != null && !commentHolder.isEmpty())
                comments.addAll(commentHolder);
        }
        
        /**
         * Get the project that this text belongs to.
         * @return project
         */
        public Project getProject()
        {
            return project;
        }
 
        /**
         * Search this text for a list of TagTypes. Returns a list of the 
         * TagInstances that have those types of tags.
         * 
         * @param tagTypes
         * @return list of TagInstances
         */
	@Override
	public List<TagInstance> searchTags(List<TagType> tagTypes) {
            List<TagInstance> returnList = new LinkedList<TagInstance>();
            for(TagType t : tagTypes){
                    for(TagInstance u : tagInstances){
                        if(t == u.getTagType()){
                            returnList.add(u);
                        }
                    }
                }
            return returnList;
        }
        
        /**
         * Search the comments applied to this text by a list of users.
         * @param users
         * @return list of Comments
         */
	@Override
	public List<Comment> searchComments(List<User> users) {
            List<Comment> returnList = new LinkedList<Comment>();
            for(User t : users){
                    for(Comment c : comments){
                        if(t.getName().equals(c.getOwner())){
                            returnList.add(c);
                        }
                    }
                }
            return returnList;
	}

	@Override
	public String getName() {
		return name;
	}
        
        /**
         * Return the pretty version of the text's name.
         * @return 
         */
        public String getPrettyName(){
            return name.substring(name.lastIndexOf("/") + 1);
            //If you don't want the file extension:
            //return name.substring(name.lastIndexOf("/") + 1, name.lastIndexOf(".")); 
        }

	@Override
	public void rename(String name) {
		this.name = name;
                // TODO: Do stuff with files
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}
        
        @Override
        public String toString(){
            return this.getPrettyName();
        }
        
        public SourceText getSourceText(){
            return sourceText;
        }
        
        /**
         * Use this method to add Tags to a text
         * @param tagType
         * @param selection 
         */
        public boolean addTag(TagType tagType, TextSection selection){
            Date dateAdded = new Date();
            Date dateModified = new Date();
            tagInstances.add(new TagInstance(project.getCurrentUser().getName(), dateAdded, dateModified,
            selection,tagType, this.sourceText.getContentHash()));
            return true;
        }
       
       /**
         * Use this method to add comments to a text
         * 
         * @param comment
         * @param selection 
         */ 
       public void addComment(String comment, TextSection selection){
            Date dateAdded = new Date();
            Date dateModified = new Date();
            comments.add(new Comment(project.getCurrentUser().getName(), dateAdded, dateModified,
            selection, comment, this.sourceText.getContentHash()));
        }
       
       /**
        * Return the list of TagInstances that belong to this text. 
        * @return tagInstances
        */
       public List<TagInstance> getTags(){
           return tagInstances;
       }
       
       /**
        * Returns the list of comments that belong to this text.
        * 
        * @return comments
        */
       public List<Comment> getComments(){
           return comments;
       }
       
       /**
        * Saves the current state of the tags and comments on this text.
        */
       public void saveCurrentState()
       {
           cgit.tags.saveTags(project.getLocalPath(), tagInstances);
           cgit.comments.saveComments(project.getLocalPath(), comments);
       }
       
       /**
        * Commit the changes made to this text. 
        * 
        * @param commiter
        * @param message 
        */
       public void commitChanges(String commiter, String message)
       {
           Branch.commit(project.getLocalPath(), commiter, message);
       }
}
