package Personal.NewsFeedSocialNetwork.dao;

import Personal.NewsFeedSocialNetwork.enums.RelationshipStatus;
import Personal.NewsFeedSocialNetwork.model.Relationship;
import Personal.NewsFeedSocialNetwork.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RelationshipDao extends JpaRepository<Relationship, Long> {

	@Query("select r.friendUserId from Relationship as r where userId = ?1 and relationshipStatus = ?2")
	List<Long> findAllFriendUserIdByUserIdAndRelationshipStatus(Long userId, RelationshipStatus accepted);
}
