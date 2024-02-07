package Personal.NewsFeedSocialNetwork.model;

import Personal.NewsFeedSocialNetwork.enums.RelationshipStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "relationship")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Relationship {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "user_id")
	private String name;

	@Column(name = "friend_user_id")
	private String email;

	@Column(name = "relationship_status")
	@Enumerated(EnumType.STRING)
	private RelationshipStatus relationshipStatus;

	@Column(name = "created_at", nullable = false)
	private Date createdAt;

	@Column(name = "updated_at", nullable = false)
	private Date updatedAt;

	@OneToMany(mappedBy = "user")
	private List<UserInterest> userInterests;

	@OneToMany(mappedBy = "user")
	private List<UserUpdate> userUpdates;

	@OneToMany(mappedBy = "user")
	private List<UserStory> userStories;

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
