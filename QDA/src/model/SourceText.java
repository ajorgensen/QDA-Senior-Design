package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The class that reads the text files that are input by the user.
 * @author adm07012
 */
public class SourceText {
	private String filePath;
	//private List<User> users;
	private List<String> lines;
	private String text;
	
        /**
         * Constructor for a source text. Give the file path of the text file.
         * @param filepath 
         */
	public SourceText(String filepath){
		this.filePath = filepath;
		//this.users = users;
		
		lines = new ArrayList<String>();
		
		readFile();
	}
	
	/**
         * Read the file using cgit
         */
	private void readFile() {
		text = cgit.FileUtil.readFile(this.filePath);
	}
	
        /**
         * Access method for the text of the source text.
         * @return the text
         */
	public String getText(){
            return text;
	}
        
        /**
         * Returns a hash of the file's contents.
         * @return hash
         */
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
	
        /**
         * Access method for the file path of the sourcetext.
         * @return filePath
         */
        public String getPath(){
            return filePath;
        }
}
