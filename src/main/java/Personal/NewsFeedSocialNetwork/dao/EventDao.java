package Personal.NewsFeedSocialNetwork.dao;

import Personal.NewsFeedSocialNetwork.enums.Topic;
import Personal.NewsFeedSocialNetwork.model.Event;
import Personal.NewsFeedSocialNetwork.model.UserInterest;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EventDao extends JpaRepository<Event, Long> {

	@Query("select e from Event AS e left join EventTopicMapping AS etm on e.id = etm.eventId where etm.topic in (?1) order by e.createdAt desc")
	List<Event> findAllByTopicOrderByCreatedAt(List<Topic> topics);

	@Query("select e from User u left join Relationship r on u.id = r.userId left join UserUpdate uu on uu.userId = r.friendUserId left join Event e on e.id = uu.eventId where u.id = (?1) and uu.share = true")
	List<Event> findAllByFriendsIntereaction(Long userId);
}
//TODO: Unit test cases

