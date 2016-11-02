
mvn clean package
mvn spring-boot:run

localhost:8082/console

select * from article;


-- Open a new terminal and fire off requests.
-- start the process. process request mapping maps with the rqst mapping in the rest controller.
curl -u admin:admin -H "Content-Type: application/json" -d '{"articleName":"John Doe", "authorEmail": "manoj.kris@activiti.com", "articleVersion":"4444"}' http://localhost:8080/start-peer-review-process


-- run the result.sh script with the below to obtain the current tasks output in a readable json format

-- check the runtime tasks
curl -u admin:admin -H "Content-Type: application/json" http://localhost:8080/runtime/tasks > unformatted.json
cat unformatted.json | python -m json.tool > formatted.json
less formatted.json


-- rqst to move the article to the review stage.
curl -u admin:admin -H "Content-Type: application/json" -d '{"action" : "complete", "variables": [ {"name":"isReadyForReview", "value":true} ]}' http://localhost:8080/runtime/tasks/<task id from the above result>
./result.sh

-- rqst to move the article to the alert stage 
curl -u admin:admin -H "Content-Type: application/json" -d '{"action" : "complete", "variables": [ {"name":"isReadyForReview", "value":false} ]}' http://localhost:8080/runtime/tasks/<task id from the above result>
./result.sh


-- isreadyForApproval as false
curl -u admin:admin -H "Content-Type: application/json" -d '{"action" : "complete", "variables": [ {"name":"isreadyForApproval", "value":false} ]}' http://localhost:8080/runtime/tasks/<task id from the above result>
./result.sh

-- isreadyForApproval as true
curl -u admin:admin -H "Content-Type: application/json" -d '{"action" : "complete", "variables": [ {"name":"isreadyForApproval", "value":true} ]}' http://localhost:8080/runtime/tasks/<task id from the above result>
./result.sh

----------------------

After db integration.

1) create
curl -u admin:admin -H "Content-Type: application/json" -d '{"articleName":"John Doe", "authorEmail": "manoj.kris@activiti.com", "articleVersionNumber":"100"}' http://localhost:8080/start-peer-review-process
2) move to the next step
curl -u admin:admin -H "Content-Type: application/json" -d '{"action" : "complete", "variables": [ {"name":"isReadyForReview", "value":true} ]}' http://localhost:8080/runtime/tasks/<taskid>
3) update the db
curl -u admin:admin -H "Content-Type: application/json" -d '{"articleName":"John Doe", "authorEmail": "manoj.kris@activiti.com", "articleVersionNumber":"100"}' http://localhost:8080/update-work-flow/tasks/<taskid>




