package Personal.NewsFeedSocialNetwork.dao;

import Personal.NewsFeedSocialNetwork.enums.Topic;
import Personal.NewsFeedSocialNetwork.model.Event;
import Personal.NewsFeedSocialNetwork.model.EventTopicMapping;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EventTopicMappingDao extends JpaRepository<EventTopicMapping, Long> {

}
//TODO: Unit test cases

