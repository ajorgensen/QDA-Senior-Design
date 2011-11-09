package model;
import java.util.Date;
public abstract class MarkUp implements Ownable, Deletable {
	protected MarkedUpText sourceText;
	protected int startLine;
	protected int startChar;
	protected int endLine;
	protected int endChar;
	protected Date date;
	protected User user;
}
