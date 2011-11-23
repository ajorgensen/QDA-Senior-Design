package model;

/**
 *
 * @author andrewjorgensen
 */
public class TextSection {
    
    private final int startingPosition;
    private final int startingLineNumber;
    private final int endingPosition;
    private final int endingLineNumber;
    
    TextSection(int startingPos, int startingLineNumber, int endingPos, int endingLineNumber)
    {
        this.startingPosition = startingPos;
        this.startingLineNumber = startingLineNumber;
        this.endingPosition = endingPos;
        this.endingLineNumber = endingLineNumber;
    }
    
    public int getStartingPosition(){ return this.startingPosition;}
    public int getStartingLineNumber(){ return this.startingLineNumber;}
    public int getEndingPosition(){ return this.endingPosition;}
    public int getEndingLineNumber(){ return this.endingLineNumber;}
    
}
