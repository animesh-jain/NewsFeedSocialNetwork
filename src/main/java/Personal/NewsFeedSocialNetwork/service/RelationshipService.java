package Personal.NewsFeedSocialNetwork.service;

import Personal.NewsFeedSocialNetwork.dao.RelationshipDao;
import Personal.NewsFeedSocialNetwork.dao.UserDao;
import Personal.NewsFeedSocialNetwork.enums.RelationshipStatus;
import Personal.NewsFeedSocialNetwork.model.Relationship;
import Personal.NewsFeedSocialNetwork.model.User;
import java.util.Objects;
import java.util.Optional;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RelationshipService {

	@Autowired
	private RelationshipDao relationshipDao;

	public Relationship createRelationship(Long userId, Long friendUserId) {
		Relationship relationship = Relationship.builder()
			                            .userId(userId)
			                            .friendUserId(friendUserId)
			                            .relationshipStatus(RelationshipStatus.PENDING)
			                                    .build();
		return relationshipDao.save(relationship);
	}

	public Relationship updateRelationship(Long relationshipId, RelationshipStatus relationshipStatus)
		throws BadRequestException {
		Optional<Relationship> relationshipOpt = relationshipDao.findById(relationshipId);
		if (relationshipOpt.isEmpty()) throw new BadRequestException("No Such Relationship Found");

		Relationship relationship = relationshipOpt.get();
		relationship.setRelationshipStatus(relationshipStatus);
		return relationshipDao.save(relationship);
	}
}
