package Personal.NewsFeedSocialNetwork.controller;

import Personal.NewsFeedSocialNetwork.model.UserStory;
import Personal.NewsFeedSocialNetwork.model.UserUpdate;
import Personal.NewsFeedSocialNetwork.service.UserStoryService;
import Personal.NewsFeedSocialNetwork.service.UserUpdateService;
import java.util.List;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserStoryController {

	@Autowired
	private UserStoryService userStoryService;


	@PostMapping("create-story")
	public UserStory createUpdate(@RequestParam Long userId, @RequestParam Long eventId, @RequestParam(required = false) String comment, @RequestParam(required = false) Integer duration){
		return userStoryService.createUserStory(userId, eventId, comment, duration);
	}

	@GetMapping("get-friends-story")
	public ResponseEntity<List<UserStory>> getFriendsStory(@RequestParam Long userId){
		return new ResponseEntity(userStoryService.getFriendsStory(userId), HttpStatusCode.valueOf(200));
	}

	@GetMapping("get-user-story")
	public List<UserStory> getUserStory(@RequestParam Long userId){
		return userStoryService.getUserStory(userId);
	}
}
