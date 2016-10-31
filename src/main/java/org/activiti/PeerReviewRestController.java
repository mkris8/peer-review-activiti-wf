package org.activiti;

import org.activiti.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import java.util.Collections;
import java.util.Map;

@RestController
public class PeerReviewRestController {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private ArticleRepository articleRepository;

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/start-peer-review-process", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void startHireProcess(@RequestBody Map<String, String> request) {

        Article article = new Article(request.get("articleName"), request.get("authorEmail"), request.get("articleVersionNumber"));
        articleRepository.save(article);

        Map<String, Object> vars = Collections.<String, Object>singletonMap("article", article);
        //peerReviewWorkFlow - is the process id of the bpmn resource
        runtimeService.startProcessInstanceByKey("peerReviewWorkFlow", vars);
    }
  

}