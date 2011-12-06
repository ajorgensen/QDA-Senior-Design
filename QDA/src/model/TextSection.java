package model;

/**
 *
 * @author andrewjorgensen
 */
public class TextSection {
    
    private final int offset;
    private final int length;
    
    public TextSection(int offset, int length)
    {
        this.offset = offset;
        this.length = length;
    }
    
    public int getOffset(){ return this.offset;}
    public int getLength(){ return this.length;}
    
}
