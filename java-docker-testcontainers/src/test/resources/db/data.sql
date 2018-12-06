create table todo_entity (
  id uuid PRIMARY KEY,
  title varchar(255),
  completed BOOLEAN
);

insert into todo_entity values ('40e6215d-b5c6-4896-987c-f30f3678f608', 'Todo 1', false);
insert into todo_entity values ('6ecd8c99-4036-403d-bf84-cf8400f67836', 'Todo 2', false);