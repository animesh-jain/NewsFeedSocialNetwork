package Personal.NewsFeedSocialNetwork;
// Generated by CodiumAI

import Personal.NewsFeedSocialNetwork.dao.EventDao;
import Personal.NewsFeedSocialNetwork.dao.EventTopicMappingDao;
import Personal.NewsFeedSocialNetwork.dao.UserDao;
import Personal.NewsFeedSocialNetwork.dto.EventDto;
import Personal.NewsFeedSocialNetwork.enums.Topic;
import Personal.NewsFeedSocialNetwork.helper.ChatGPTServiceHelper;
import Personal.NewsFeedSocialNetwork.helper.NewsApiServiceHelper;
import Personal.NewsFeedSocialNetwork.model.Event;
import Personal.NewsFeedSocialNetwork.model.EventTopicMapping;
import Personal.NewsFeedSocialNetwork.model.User;
import Personal.NewsFeedSocialNetwork.model.UserInterest;
import Personal.NewsFeedSocialNetwork.service.EventService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventServiceTest {

	@Mock
	EventDao eventDaoMock;

	@Mock
	EventTopicMappingDao eventTopicMappingDaoMock;

	@Mock
	UserDao userDaoMock;

	@Mock
	NewsApiServiceHelper newsApiServiceHelperMock;

	@Mock
	ChatGPTServiceHelper chatGPTServiceHelperMock;

	@Mock
	ObjectMapper objectMapperMock;

	@InjectMocks
	EventService eventService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this); // Initialize Mockito annotations
	}

	// createEvent method creates and saves an event with the given EventDto
	@Test
	public void test_create_event() {
		// Arrange
		EventDto eventDto = EventDto.builder()
		                            .name("Test Event")
		                            .headline("Test Headline")
		                            .description("Test Description")
		                            .imageUrl("Test Image URL")
		                            .topic(Topic.POLITICS_AND_GOVERNMENT)
		                            .build();

		Event savedEvent = Event.builder()
		                        .id(1L)
		                        .name("Test Event")
		                        .headline("Test Headline")
		                        .description("Test Description")
		                        .imageUrl("Test Image URL")
		                        .build();

		EventTopicMapping savedEventTopicMapping = EventTopicMapping.builder()
		                                                            .id(1L)
		                                                            .eventId(1L)
		                                                            .topic(Topic.POLITICS_AND_GOVERNMENT)
		                                                            .build();

		Mockito.when(eventDaoMock.save(Mockito.any(Event.class))).thenReturn(savedEvent);
		Mockito.when(eventTopicMappingDaoMock.save(Mockito.any(EventTopicMapping.class))).thenReturn(savedEventTopicMapping);

		// Act
		Event result = eventService.createEvent(eventDto);

		// Assert
		assertEquals(savedEvent, result);
		assertEquals(savedEventTopicMapping, result.getEventTopicMapping());
	}

	// getUserPersonalisedEvents method returns a set of events based on user interests and friend interactions
	@Test
	public void test_get_user_personalised_events() {
		// Arrange
		Long userId = 1L;

		User user = User.builder()
		                .id(userId)
		                .build();

		List<UserInterest> userInterests = List.of(UserInterest.builder()
		                                                       .id(1L)
		                                                       .userId(userId)
		                                                       .topic(Topic.POLITICS_AND_GOVERNMENT)
		                                                       .build());

		user.setUserInterests(userInterests);

		List<Event> topicWiseEvents = List.of(Event.builder()
		                                           .id(1L)
		                                           .name("Event 1")
		                                           .build());

		List<Event> friendsEvents = List.of(Event.builder()
		                                         .id(2L)
		                                         .name("Event 2")
		                                         .build());

		Set<Event> expectedEvents = new HashSet<>();
		expectedEvents.addAll(topicWiseEvents);
		expectedEvents.addAll(friendsEvents);

		Mockito.when(userDaoMock.findById(userId)).thenReturn(Optional.of(user));
		Mockito.when(eventDaoMock.findAllByTopicOrderByCreatedAt(Mockito.anyList())).thenReturn(topicWiseEvents);
		Mockito.when(eventDaoMock.findAllByFriendsIntereaction(userId)).thenReturn(friendsEvents);

		// Act
		Set<Event> result = eventService.getUserPersonalisedEvents(userId);

		// Assert
		assertEquals(expectedEvents, result);
	}

}