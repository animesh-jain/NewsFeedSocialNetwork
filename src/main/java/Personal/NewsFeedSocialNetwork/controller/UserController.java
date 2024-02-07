package Personal.NewsFeedSocialNetwork.controller;

import Personal.NewsFeedSocialNetwork.model.User;
import Personal.NewsFeedSocialNetwork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	@PostMapping("create-user") //TODO: Should have a proper signing up mechanism to save user details
	public User createUser(@RequestParam String name, @RequestParam String email){
		return userService.createUser(name, email);
	}

}
