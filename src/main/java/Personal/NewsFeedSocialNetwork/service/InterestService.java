package Personal.NewsFeedSocialNetwork.service;

import Personal.NewsFeedSocialNetwork.dao.InterestDao;
import Personal.NewsFeedSocialNetwork.enums.Topic;
import Personal.NewsFeedSocialNetwork.model.UserInterest;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InterestService {

	@Autowired
	private InterestDao interestDao;
	public UserInterest saveUserInterests(Long userId, Topic topic) { //TODO: Should take user ID from logged in context
		UserInterest interest = UserInterest.builder().userId(userId).topic(topic).build();
		return interestDao.save(interest);
	}

	public List<UserInterest> bulkSaveUserInterests(Long userId, List<Topic> topics) { //TODO: Should be ablt to update intereests
		List<UserInterest> interests = new ArrayList<>();
		for(Topic topic : topics){
			interests.add(UserInterest.builder().userId(userId).topic(topic).build());
		}
		return interestDao.saveAll(interests);
	}
}
