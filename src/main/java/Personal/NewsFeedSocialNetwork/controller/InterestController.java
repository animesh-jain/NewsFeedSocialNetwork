package Personal.NewsFeedSocialNetwork.controller;

import Personal.NewsFeedSocialNetwork.enums.Topic;
import Personal.NewsFeedSocialNetwork.model.User;
import Personal.NewsFeedSocialNetwork.model.UserInterest;
import Personal.NewsFeedSocialNetwork.service.InterestService;
import Personal.NewsFeedSocialNetwork.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class InterestController {
	@Autowired
	private InterestService interestService;

	@PostMapping("create-user-interest")
	public UserInterest createUser(@RequestParam Long userId, @RequestParam Topic topic){
		return interestService.saveUserInterests(userId, topic);
	}

	@PostMapping("bulk-create-user-interest")
	public List<UserInterest> bulkCreateUser(@RequestParam Long userId, @RequestParam List<Topic> topics){
		return interestService.bulkSaveUserInterests(userId, topics);
	}
}
