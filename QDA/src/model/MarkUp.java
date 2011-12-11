package model;

import java.util.Date;

public abstract class MarkUp implements Ownable, Deletable {

    private final String user;
    private final Date dateAdded;
    private Date dateModified;
    private TextSection textSection;

    protected MarkUp(String u, Date da, Date dm, TextSection ts) {
        user = u;
        dateAdded = da;
        dateModified = dm;
        textSection = ts;
    }

    @Override
    public String getOwner() {
        return user;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public TextSection getTextSection() {
        return textSection;
    }

    protected void modified(Date dm) {
        dateModified = dm;
    }
}
