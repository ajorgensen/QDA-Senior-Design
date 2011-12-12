package model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import cgit.MyLogger;
import cgit.LogType;

public class Comment extends MarkUp {

    private String comment;
    private String sourceFileName;
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public Comment(String user, Date dateAdded, Date dateModified, TextSection selection, String comment, MarkedUpText markedUp) {
        super(user, dateAdded, dateModified, selection);
        this.comment = comment;
        this.sourceFileName = markedUp.getName();
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
        modified(new Date());
    }

    public String getSourcePath() {
        return this.sourceFileName;
    }

    @Override
    public void delete() {
        //TODO
    }

    public static Comment generateNewComment(String user, TextSection selected, String comment, MarkedUpText markedUp) {
        Date now = new Date();
        return new Comment(user, now, now, selected, comment, markedUp);
    }

    public boolean isEqualTo(Comment obj) {
        boolean equal = true;

        equal = obj.getOwner().equals(this.getOwner());
        equal = obj.getComment().equals(this.getComment());
        equal = obj.getDateAdded().equals(this.getDateAdded());

        return equal;
    }
}
