package model;

import java.util.LinkedList;
import java.util.List;
import javax.swing.tree.*;
import java.util.Date;

public class MarkedUpText extends DefaultMutableTreeNode implements Element, Nameable, Deletable {
	private List<Comment> comments;
        private List<TagInstance> tags;
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
            this.tags = new LinkedList<TagInstance>();
            this.comments = new LinkedList<Comment>();
            this.sourceText = sourceText;
            this.name = sourceText.getPath();
	}
 
	@Override
	public List<TagInstance> searchTags(List<Tag> tags, List<User> users) {
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
		return name;
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
            return this.getName();
        }
        
        public SourceText getSourceText(){
            return sourceText;
        }
        
        /**
         * Use this method to add Tags to a text
         * @param tagType
         * @param selection 
         */
        public void addTag(Tag tagType, TextSection selection){
            Date dateAdded = new Date();
            Date dateModified = new Date();
            tags.add(new TagInstance(project.getCurrentUser(), dateAdded, dateModified,
            selection, sourceText.getPath(), tagType));
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
            comments.add(new Comment((project.getCurrentUser()).getName(), dateAdded, dateModified,
            selection, comment, sourceText.getPath()));
        }
       
       public List<TagInstance> getTags(){
           return tags;
       }
       
       public List<Comment> getComments(){
           return comments;
       }
       
       
}
