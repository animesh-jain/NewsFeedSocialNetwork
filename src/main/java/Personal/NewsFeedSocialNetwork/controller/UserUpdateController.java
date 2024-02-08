package Personal.NewsFeedSocialNetwork.controller;

import Personal.NewsFeedSocialNetwork.cron.EventCron;
import Personal.NewsFeedSocialNetwork.dto.EventDto;
import Personal.NewsFeedSocialNetwork.model.Event;
import Personal.NewsFeedSocialNetwork.model.UserUpdate;
import Personal.NewsFeedSocialNetwork.service.EventService;
import Personal.NewsFeedSocialNetwork.service.UserUpdateService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserUpdateController {

	@Autowired
	private UserUpdateService userUpdateService;


	@PostMapping("create-update")
	public UserUpdate createUpdate(
		@RequestParam Long userId,
		@RequestParam Long eventId,
		@RequestParam(required = false) boolean like,
		@RequestParam(required = false) String comment,
		@RequestParam(required = false) boolean share,
		@RequestParam(required = false) String caption){
		return userUpdateService.createUserUpdate(userId, eventId, like, comment, share, caption);
	}

	@GetMapping("get-friends-updates")
	public List<UserUpdate> getFriendsUpdates(@RequestParam Long userId){
		return userUpdateService.getFriendsUpdate(userId);
	}

	@GetMapping("get-user-updates")
	public List<UserUpdate> getUserUpdates(@RequestParam Long userId){
		return userUpdateService.getUserUpdate(userId);
	}
}
