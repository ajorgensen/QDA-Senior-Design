package model;

import java.util.Date;

public class Comment extends MarkUp {
	private User author;
	private String body;
	
	public Comment(User author, String body, int startline, int endline,
			int startchar, int endchar, Date date) {
		this.author=author;
		this.body=body;
		this.startLine=startline;
		this.endLine=endline;
		this.startChar=startchar;
		this.endChar=endchar;
		this.date=date;
	}
	public void setBody(String text){
		this.body = text;
	}
	
	public String getBody(){
		return body;
	}

	@Override
	public User getOwner() {
		return author;
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}
}
