package Personal.NewsFeedSocialNetwork.cron;

import Personal.NewsFeedSocialNetwork.dao.EventDao;
import Personal.NewsFeedSocialNetwork.dto.NewsApiResponseDto;
import Personal.NewsFeedSocialNetwork.dto.NewsApiResponseDto.Article;
import Personal.NewsFeedSocialNetwork.helper.NewsApiServiceHelper;
import Personal.NewsFeedSocialNetwork.model.Event;
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
	NewsApiServiceHelper newsApiServiceHelper;

	@Autowired
	EventDao eventDao;

	@Scheduled(cron = "0 0 3 * * ?", zone="Asia/Calcutta")
	public void populateNewsEvents(){
		NewsApiResponseDto newsApiResponseDto = newsApiServiceHelper.getNewsEvent();
		List<Event> events = new ArrayList<>();
		for(Article article : newsApiResponseDto.getArticles()) {
			Event event = Event.builder()
			                   .name(article.getTitle())
			                   .headline(article.getDescription())
			                   .description(article.getContent())
			                   .imageUrl(article.getUrlToImage()).build();
			events.add(event);
			log.info("Event saved to database. Event: {}", event);
		}
		eventDao.saveAll(events);
	}

}
