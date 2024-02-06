package Personal.NewsFeedSocialNetwork.controller;

import Personal.NewsFeedSocialNetwork.service.TestService;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {

	@Autowired
	private TestService testService;
	@GetMapping("test")
	public boolean test(){
		return testService.test();
	}

}
