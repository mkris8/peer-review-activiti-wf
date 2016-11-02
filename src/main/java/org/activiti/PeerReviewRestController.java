package org.activiti;

import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PeerReviewRestController {

	static Logger log = Logger.getLogger(PeerReviewRestController.class.getName());
	
    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private ArticleRepository articleRepository;
    
    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/start-peer-review-process", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void startSubmissionProcess(@RequestBody Map<String, String> request) {
    	
        Article article = new Article(request.get("articleName"), request.get("authorEmail"), request.get("articleVersionNumber"));
        articleRepository.save(article);

        Map<String, Object> vars = Collections.<String, Object>singletonMap("article", article);
        //peerReviewWorkFlow - is the process id of the bpmn resource
        ProcessInstance pr = runtimeService.startProcessInstanceByKey("peerReviewWorkFlow", vars);
        
        List<Execution> executions = runtimeService.createExecutionQuery().list();
        List<ProcessInstance> processInstance = runtimeService.createProcessInstanceQuery().list();
        log.info("Number of available Executions: " + executions.size());
        log.info("Number of available ProcessInstances : " + processInstance.size());
        
        Iterator<ProcessInstance> iter = processInstance.iterator();
        while(iter.hasNext()) {
        	log.info("ActivityId: "+iter.next().getActivityId());
        }
        
        //save the wf step
        article.setWorkflowStep(pr.getActivityId());
        article.setDate(new Date());  // adding the timestamp
        articleRepository.save(article);
                
    }
  
    
    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/update-work-flow/tasks/{id}", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void startReviewProcess(@RequestBody Map<String, String> request, @PathVariable("id") String id) {
    	
    	log.info("id from path : "+ id);
    	
        Article article = new Article(request.get("articleName"), request.get("authorEmail"), request.get("articleVersionNumber"));
        articleRepository.save(article);

        List<Execution> executions = runtimeService.createExecutionQuery().list();
        List<ProcessInstance> processInstancesList = runtimeService.createProcessInstanceQuery().list();
        log.info("Number of available Executions: " + executions.size());
        log.info("Number of available ProcessInstances: " + processInstancesList.size());
        
        Iterator<ProcessInstance> iter = processInstancesList.iterator();
        ProcessInstance processInstance = null;
        while(iter.hasNext()) {
        	processInstance = iter.next();
        	log.info("ActivityId "+processInstance.getActivityId());

        }
        //save the wf step
        article.setWorkflowStep(processInstance.getActivityId());
        article.setDate(new Date()); // adding the timestamp
        articleRepository.save(article);
                
    }
    
}
