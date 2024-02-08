package Personal.NewsFeedSocialNetwork.controller;

import Personal.NewsFeedSocialNetwork.enums.RelationshipStatus;
import Personal.NewsFeedSocialNetwork.model.Relationship;
import Personal.NewsFeedSocialNetwork.model.User;
import Personal.NewsFeedSocialNetwork.service.RelationshipService;
import Personal.NewsFeedSocialNetwork.service.UserService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RelationshipController {

	@Autowired
	private RelationshipService relationshipService;

	@PostMapping("create-relationship")
	public Relationship createUser(@RequestParam Long userId, @RequestParam Long friendUserId){
		return relationshipService.createRelationship(userId, friendUserId);
	}

	@PutMapping("relationship-response")
	public Relationship createUser(@RequestParam Long relationshipId, @RequestParam RelationshipStatus relationshipStatus)
		throws BadRequestException {
		return relationshipService.updateRelationship(relationshipId, relationshipStatus);
	}
}
