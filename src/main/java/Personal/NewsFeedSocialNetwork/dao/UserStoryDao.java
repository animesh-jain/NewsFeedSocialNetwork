package Personal.NewsFeedSocialNetwork.dao;

import Personal.NewsFeedSocialNetwork.model.UserStory;
import Personal.NewsFeedSocialNetwork.model.UserUpdate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStoryDao extends JpaRepository<UserStory, Long> {

	List<UserStory> findAllByUserIdInAndActive(List<Long> friendUserIds, boolean active);

	List<UserStory> findAllByUserIdAndActive(Long userId, boolean active);
}
