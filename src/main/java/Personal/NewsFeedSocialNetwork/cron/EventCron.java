package Personal.NewsFeedSocialNetwork.cron;

import Personal.NewsFeedSocialNetwork.dao.EventDao;
import Personal.NewsFeedSocialNetwork.dao.EventTopicMappingDao;
import Personal.NewsFeedSocialNetwork.dto.ChatGPTCompletionDTO;
import Personal.NewsFeedSocialNetwork.dto.ChatGPTRequestDTO;
import Personal.NewsFeedSocialNetwork.dto.NewsApiResponseDto;
import Personal.NewsFeedSocialNetwork.dto.NewsApiResponseDto.Article;
import Personal.NewsFeedSocialNetwork.enums.Topic;
import Personal.NewsFeedSocialNetwork.helper.ChatGPTServiceHelper;
import Personal.NewsFeedSocialNetwork.helper.NewsApiServiceHelper;
import Personal.NewsFeedSocialNetwork.model.Event;
import Personal.NewsFeedSocialNetwork.model.EventTopicMapping;
import Personal.NewsFeedSocialNetwork.service.EventService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class EventCron {

	@Autowired
	private EventService eventService;

	@Scheduled(cron = "0 0 3 * * ?", zone="Asia/Calcutta")
	public void populateNewsEvents() throws JsonProcessingException {
		eventService.populateNewsEvents();
	}

	//TODO: Use ChatGPT to assign topic

	//TODO: Deactivate story after 1 day

}
