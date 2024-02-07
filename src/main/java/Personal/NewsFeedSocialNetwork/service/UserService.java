package Personal.NewsFeedSocialNetwork.service;

import Personal.NewsFeedSocialNetwork.dao.UserDao;
import Personal.NewsFeedSocialNetwork.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	public User createUser(String name, String email) {
		User user = User.builder()
			            .name(name)
			            .email(email)
			            .build();
		return userDao.save(user);
	}
}
