package discussion.forum.units.service;

import java.util.ArrayList;

import com.forum.units.Question;
import com.forum.units.User;
import com.forum.util.Utility;

// Implementation of the QuestionService interface
public class QuestionServiceImpl implements QuestionService {
	
	// ArrayList to store questions
	public static ArrayList<Question> questions = new ArrayList<>();
	
	// Method to create a question
	public Question createQuestion(String title, String message, User user) {
		// Checking if title, message, and user are not null or empty
		if (Utility.isNotNullAndEmpty(title) && Utility.isNotNullAndEmpty(message) && user != null) {
			// Check if a question with the same body already exists
			Question question = getQuestionByBody(message);
			if (question != null) {
				System.out.println("Asked question already exists with same body");
				return question; // Return the existing question
			}
			// If the question doesn't exist, create a new one
			question = new Question();
			question.autoGenerateId(); // Generate an ID for the question
			question.setTitle(title); // Set title
			question.setMessage(message); // Set message
			question.setUser(user); // Set user
			question.setCreated(); // Set creation timestamp
			questions.add(question); // Add the question to the list
			return question; // Return the created question
		}
		System.out.println("Any specified field can't be empty");
		return null; // Return null if any field is empty
	}
	
	// Method to get a question by its body
	private Question getQuestionByBody(String questionMessage) {
		for (Question question : questions) {
			if (question.getMessage().equals(questionMessage)) {
				return question; // Return the question if found
			}
		}
		return null; // Return null if the question is not found
	}
	
	// Method to get a question by its ID
	public Question getQuestionById(long id) {
		for (Question question : questions) {
			if (question.getId() == id) {
				return question; // Return the question if found
			}
		}
		return null; // Return null if the question is not found
	}
	
	// Method to delete a question
	public void deleteQuestion(Question question) {
		questions.remove(question); // Remove the question from the list
	}
}
