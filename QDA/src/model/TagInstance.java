package model;

public class TagInstance extends MarkUp {
	private Tag tagType;
	
	public TagInstance(Tag tagType){
		this.tagType = tagType;
	}

	public Tag getTagType() {
		return tagType;
	}
	
	@Override
	public User getOwner() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}
}
