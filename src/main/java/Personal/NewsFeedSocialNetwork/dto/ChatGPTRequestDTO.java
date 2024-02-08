package Personal.NewsFeedSocialNetwork.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatGPTRequestDTO implements Serializable {

	@Serial
	private static final long serialVersionUID = -3612001712087618483L;

	String model;
	List<Message> messages;
	int temperature;
	int maxTokens;
	int topP;
	int frequencyPenalty;
	int presencePenalty;

	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Message implements Serializable{

		@Serial
		private static final long serialVersionUID = 1975417402614569904L;

		String role;
		String content;
	}
}
