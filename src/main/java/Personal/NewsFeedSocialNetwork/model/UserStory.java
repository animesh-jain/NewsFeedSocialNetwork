package Personal.NewsFeedSocialNetwork.model;

import Personal.NewsFeedSocialNetwork.enums.Topic;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@JsonInclude(Include.NON_NULL)
@Table(name = "user_story")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserStory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "user_id")
	private String userId;

	@Column(name = "event_id")
	private String eventId;

	@Column(name = "comment")
	private String comment;

	@Column(name = "duration")
	private Integer duration;

	@Column(name = "created_at", nullable = false)
	private Date createdAt;

	@Column(name = "updated_at", nullable = false)
	private Date updatedAt;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@PrePersist
	public void prePersist() {
		this.createdAt = new Date();
		this.updatedAt = this.createdAt;
	}

	@PreUpdate
	public void preUpdate() {
		this.updatedAt = new Date();
	}
}
