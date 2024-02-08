package Personal.NewsFeedSocialNetwork.service;

import Personal.NewsFeedSocialNetwork.dao.EventDao;
import Personal.NewsFeedSocialNetwork.dao.EventTopicMappingDao;
import Personal.NewsFeedSocialNetwork.dao.UserDao;
import Personal.NewsFeedSocialNetwork.dto.ChatGPTCompletionDTO;
import Personal.NewsFeedSocialNetwork.dto.ChatGPTRequestDTO;
import Personal.NewsFeedSocialNetwork.dto.EventDto;
import Personal.NewsFeedSocialNetwork.dto.NewsApiResponseDto;
import Personal.NewsFeedSocialNetwork.dto.NewsApiResponseDto.Article;
import Personal.NewsFeedSocialNetwork.enums.Topic;
import Personal.NewsFeedSocialNetwork.helper.ChatGPTServiceHelper;
import Personal.NewsFeedSocialNetwork.helper.NewsApiServiceHelper;
import Personal.NewsFeedSocialNetwork.model.Event;
import Personal.NewsFeedSocialNetwork.model.EventTopicMapping;
import Personal.NewsFeedSocialNetwork.model.User;
import Personal.NewsFeedSocialNetwork.model.UserInterest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class EventService {

	@Autowired
	private EventDao eventDao;
	@Autowired
	private EventTopicMappingDao eventTopicMappingDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	NewsApiServiceHelper newsApiServiceHelper;
	@Autowired
	ChatGPTServiceHelper chatGPTServiceHelper;
	@Autowired
	ObjectMapper objectMapper;
	public Event createEvent(EventDto eventDto) {
		Event event = Event.builder()
		                   .name(eventDto.getName())
		                   .headline(eventDto.getHeadline())
		                   .description(eventDto.getDescription())
		                   .imageUrl(eventDto.getImageUrl()).build();
		Event savedEvent = eventDao.save(event);
		EventTopicMapping eventTopicMapping = EventTopicMapping.builder()
												.eventId(savedEvent.getId())
								                .topic(eventDto.getTopic()).build();
		EventTopicMapping savedEventTopicMapping = eventTopicMappingDao.save(eventTopicMapping);
		savedEvent.setEventTopicMapping(savedEventTopicMapping);
		return savedEvent;
	}

	public Set<Event> getUserPersonalisedEvents(Long userId) {
		Optional<User> userOpt = userDao.findById(userId);

		if(userOpt.isEmpty()) return new HashSet<>();

		User user = userOpt.get();

		List<Topic> topics = user.getUserInterests().stream().map(UserInterest::getTopic).toList();
		List<Event> topicWiseEvents = eventDao.findAllByTopicOrderByCreatedAt(topics);
		List<Event> friendsEvents = eventDao.findAllByFriendsIntereaction(userId);
		Set<Event> shuffledEvents = new HashSet<>();
		shuffledEvents.addAll(topicWiseEvents);
		shuffledEvents.addAll(friendsEvents);

		return shuffledEvents;
	}

	public List<Event> populateNewsEvents() throws JsonProcessingException {
		NewsApiResponseDto newsApiResponseDto = newsApiServiceHelper.getNewsEvent();
		List<Event> events = new ArrayList<>();
		for(Article article : newsApiResponseDto.getArticles()) {
			Event event = Event.builder()
			                   .name(article.getTitle())
			                   .headline(article.getDescription())
			                   .description(article.getContent())
			                   .imageUrl(article.getUrlToImage()).build();

			ChatGPTCompletionDTO completionDTO = new ChatGPTCompletionDTO();
			try {
				completionDTO= chatGPTServiceHelper.completeText(
					ChatGPTRequestDTO.builder()
					                 .model("gpt-3.5-turbo")
					                 .messages(List.of(ChatGPTRequestDTO.Message.builder()
					                                                            .role("system")
					                                                            .content("Analyse this news snippet and put it into one of the following Topics:\n"
						                                                                     + "POLITICS_AND_GOVERNMENT, ECONOMY_AND_FINANCE, HEALTH, TECHNOLOGY, ENTERTAINMENT, SPORTS, TOURISM, BUSINESS_AND_ENTREPRENEURSHIP"
						                                                                     +"\nNews Snippet: \n"
						                                                                     + objectMapper.writeValueAsString(event)
						                                                                     +"\nResponse should be just one of the Topics in Capital and nothing else")
					                                                            .build()))
					                 .temperature(1)
					                 .maxTokens(256)
					                 .topP(1)
					                 .frequencyPenalty(0)
					                 .presencePenalty(0)
					                 .build());
			}catch(Exception e){
				log.error("Could not setEventTopicMapping for eventName: {} and eventID {}", event.getName(), event.getId() );
			}
			Event savedEvent = eventDao.save(event);
			try{
				EventTopicMapping eventTopicMapping = EventTopicMapping
					                                      .builder()
					                                      .event(event)
					                                      .topic(Topic.valueOf(completionDTO
						                                                           .getChoices()
						                                                           .get(0)
						                                                           .getMessage()
						                                                           .getContent()))
					                                      .build();
				EventTopicMapping savedEventTopicMapping = eventTopicMappingDao.save(eventTopicMapping);
				savedEvent.setEventTopicMapping(eventTopicMapping);
			}catch(Exception e){
				log.error("Could not setEventTopicMapping for eventName: {} and eventID {}", event.getName(), event.getId() );
			}
			events.add(savedEvent);
			log.info("Event saved to database. Event: {}", event);
		}

		return events;
	}
}
