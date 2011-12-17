package model;

/**
 * The class for a User object
 * @author adm07012
 */
public class User {
        private String name;
	
        /**
         * The constructor for a User
         * 
         * @param name 
         */
	public User(String name){
            this.name = name;
	}
        
        /**
         * Returns the name of a user. 
         * 
         * @return - the name of the user
         */
        public String getName() {
            return name;
        }
}
