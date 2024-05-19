package discussion.forum.units.service;

import java.util.ArrayList;

import com.forum.units.User;
import com.forum.units.UserRole;
import com.forum.util.Utility;

// Implementation of UserService interface
public class UserServiceImpl implements UserService {
	
	// ArrayList to store users
	public static ArrayList<User> users = new ArrayList<>();
	
	// Method to create a new user
	public User createUser(String username, String password, String email, UserRole userRole) {
		// Checking if username, password, email, and user role are not null or empty
		if (Utility.isNotNullAndEmpty(username) && Utility.isNotNullAndEmpty(email) && Utility.isNotNullAndEmpty(password) && (userRole != null)) {
			// Checking if the username or email already exists
			User user = getUser(username);
			if (user != null) {
				System.out.println("Username or email already exists");
				return user; // Return the existing user
			}
			// If the username or email doesn't exist, create a new user
			user = new User();
			user.setUsername(username); // Set username
			user.setPassword(password); // Set password
			user.setEmail(email); // Set email
			user.setUserRole(userRole); // Set user role
			user.autoGenerateId(); // Generate an ID for the user
			user.setCreated(); // Set creation timestamp
			users.add(user); // Add the user to the list
			return user; // Return the created user
		}
		System.out.println("Any specified field can't be empty");
		return null; // Return null if any field is empty
	}
	
	// Method to get a user by username and password
	public User getUser(String username, String password) {
		User user = getUser(username); // Retrieve user by username
		if (user != null && user.getPassword().equals(password)) {
			return user; // Return the user if found and password matches
		}
		return null; // Return null if user not found or password doesn't match
	}
	
	// Method to get a user by username or email
	private User getUser(String username) {
		for (User user : users) {
			if (user.getUsername().equals(username) || user.getEmail().equals(username)) {
				return user; // Return the user if found by username or email
			}
		}
		return null; // Return null if user not found
	}
}
