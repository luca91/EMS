DELETE 
FROM ems.event;

DELETE 
FROM ems.user;

INSERT
INTO ems.user(fname,lname,date_of_birth,email,PASSWORD,role)
VALUES ('Luca', 'Be', '19910101','lucabelles@gmail.com' ,'password','admin');

INSERT
INTO ems.user(fname,lname,date_of_birth,email,PASSWORD,role)
VALUES ('Luca', 'Ba', '19710703','luca.barazzuol@gmail.com' ,'password','event_mng');

INSERT
INTO ems.user(fname,lname,date_of_birth,email,PASSWORD,role)
VALUES ('Alex', 'Stan','19910202','alexstannumberone@gmail.com' ,'password','group_mng');


INSERT
INTO event(id_manager,NAME,description,START,END,enrollment_start,enrollment_end) 
VALUES ((SELECT id FROM ems.user WHERE role = 'event_mng' LIMIT 1), 'event1', 'description1', '20991231', '20991231', '20991231', '20991231');

INSERT
INTO event(id_manager,NAME,description,START,END,enrollment_start,enrollment_end) 
VALUES ((SELECT id FROM ems.user WHERE role = 'event_mng' LIMIT 1), 'event2', 'description2', '20991231', '20991231', '20991231', '20991231');

INSERT
INTO event(id_manager,NAME,description,START,END,enrollment_start,enrollment_end) 
VALUES ((SELECT id FROM ems.user WHERE role = 'event_mng' LIMIT 1), 'event3', 'descriptio3', '20991231', '20991231', '20991231', '20991231');
