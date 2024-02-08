package Personal.NewsFeedSocialNetwork.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class NewsFeedSocialNetworkConfig {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
