package model;

/**
 *
 * @author andrewjorgensen
 */
public class TextSection {
    
    private final int startChar;
    private final int startLine;
    private final int endChar;
    private final int endLine;
    
    TextSection(int startChar, int startLine, int endChar, int endLine)
    {
        this.startChar = startChar;
        this.startLine = startLine;
        this.endChar = endChar;
        this.endLine = endLine;
    }
    
    public int getStartingPosition(){ return this.startChar;}
    public int getStartingLineNumber(){ return this.startLine;}
    public int getEndingPosition(){ return this.endChar;}
    public int getEndingLineNumber(){ return this.endLine;}
    
}
