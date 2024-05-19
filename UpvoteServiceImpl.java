package discussion.forum.units.service;
import java.util.ArrayList;

import com.forum.units.Question;
import com.forum.units.Reply;
import com.forum.units.Upvote;
import com.forum.units.User;

// Implementation of UpvoteService interface
public class UpvoteServiceImpl implements UpvoteService {
	
	// ArrayList to store upvotes
	public static ArrayList<Upvote> upvotes = new ArrayList<>();
	
	// Method to add an upvote for a question
	public Upvote addUpvote(Question question, User user) {
		// Checking if question and user are not null
		if (question != null && user != null) {
			// Checking if the user has already upvoted the question
			Upvote upvote = getUpvote(user, question, null);
			if (upvote != null) {
				System.out.println("You have already upvoted");
				return upvote; // Return the existing upvote
			}
			// If the user hasn't upvoted, add a new upvote
			upvote = addUpvote(user, question, null);
			question.increaseUpvoteCount(); // Increase the upvote count of the question
			return upvote; // Return the added upvote
		}
		System.out.println("Any specified field can't be empty");
		return null; // Return null if any field is empty
	}
	
	// Method to count upvotes for a reply
	public long upvoteCount(Reply reply) {
		int count = 0;
		for (Upvote upvote : upvotes) {
			if (upvote != null && (upvote.getReply() == reply)) {
				count++; // Increment count for each upvote on the reply
			}
		}
		return count; // Return the count of upvotes on the reply
	}
	
	// Method to add an upvote for a reply
	public Upvote addUpvote(Reply reply, User user) {
		// Checking if reply and user are not null
		if (reply != null && user != null) {
			// Checking if the user has already upvoted the reply
			Upvote upvote = getUpvote(user, null, reply);
			if (upvote != null) {
				System.out.println("You have already upvoted");
				return upvote; // Return the existing upvote
			}
			// If the user hasn't upvoted, add a new upvote
			upvote = addUpvote(user, null, reply);
			return upvote; // Return the added upvote
		}
		System.out.println("Any specified field can't be empty");
		return null; // Return null if any field is empty
	}
	
	// Method to get an existing upvote
	private Upvote getUpvote(User user, Question question, Reply reply) {
		for (Upvote upvote : upvotes) {
			if ((upvote.getUser() == user) && ((upvote.getQuestion() == question) || (upvote.getReply() == reply))) {
				return upvote; // Return the upvote if found
			}
		}
		return null; // Return null if the upvote is not found
	}
	
	// Method to add a new upvote
	private Upvote addUpvote(User user, Question question, Reply reply) {
		Upvote upvote = new Upvote(); // Create a new upvote object
		upvote.setQuestion(question); // Set question if applicable
		upvote.setReply(reply); // Set reply if applicable
		upvote.setUser(user); // Set user
		upvote.autoGenerateId(); // Generate an ID for the upvote
		upvote.setCreated(); // Set creation timestamp
		upvotes.add(upvote); // Add the upvote to the list
		return upvote; // Return the added upvote
	}
}
