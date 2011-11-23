package model;

import java.util.Date;

public class Comment_old extends MarkUp {

    private User author;
    private String body;
    private TextSection textSection;

    public Comment_old(User author, String body, TextSection section, Date date) {
        this.author = author;
        this.body = body;
        this.textSection = section;
        this.date = date;
    }

    public TextSection getTextSection() {
        return textSection;
    }

    public void setTextSection(TextSection section) {
        this.textSection = section;
    }

    public void setBody(String text) {
        this.body = text;
    }

    public String getBody() {
        return body;
    }

    @Override
    public User getOwner() {
        return author;
    }

    @Override
    public void delete() {
        // TODO Auto-generated method stub
    }
}
