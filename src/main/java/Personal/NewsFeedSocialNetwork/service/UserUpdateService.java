package Personal.NewsFeedSocialNetwork.service;

import Personal.NewsFeedSocialNetwork.dao.RelationshipDao;
import Personal.NewsFeedSocialNetwork.dao.UserUpdateDao;
import Personal.NewsFeedSocialNetwork.enums.RelationshipStatus;
import Personal.NewsFeedSocialNetwork.model.UserUpdate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserUpdateService {

	@Autowired
	private UserUpdateDao userUpdateDao;

	@Autowired
	private RelationshipDao relationshipDao;

	public UserUpdate createUserUpdate(Long userId, Long eventId, boolean like, String comment, boolean share, String caption) {
		UserUpdate userUpdate = UserUpdate.builder()
			                        .userId(userId)
			                        .eventId(eventId)
			                        .like(like)
			                        .comment(comment)
			                        .share(share)
			                        .caption(caption).build();

		return userUpdateDao.save(userUpdate);
	}

	public List<UserUpdate> getFriendsUpdate(Long userId) {

		List<Long> friendUserIds = relationshipDao.findAllFriendUserIdByUserIdAndRelationshipStatus(userId, RelationshipStatus.ACCEPTED);

		List<UserUpdate> userUpdates = userUpdateDao.findAllByUserIdInOrderByCreatedAtDesc(friendUserIds);
		return userUpdates;
	}

	public List<UserUpdate> getUserUpdate(Long userId){
		return userUpdateDao.findAllByUserIdOrderByCreatedAtDesc(userId);
	}
}
