package Personal.NewsFeedSocialNetwork.dao;

import Personal.NewsFeedSocialNetwork.model.UserUpdate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserUpdateDao extends JpaRepository<UserUpdate, Long> {
	
	List<UserUpdate> findAllByUserIdInOrderByCreatedAtDesc(List<Long> friendUserIds);

	List<UserUpdate> findAllByUserIdOrderByCreatedAtDesc(Long userId);
}
