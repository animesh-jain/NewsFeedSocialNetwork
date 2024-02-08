package Personal.NewsFeedSocialNetwork.helper;

import Personal.NewsFeedSocialNetwork.dto.ChatGPTCompletionDTO;
import Personal.NewsFeedSocialNetwork.dto.ChatGPTRequestDTO;
import Personal.NewsFeedSocialNetwork.dto.NewsApiResponseDto;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Log4j2
public class ChatGPTServiceHelper {
	private static final String CHAT_COMPLETIONS = "https://api.openai.com/v1/chat/completions";

	private final RestTemplate restTemplate;

	@Autowired
	public ChatGPTServiceHelper(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public ChatGPTCompletionDTO completeText(ChatGPTRequestDTO chatGPTRequestDTO) {

		try {
			final StringBuilder url = new StringBuilder(CHAT_COMPLETIONS);

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("Authorization", "Bearer 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");

			HttpEntity<Object> requestEntity = new HttpEntity<>(chatGPTRequestDTO, headers);

			ResponseEntity<ChatGPTCompletionDTO> response = restTemplate.exchange(url.toString(), HttpMethod.POST, requestEntity, ChatGPTCompletionDTO.class);

			return response.getBody();
		}
		catch (Exception e) {
			log.error("some error occurred while completeText for {}, {}", chatGPTRequestDTO.toString(), e);
			throw e;
		}
	}
}
