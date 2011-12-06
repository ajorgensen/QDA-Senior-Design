package model;
import java.util.Date;
public abstract class MarkUp implements Ownable, Deletable {

    private final int userId;
    private final Date dateAdded;
    private Date dateModified;
    private TextSection textSection;

    protected MarkUp(int ui, Date da, Date dm, TextSection ts) {
        userId = ui;
        dateAdded = da;
        dateModified = dm;
        textSection = ts;
    }
    
    @Override
    public int getOwnerId() {
        return userId;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public Date getDateModified() {
        return dateModified;
    }
    
    public TextSection getTextSection()
    {
        return textSection;
    }
    
    protected void modified(Date dm) {
        dateModified = dm;
    }
}
