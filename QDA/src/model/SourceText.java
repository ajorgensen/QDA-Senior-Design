package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SourceText {
	private String filePath;
	private List<User> users;
	private List<String> lines;
	private String text;
	
	public SourceText(String filepath, List<User> users){
		this.filePath = filepath;
		this.users = users;
		
		lines = new ArrayList<String>();
		
		readFile();
	}
	
	//TODO: figure out the best way to store the source text (one long string?)
	//Stores line by line in "lines" and as a single string in "text"
	private void readFile() {

		StringBuilder content = new StringBuilder();

		try {
			
			BufferedReader in = new BufferedReader(new FileReader(filePath));
			try {
				String line = null; // not declared within while loop
				
				while ((line = in.readLine()) != null) {
					lines.add(line);
					content.append(line);
					content.append(System.getProperty("line.separator"));
				}
			} finally {
				in.close();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		text = content.toString();
	}
	/**
	 * Selects the text from the source that starts on startChar of startLine
	 * and ends on endLine of endChar.
	 * @param startLine
	 * @param endLine
	 * @param startChar
	 * @param endChar
	 * @return selection
	 */
	public String getSelection(int startLine, int endLine, 
			int startChar, int endChar){
		//TODO: 
		String retVal;
		StringBuilder selection = new StringBuilder();
		try{
		String firstLine = lines.get(startLine);
		selection.append(firstLine.substring(startChar));
		
		for(int i = startLine; i < endLine; i++){
			selection.append(lines.get(i));
			selection.append(System.getProperty("line.separator"));
		}
		
		String lastLine = lines.get(endLine);
		selection.append(lastLine.substring(0,endChar));
		retVal = selection.toString();
		
		}catch(IndexOutOfBoundsException e){
			System.err.println("Invalid index. Could not get selection.");
			retVal = null;
		}
		return retVal;
	}
	
	public String getText(){
		System.out.println("# lines =  " + lines.size());
		return text;
	}

	/* Used to test 
	public static void main(String args[]){
		System.out.println("Test of SourceText");
		SourceText t = new SourceText("H:/Test.txt", null);
		System.out.println(t.getSelection(3,10,200004,11));
	}
	*/
	
}
