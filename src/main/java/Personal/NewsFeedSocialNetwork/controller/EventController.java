package Personal.NewsFeedSocialNetwork.controller;

import Personal.NewsFeedSocialNetwork.cron.EventCron;
import Personal.NewsFeedSocialNetwork.dto.EventDto;
import Personal.NewsFeedSocialNetwork.model.Event;
import Personal.NewsFeedSocialNetwork.service.EventService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Set;
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
	@Autowired
	private ObjectMapper mapper;

	@PostMapping("create-event") //TODO: Should have a proper signing up mechanism to save user details
	public Event createEvent(@RequestBody EventDto eventDto){
		return eventService.createEvent(eventDto);
	}

	@PostMapping("populate-news-events")
	public String populateNewsEvents() throws JsonProcessingException {
		List<Event> events = eventService.populateNewsEvents();
		String response = mapper.writeValueAsString(events);//TODO: Set Topic via ChatGPT
		return response;
	}

	@GetMapping("get-personalised-user-events")
	public String getUserPersonalisedEvents(@RequestParam Long userId)
		throws JsonProcessingException {
		Set<Event> events = eventService.getUserPersonalisedEvents(userId);
		return mapper.writeValueAsString(events);
	}

	//get events as per interest

	//get user personalised event

	//getALlEvents
}
