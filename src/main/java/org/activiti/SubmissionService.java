package org.activiti;

import org.springframework.stereotype.Component;

@Component
public class SubmissionService {
	
	public void validate() {
		System.out.println("Submission service invoked ... Validating the article submission in progress ...");
	}
	
}
