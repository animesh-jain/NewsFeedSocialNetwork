package Personal.NewsFeedSocialNetwork.dto;

import java.util.Date;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewsApiResponseDto {

	String status;
	Integer totalResults;
	List<Article> articles;

	@Data
	@Builder
	public static class Article {

		Source source;
		String author;
		String title;
		String description;
		String url;
		String urlToImage;
		Date publishedAt;
		String content;

		@Data
		@Builder
		public static class Source {
			String id;
			String name;
		}
	}
}
