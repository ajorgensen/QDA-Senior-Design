package model;

import java.util.Enumeration;
import java.util.List;
import java.util.LinkedList;
import javax.swing.tree.*;

/**
 * A folder holds a group of MarkedUpTexts. It extends DefaultMutableTreeNode so
 * it can have tree functionality. It's children can be MarkedUpTexts or other 
 * folders. 
 * 
 * @author adm07012
 */
public class Folder extends DefaultMutableTreeNode implements Element, Nameable, Deletable {
        private String name;
	
        /**
         * Create a folder with name
         * @param name 
         */
	public Folder(String name){
		super();
                this.name = name;
	}
        
        /**
         * Search folder for a list of taginstances of tagtypes
         * @param tags
         * @return list of tagInstances
         */
	@Override
	public List<TagInstance> searchTags(List<TagType> tags) {
            List<MarkedUpText> texts = getDescendantTexts();
            
            List<TagInstance> tagInstances = new LinkedList<TagInstance>();
            for(MarkedUpText t: texts) {
                tagInstances.addAll(t.searchTags(tags));
            }
            return tagInstances;
	}

        /**
         * Returns all comments from any user in users in any text in the
         * folder or a subfolder.
         * @param users
         * @return 
         */
	@Override
	public List<Comment> searchComments(List<User> users) {
            List<MarkedUpText> texts = getDescendantTexts();
            
            List<Comment> comments = new LinkedList<Comment>();
            for(MarkedUpText t: texts) {
                comments.addAll(t.searchComments(users));
            }
            return comments;
	}
        
        /**
         * Returns the name of the folder.
         * @return name
         */
	@Override
	public String getName() {
		return name;
	}
        
        /**
         * (Renaming folders has not been implemented)
         * @param name 
         */
	@Override
	public void rename(String name) {
		// TODO Auto-generated method stub
		
	}
        
        /**
         * Deleting folders has not been implemented.
         */
	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}
        
        /**
         * Returns the string representation of a folder.
         * @return name
         */
        @Override
        public String toString(){
            return name;
        }
        
        /**
         * Return a list of MarkedUpTexts that are directly inside the folder
         * Does NOT return texts in subfolders.
         * @return 
         */
        public List<MarkedUpText> getChildrenTexts() {
            Enumeration<DefaultMutableTreeNode> children = this.children();
            
            List<MarkedUpText> texts = new LinkedList<MarkedUpText>();
            while (children.hasMoreElements()) {
                DefaultMutableTreeNode curChild = children.nextElement();
                if(!curChild.getAllowsChildren()) {
                    texts.add((MarkedUpText) curChild);
                }
            }
            return texts;
        }
        /**
         * Recursively returns all texts in the folder, its subfolders, their
         * subfolders, etc.
         * @return 
         */
        public List<MarkedUpText> getDescendantTexts() {
            List<MarkedUpText> texts = this.getChildrenTexts();
            
            Enumeration<DefaultMutableTreeNode> children = this.children();
            while (children.hasMoreElements()) {
                DefaultMutableTreeNode curChild = children.nextElement();
                if(curChild.getAllowsChildren()) {
                    texts.addAll(((Folder) curChild).getDescendantTexts());
                }
            }
            return texts;
        }
}
