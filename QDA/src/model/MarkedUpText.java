package model;

import java.util.LinkedList;
import java.util.List;

public class MarkedUpText extends Element{
	private List<MarkUp> markUps;
	private SourceText sourceText;
	
	public MarkedUpText(List<MarkUp> markUps, SourceText sourceText){
		this.markUps = markUps;
		this.sourceText = sourceText;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void rename(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}
}
