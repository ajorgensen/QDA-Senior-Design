package model;

import java.util.List;

public class SourceText {
	private String filePath;
	private List<User> users;
	
	public SourceText(String filepath, List<User> users){
		this.filePath = filepath;
		this.users = users;
	}
	
	//TODO: figure out the best way to store the source text (one long string?)
		
	/**
	 * Selects the text from the source that starts on startChar of startLine
	 * and ends on endLine of endChar.
	 * @param startLine
	 * @param endLine
	 * @param startChar
	 * @param endChar
	 * @return
	 */
	public String getSelection(int startLine, int endLine, 
			int startChar, int endChar) {
		//TODO
		return "";
	}
}
