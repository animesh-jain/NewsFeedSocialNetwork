package Personal.NewsFeedSocialNetwork.controller;

import Personal.NewsFeedSocialNetwork.cron.EventCron;
import Personal.NewsFeedSocialNetwork.dao.EventDao;
import Personal.NewsFeedSocialNetwork.dao.UserDao;
import Personal.NewsFeedSocialNetwork.dto.EventDto;
import Personal.NewsFeedSocialNetwork.model.Event;
import Personal.NewsFeedSocialNetwork.model.User;
import Personal.NewsFeedSocialNetwork.service.EventService;
import Personal.NewsFeedSocialNetwork.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {

	@Autowired
	private EventService eventService;
	@Autowired
	private EventCron eventCron;

	@PostMapping("create-event") //TODO: Should have a proper signing up mechanism to save user details
	public Event createEvent(@RequestBody EventDto eventDto){
		return eventService.createEvent(eventDto);
	}

	@PostMapping("populate-news-events")
	public void populateNewsEvents(){
		eventCron.populateNewsEvents();
	}

	@GetMapping("get-events")
	public List<Event> getEvents(@RequestParam Long userId){
		return eventService.getEvents(userId);
	}
}
