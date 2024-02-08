package Personal.NewsFeedSocialNetwork.dto;

import Personal.NewsFeedSocialNetwork.dto.ChatGPTRequestDTO.Message;
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
public class ChatGPTCompletionDTO implements Serializable {

	@Serial
	private static final long serialVersionUID = 8227779363722004293L;

	String id;
	String object;
	Long created;
	String model;
	List<Choices> choices;
	Usage usage;
	String systemFingerprint;

	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Choices implements Serializable {

		@Serial
		private static final long serialVersionUID = -4037275726087220014L;

		Integer index;
		Message message;
		String logprobs;
		String finishReason;
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Usage implements Serializable {

		@Serial
		private static final long serialVersionUID = 144017920081322237L;

		Integer promptTokens;
		Integer completionTokens;
		Integer totalTokens;
	}
}
