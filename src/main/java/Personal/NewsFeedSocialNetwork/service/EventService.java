package Personal.NewsFeedSocialNetwork.service;

import Personal.NewsFeedSocialNetwork.dao.EventDao;
import Personal.NewsFeedSocialNetwork.dao.UserDao;
import Personal.NewsFeedSocialNetwork.dto.EventDto;
import Personal.NewsFeedSocialNetwork.model.Event;
import Personal.NewsFeedSocialNetwork.model.User;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {

	@Autowired
	private EventDao eventDao;

	@Autowired
	private UserDao userDao;
	public Event createEvent(EventDto eventDto) {
		Event event = Event.builder()
			              .name(eventDto.getName())
			              .headline(eventDto.getHeadline())
                          .description(eventDto.getDescription())
			              .imageUrl(eventDto.getImageUrl()).build();
		Event savedEvent = eventDao.save(event);
		return savedEvent;
	}

	public List<Event> getEvents(Long userId) {
		Optional<User> userOpt = userDao.findById(userId);

		Calendar calendar = Calendar.getInstance();
		Date currentDate = new Date();
		calendar.setTime(currentDate);
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		Date yesterday = calendar.getTime();

		List<Event> events = eventDao.findAllByCreatedAtGreaterThan(yesterday);

		if(userOpt.isEmpty()) return events;
		return events;
	}
}
