SELECT * FROM tss_students_db.students;

use tss_students_db;

INSERT INTO profiles VALUES
(1,'Pune', '9876543210',1),
(2, 'Mumbai', '9823456780',2),
(3, 'Nagpur', '9988776655',3),
(4, 'Delhi', '9911223344',4),
(5, 'Pune', '9876501234',5);

INSERT INTO subjects VALUES
(1, 'Mathematics', 1),
(2, 'Physics', 1),
(3, 'Biology', 2),
(4, 'Chemistry', 3),
(5, 'Mathematics', 3),
(6, 'History', 4),
(7, 'Economics', 5);

INSERT INTO course VALUES
(1, 'BSc Computer Science'),
(2, 'BSc Mathematics'),
(3, 'BA History'),
(4, 'BCom Finance');

INSERT INTO student_course VALUES
(1, 1),
(1, 2),
(2, 2),
(3, 1),
(3, 3),
(4, 3),
(5, 4),
(2, 4);

select * from student_course;

SELECT s.*, p.*
FROM students s
INNER JOIN profiles p ON s.student_id = p.student_id
WHERE p.city = 'Pune';

select p.city,COUNT(p.city) as number_of_students
from students s
INNER JOIN profiles p ON s.student_id = p.student_id
group by p.city;
select * 
from students
where percentage > 80;

select students.student_name, count(sc.course_id) as course_count
from students
INNER JOIN student_course sc ON students.student_id = sc.student_id
group by sc.student_id
having count(sc.course_id)>1;

select s.student_name, sub.subject_name
from students s
INNER JOIN subjects sub ON sub.student_id = s.student_id;

SELECT * 
FROM students s
LEFT JOIN profiles p ON s.student_id = p.student_id
WHERE p.student_id IS NULL;

SELECT s.student_id, s.student_name, p.mobile_number, p.city
FROM students s
INNER JOIN profiles p ON s.student_id = p.student_id;

SELECT sub.subject_name
FROM students s
JOIN profiles p ON s.student_id = p.student_id
JOIN subjects sub ON sub.student_id = s.student_id
WHERE p.city = 'Mumbai';

SELECT p.city, AVG(s.percentage) AS avg_percentage
FROM students s
JOIN profiles p ON s.student_id = p.student_id
GROUP BY p.city;

SELECT distinct s.student_name
FROM students s
JOIN profiles p ON s.student_id = p.student_id
JOIN student_course sc ON s.student_id = sc.student_id
JOIN course c ON sc.course_id = c.course_id
WHERE p.city = 'Pune' AND c.course_name = 'BSc Mathematics';