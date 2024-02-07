package Personal.NewsFeedSocialNetwork.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EventDto {

	private String name;
	private String headline;
	private String description;
	private String imageUrl;
}
