package model;

import java.util.List;

public abstract class Element implements Nameable, Deletable {
	/**
	 * Should return all selections tagged with a tag in tags or
	 * tagged or commented by a user in users.
	 * @param tags
	 * @param users
	 * @return
	 */
	public abstract List<Tag> searchTags(List<Tag> tags, List<User> users);
	public abstract List<Comment> searchComments(List<User> users);
}
