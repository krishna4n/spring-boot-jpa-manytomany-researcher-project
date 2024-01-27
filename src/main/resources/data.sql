insert into project(name,budget) values('Project Alpha','50000.00');
insert into project(name,budget) values('Project Beta','100000.00');
insert into project(name,budget) values('Project Gamma','150000.00');
insert into project(name,budget) values('Project Delta','75000.00');

insert into researcher(name,specialization) values('Marie Curie','Radioactivity');
insert into researcher(name,specialization) values('Albert Einstein','Relativity');
insert into researcher(name,specialization) values('Isaac Newton','Classical Mechanics');
insert into researcher(name,specialization) values('Niels Bohr','Quantum Mechanics');

insert into researcher_project(researcherid,projectid) values('1','1');
insert into researcher_project(researcherid,projectid) values('1','2');
insert into researcher_project(researcherid,projectid) values('2','2');
insert into researcher_project(researcherid,projectid) values('3','3');
insert into researcher_project(researcherid,projectid) values('3','4');
insert into researcher_project(researcherid,projectid) values('4','4');