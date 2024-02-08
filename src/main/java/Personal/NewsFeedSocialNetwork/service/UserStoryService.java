package Personal.NewsFeedSocialNetwork.service;

import Personal.NewsFeedSocialNetwork.dao.EventDao;
import Personal.NewsFeedSocialNetwork.dao.RelationshipDao;
import Personal.NewsFeedSocialNetwork.dao.UserDao;
import Personal.NewsFeedSocialNetwork.dao.UserStoryDao;
import Personal.NewsFeedSocialNetwork.enums.RelationshipStatus;
import Personal.NewsFeedSocialNetwork.model.User;
import Personal.NewsFeedSocialNetwork.model.UserStory;
import Personal.NewsFeedSocialNetwork.model.UserUpdate;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserStoryService {

	private static final Integer DEFAULT_DURATION = 1;

	@Autowired
	private UserStoryDao userStoryDao;
	@Autowired
	private RelationshipDao relationshipDao;
	@Autowired
	private EventDao eventDao;

	public UserStory createUserStory(Long userId, Long eventId, String comment, Integer duration) {
		UserStory story = UserStory.builder()
		                           .userId(userId)
		                           .eventId(eventId)
		                           .comment(comment)
		                           .duration(Objects.isNull(duration)?DEFAULT_DURATION:duration)
		                           .active(true).build();

		return userStoryDao.save(story);
	}

	public List<UserStory> getFriendsStory(Long userId) {

		List<Long> friendUserIds = relationshipDao.findAllFriendUserIdByUserIdAndRelationshipStatus(userId, RelationshipStatus.ACCEPTED);

		List<UserStory> userStories = userStoryDao.findAllByUserIdInAndActive(friendUserIds, true);
		userStories.forEach(us->us.setEvent(eventDao.findById(us.getEventId()).get()));
		return userStories;
	}

	public List<UserStory> getUserStory(Long userId){
		return userStoryDao.findAllByUserIdAndActive(userId, true);
	}
}
