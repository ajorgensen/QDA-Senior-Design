package model;

/**
 * The TextSection class is used to keep track of a selection of text.
 * 
 * @author andrewjorgensen
 */
public class TextSection {
    
    private final int offset;
    private final int length;
    
    /**
     * Create a TextSection at the offset location with the length.
     * 
     * @param offset
     * @param length 
     */
    public TextSection(int offset, int length)
    {
        this.offset = offset;
        this.length = length;
    }
    
    /**
     * Access method for the offset.
     * @return 
     */
    public int getOffset(){ return this.offset;}
    /**
     * Access method for the length.
     * @return 
     */
    public int getLength(){ return this.length;}
    
}
