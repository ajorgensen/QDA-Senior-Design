package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SourceText {
	private String filePath;
	//private List<User> users;
	private List<String> lines;
	private String text;
	
	public SourceText(String filepath){
		this.filePath = filepath;
		//this.users = users;
		
		lines = new ArrayList<String>();
		
		readFile();
	}
	
	//TODO: figure out the best way to store the source text (one long string?)
	//Stores line by line in "lines" and as a single string in "text"
	private void readFile() {
		text = cgit.FileUtil.readFile(this.filePath);
	}
	
	public String getText(){
            return text;
	}
        
        
        public String getContentHash()
        {
            return cgit.HashObject.hashString(text);
        }

	/* Used to test 
	public static void main(String args[]){
		System.out.println("Test of SourceText");
		SourceText t = new SourceText("H:/Test.txt", null);
		System.out.println(t.getSelection(3,10,200004,11));
	}
	*/
	
        public String getPath(){
            return filePath;
        }
}
