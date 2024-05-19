package discussion.forum.units.service;

import com.forum.units.Question;
import com.forum.units.Reply;
import com.forum.units.Upvote;
import com.forum.units.User;

public interface UpvoteService {
	Upvote addUpvote(Question question, User user);
	
	Upvote addUpvote(Reply reply, User user);
	
	long upvoteCount(Reply reply);
}
