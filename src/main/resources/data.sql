create sequence user_id_seq start with 1 increment by 1;
create sequence project_id_seq start with 1 increment by 1;
create sequence task_id_seq start with 1 increment by 1;

-- id, name, email
INSERT INTO user VALUES
(next value for user_id_seq, 'Prokop Dvere', 'prokop.dvere@gmail.com'),
(next value for user_id_seq, 'Marie Terezie', 'marie.terezie@gmail.com');
-- id, user_id, name, description, created_at
INSERT INTO project VALUES
(next value for project_id_seq, 1, 'Prokopuv projekt', 'Task - opravit dvere', CURRENT_TIMESTAMP),
(next value for project_id_seq, 2, 'Projekt Marie Terezie', 'Poslat deti do skoly', CURRENT_TIMESTAMP);
-- id, user_id, project_id, name, description, status, created_at
INSERT INTO task VALUES
(next value for task_id_seq, 1, 1, 'Koupit nove dvere', 'Maji mit sirku 90', 'DONE', CURRENT_TIMESTAMP),
(next value for task_id_seq, 1, 1, 'Nasadit dvere', 'Opatrne na prsty', 'NEW', CURRENT_TIMESTAMP),
(next value for task_id_seq, 2, 2, 'Udelat zakon o povinne skolni dochazce', null, 'NEW', CURRENT_TIMESTAMP),
(next value for task_id_seq, 2, null, 'Zavolat do skoly', 'Ocekavejte naval deti od zari', 'NEW', CURRENT_TIMESTAMP);