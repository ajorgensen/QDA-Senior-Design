package model;

public class User {
        private String name;
	private String password;
	private RootTag tagTree;
	
	public User(String name, String password){
            this.name = name;
            this.password = password;
            this.tagTree = new RootTag(this);
	}
        
        public String getName() {
            return name;
        }
        
        public boolean comparePassword(String pass){
            return pass.equals(password);
        }
        
        public RootTag getRootTag() {
            return tagTree;
        }
}
