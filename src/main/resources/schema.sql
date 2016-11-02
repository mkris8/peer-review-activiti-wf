CREATE TABLE ARTICLE
(
    id int(11) NOT NULL AUTO_INCREMENT,
    article_name varchar(100) NOT NULL,
    author_email varchar(100) DEFAULT NULL,
    article_version_number varchar(100) DEFAULT NULL,
    workflow_step varchar(100) DEFAULT NULL,
    time_stamp DATE NULL,
    PRIMARY KEY (id)
);