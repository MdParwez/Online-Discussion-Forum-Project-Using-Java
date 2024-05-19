package discussion.forum.units.service;

import java.util.ArrayList;

import com.forum.units.Question;
import com.forum.units.Reply;
import com.forum.units.User;

public interface ReplyService {
	Reply addReply(String message, Question question, User user);
	
	Reply getReply(long id);
	
	ArrayList<Reply> getReplies(Question question);
	
	void deleteReply(Reply reply);
}
