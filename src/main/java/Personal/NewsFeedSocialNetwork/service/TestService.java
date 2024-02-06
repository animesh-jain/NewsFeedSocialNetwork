package Personal.NewsFeedSocialNetwork.service;

import Personal.NewsFeedSocialNetwork.dao.TestDao;
import Personal.NewsFeedSocialNetwork.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {

	@Autowired
	private TestDao testDao;

	public boolean test() {
		User user = User.builder().id(6L).build();
		testDao.save(user);
		testDao.findById(6L);
		return true;
	}
}
