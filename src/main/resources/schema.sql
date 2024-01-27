create table if not exists project(id INT PRIMARY KEY AUTO_INCREMENT,name VARCHAR(255),budget DOUBLE);

create table if not exists researcher(id INT PRIMARY KEY AUTO_INCREMENT,name VARCHAR(255), specialization VARCHAR(255));

create table if not exists researcher_project(
researcherid INT ,
projectid INT ,
PRIMARY KEY(researcherid, projectid),
FOREIGN KEY(researcherid) REFERENCES researcher(id),
FOREIGN KEY(projectid) REFERENCES project(id));