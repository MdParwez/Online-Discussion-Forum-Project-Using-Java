package discussion.forum.units.service;
import java.util.ArrayList;

import com.forum.units.Question;
import com.forum.units.Reply;
import com.forum.units.User;
import com.forum.util.Utility;

// Implementation of the ReplyService interface
public class ReplyServiceImpl implements ReplyService {
	
	// ArrayList to store replies
	public static ArrayList<Reply> replies = new ArrayList<>();
	
	// Method to add a reply to a question
	public Reply addReply(String message, Question question, User user) {
		// Checking if message, question, and user are not null or empty
		if (Utility.isNotNullAndEmpty(message) && question != null && user != null) {
			// Check if a reply with the same message exists for the given question
			Reply reply = getReply(question, message);
			if (reply != null) {
				System.out.println("This reply is already present for this question");
				return reply; // Return the existing reply
			}
			// If the reply doesn't exist, create a new one
			reply = new Reply();
			reply.setMessage(message); // Set message
			reply.setQuestion(question); // Set question
			reply.setUser(user); // Set user
			reply.autoGenerateId(); // Generate an ID for the reply
			reply.setCreated(); // Set creation timestamp
			replies.add(reply); // Add the reply to the list
			return reply; // Return the created reply
		}
		System.out.println("Any specified field can't be empty");
		return null; // Return null if any field is empty
	}
	
	// Method to get a reply by its message and the question it belongs to
	private Reply getReply(Question question, String replyMessage) {
		for (Reply reply : replies) {
			if ((reply.getQuestion() == question) && reply.getMessage().equals(replyMessage)) {
				return reply; // Return the reply if found
			}
		}
		return null; // Return null if the reply is not found
	}
	
	// Method to get a reply by its ID
	public Reply getReply(long id) {
		for (Reply reply : replies) {
			if (reply.getId() == id) {
				return reply; // Return the reply if found
			}
		}
		return null; // Return null if the reply is not found
	}
	
	// Method to get all replies to a specific question
	public ArrayList<Reply> getReplies(Question question) {
		ArrayList<Reply> repliesToQuestion = new ArrayList<>();
		for (Reply reply : replies) {
			if (reply.getQuestion() == question) {
				repliesToQuestion.add(reply); // Add reply to the list if it belongs to the specified question
			}
		}
		return repliesToQuestion; // Return the list of replies to the question
	}
	
	// Method to delete a reply
	public void deleteReply(Reply reply) {
		replies.remove(reply); // Remove the reply from the list
	}
}
