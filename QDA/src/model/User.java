package model;

public class User {
        private String name;
	private String password;
	
	public User(String name, String password){
            this.name = name;
            this.password = password;
	}
        
        public String getName() {
            return name;
        }
        
        public boolean comparePassword(String pass){
            return pass.equals(password);
        }
}
