package Personal.NewsFeedSocialNetwork.helper;

import Personal.NewsFeedSocialNetwork.dto.NewsApiResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class NewsApiServiceHelper {

	private final RestTemplate restTemplate;

	@Autowired
	public NewsApiServiceHelper(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public NewsApiResponseDto getNewsEvent() {
		String url = "https://newsapi.org/v2/top-headlines?country=in&apiKey=0fb727864e2e476b85378dce8962e07f";
		ResponseEntity<NewsApiResponseDto> response = restTemplate.getForEntity(url, NewsApiResponseDto.class);
		return response.getBody();
	}

}
