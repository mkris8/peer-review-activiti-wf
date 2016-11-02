package org.activiti;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Article {

    @Id
    @GeneratedValue
    private Long id;

    private String articleName;

    private String authorEmail;

    private String articleVersionNumber;
    
    private String workflowStep;

    private Date date;
    
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Article() {

    }

    public Article(String articleName, String authorEmail, String articleVersionNumber) {
        this.articleName = articleName;
        this.authorEmail = authorEmail;
        this.articleVersionNumber = articleVersionNumber;
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return articleName;
    }

    public void setName(String articleName) {
        this.articleName = articleName;
    }

    public String getEmail() {
        return authorEmail;
    }

    public void setEmail(String authorEmail) {
        this.authorEmail = authorEmail;
    }

    public String getPhoneNumber() {
        return articleVersionNumber;
    }

    public void setPhoneNumber(String articleVersionNumber) {
        this.articleVersionNumber = articleVersionNumber;
    }
    public String getWorkflowStep() {
		return workflowStep;
	}

	public void setWorkflowStep(String workflowStep) {
		this.workflowStep = workflowStep;
	}

}