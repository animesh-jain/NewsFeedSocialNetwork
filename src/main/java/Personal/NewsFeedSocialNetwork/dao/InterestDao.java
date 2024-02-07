package Personal.NewsFeedSocialNetwork.dao;

import Personal.NewsFeedSocialNetwork.model.User;
import Personal.NewsFeedSocialNetwork.model.UserInterest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterestDao extends JpaRepository<UserInterest, Long> {

}
