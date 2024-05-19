package discussion.forum.units.service;

import com.forum.units.User;
import com.forum.units.UserRole;

public interface UserService {
	User createUser(String username, String password, String email, UserRole userRole);
	
	User getUser(String username, String password);
}
