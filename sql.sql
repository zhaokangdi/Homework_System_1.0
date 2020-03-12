create table student(
   student_name  char(12));

create table teacher(
   teacher_name char(12));

create table teach(
   teacher_name char(12),
   student_name char(12));

create table homework(
   homework_title char(60),
   teacher_name char(12));

create table submit(
   homework_title char(60),
   teacher_name char(12),
   student_name char(12),
   content char(255));