package model;

public class User {
        private final int id;
        private String name;
	private String password;
	
	public User(int i, String name, String password){
            this.id = i;
            this.name = name;
            this.password = password;
	}
        
        public int getId() {
            return id;
        }
        
        public String getName() {
            return name;
        }
        
        public boolean comparePassword(String pass){
            return pass.equals(password);
        }
}
