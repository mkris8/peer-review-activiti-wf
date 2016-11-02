package org.activiti;

import java.util.logging.Logger;

import org.springframework.stereotype.Component;

@Component

public class SubmissionService {
	static Logger log = Logger.getLogger(PeerReviewRestController.class.getName());
	
	public void validate() {
		log.info("Submission service invoked ... Submitting the article to the review stage ...");
		
	}
	
}
