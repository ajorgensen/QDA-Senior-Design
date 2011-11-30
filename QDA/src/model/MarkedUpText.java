package model;

import java.util.LinkedList;
import java.util.List;
import javax.swing.tree.*;

public class MarkedUpText extends DefaultMutableTreeNode implements Element, Nameable, Deletable {
	private List<Comment> comments;
        private List<TagInstance> tags;
	private SourceText sourceText;
        private String name;
	
        /**
         * 
         * @param sourceText
         * @param folder 
         */
	public MarkedUpText(SourceText sourceText){
            super(null,false);
            this.tags = new LinkedList<TagInstance>();
            this.comments = new LinkedList<Comment>();
            this.sourceText = sourceText;
            this.name = sourceText.getPath();
	}
 
	@Override
	public List<Tag> searchTags(List<Tag> tags, List<User> users) {
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
}
