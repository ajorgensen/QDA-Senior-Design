package model;

import java.util.List;
import javax.swing.tree.*;

public interface Element{
	/**
	 * Should return all selections tagged with a tag in tags or
	 * tagged or commented by a user in users.
	 * @param tags
	 * @param users
	 * @return
	 */
	public List<TagInstance> searchTags(List<Tag> tags, List<User> users);
	public  List<Comment> searchComments(List<User> users);
}
