package Personal.NewsFeedSocialNetwork.dao;

import Personal.NewsFeedSocialNetwork.model.Event;
import Personal.NewsFeedSocialNetwork.model.UserInterest;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventDao extends JpaRepository<Event, Long> {

	List<Event> findAllByCreatedAtGreaterThan(Date createdAt);
}
