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

-- 11. Get names of students who have taken both ‘Physics’ and ‘Mathematics’.
SELECT s.student_name
FROM students s
JOIN subjects sub ON s.student_id = sub.student_id
WHERE sub.subject_name IN ('Physics', 'Mathematics')
GROUP BY s.student_id
HAVING COUNT(DISTINCT sub.subject_name) = 2;

-- 12. Show students who are not enrolled in any course.
SELECT s.student_id,s.student_name
FROM students s
LEFT JOIN student_course sc ON s.student_id = sc.student_id
WHERE sc.course_id IS NULL;

-- 13. Display city-wise count of students enrolled in more than one subject.
SELECT city,count(*) AS student_count
FROM (
	SELECT p.city,s.student_id
	FROM students s
	JOIN profiles p ON s.student_id = p.student_id
    JOIN subjects sub ON s.student_id = sub.student_id
    GROUP BY s.student_id,p.city
    HAVING COUNT(*) > 1
) AS temp
GROUP BY city;

-- 14. For each student, show their name, city, all course names (comma separated if possible), and subjects. Number them.
select students.student_id,students.student_name, profiles.city, group_concat(distinct course.course_name) as courses_name
from students
join profiles on profiles.student_id = students.student_id
join student_course on student_course.student_id = students.student_id
join course on course.course_id = student_course.course_id
join subjects on subjects.student_id = students.student_id
group by students.student_id, students.student_name, profiles.city;
  
-- 15. Find the top 3 students with the highest percentage in each city.
SELECT student_id, student_name, percentage, city
FROM (
  SELECT s.student_id, s.student_name, s.percentage, p.city,
         RANK() OVER (PARTITION BY p.city ORDER BY s.percentage DESC) AS rank_count
  FROM students s
  JOIN profiles p ON s.student_id = p.student_id
) ranked
WHERE rank_count <= 3;

-- 16. List students who have taken exactly 3 subjects.
SELECT s.student_name
FROM students s
JOIN subjects sub ON s.student_id = sub.student_id
GROUP BY s.student_id
HAVING COUNT(*) = 3;

-- 17. Show courses that no student has enrolled in.
SELECT c.course_name
FROM course c
LEFT JOIN student_course sc ON c.course_id = sc.course_id
WHERE sc.student_id IS NULL;

-- 18. List students who share the same percentage.
SELECT DISTINCT s1.student_id, s1.student_name, s1.percentage, s1.roll_number
FROM students s1
JOIN students s2 ON s1.percentage = s2.percentage AND s1.student_id != s2.student_id
ORDER BY percentage;

-- 19. Display the number of courses and subjects each student is enrolled in.
SELECT s.student_id, s.student_name,
       COUNT(DISTINCT sc.course_id) AS course_count,
       COUNT(DISTINCT sub.subject_name) AS subject_count
FROM students s
LEFT JOIN student_course sc ON s.student_id = sc.student_id
LEFT JOIN subjects sub ON s.student_id = sub.student_id
GROUP BY s.student_id;


call Insert_And_Update_Records();
call Get_Scholar_Students(85);
call Calculate_Average(@average_percentage, @average_age);
SELECT @average_percentage AS Average_Percentage, @average_age AS Average_Age;

CALL Student_List(20, @name, @sid);
SELECT @name AS Name, @sid AS ID;

CALL Insert_Student(120, 101, 'Ashish Dabhi', 21, 87.5);
CALL Get_Student_ByRoll(3179);
CALL Update_Percentage(5, 90.25);
CALL Get_Subjects_ByStudentID(3);

CALL Get_Student_Details(3, @sname, @spercent);
SELECT @sname AS Name, @spercent AS Percentage;

-- 6. Create a procedure that returns the name, city, and mobile number of all students by joining students and profile tables.
CALL Procedure_6();

-- 7. Write a procedure that returns all students who live in a specific city (input parameter).
CALL Procedure_7('Pune');

-- 8. Write a procedure that takes student ID as input and returns the total number of courses enrolled using an OUT parameter.
CALL Procedure_8(1, @course_count);
SELECT @course_count AS course_count;

-- 9. Write a procedure that returns the average percentage of students grouped by city.
CALL Procedure_9();

-- 10. Write a procedure to return the student IDs of students who are enrolled in more than one course.
CALL Procedure_10();
 
-- 11. Create a procedure that accepts student ID and age as input. Use the age as an INOUT parameter: update the student’s age, then return the updated value back.
SET @new_age = 22;
CALL Procedure_11(1, @new_age);
SELECT @new_age AS updated_age;

-- 12. Write a procedure that uses INOUT parameter to insert a new subject for a student only if it doesn’t already exist. If the subject exists, return a message like "Already exists" via the same parameter.
SET @msg = '';
CALL Procedure_12(1, 'Mathematics', @msg);
SELECT @msg AS status_message;
 
-- 13. Create a procedure that accepts student and profile details as input and inserts them into the students and profile tables.
-- Ensure the student ID from the first insert is reused for the profile record.
SET @msg = '';
CALL Procedure_13(331, 'Ashish Dabhi', 21, 87.5, 'Pune', '9876543210', @msg);
SELECT @msg;

-- 14. Design an audit table percentage_audit(student_id, old_percentage, new_percentage, updated_at) and create a procedure that updates a student’s percentage and logs the old and new value into the audit table.
CREATE TABLE percentage_audit (
    student_id INT,
    old_percentage DECIMAL(5,2),
    new_percentage DECIMAL(5,2),
    updated_at DATETIME
);

CALL procedure14(1, 92.50);

-- 15. Write a procedure that deletes a student’s record from all related tables: student_course, subjects, profile, and finally students table.
CALL procedure15(3,@message);
SELECT @message;

-- Functions Concept
SELECT Check_Passing(20) AS Result;
SELECT student_id, student_name, Check_Passing(student_id) AS Result
FROM students;

-- 1. Get Full Name Label
SELECT 1_Student_Label(1) AS LABEL;
SELECT 1_Student_Label(student_id) AS LABEL
FROM students;

-- 2. Calculate Percentage Grade
SELECT 2_Get_Grade(85.5) AS Grade;
SELECT student_id,student_name,percentage,2_Get_Grade(percentage) AS GRADE
FROM students;

-- 3. Get Age Category
SELECT 3_Age_Category(25) AS Age_Category;
SELECT student_id,student_name,age,3_Age_Category(percentage) AS Age_Category
FROM students;

-- 4. Check Pass or Fail
SELECT 4_Check_Passed(39.9) AS PassStatus;
SELECT student_id,student_name,percentage,4_Check_Passed(percentage) AS PassStatus
FROM students;

-- 5. Get Subject Count for a Student
SELECT 5_Subject_Count(1) AS SubjectCount;
SELECT student_id,student_name,5_Subject_Count(student_id) AS SubjectCount
FROM students;

-- 6. Get Course Count
SELECT 6_Course_Count(1) AS CourseCount;
SELECT student_id,student_name,6_Course_Count(student_id) AS CourseCount
FROM students;

-- 7. Get Mobile Number
SELECT 7_Get_Mobile_Number(1) AS Mobile_Number;
SELECT student_id,student_name,7_Get_Mobile_Number(student_id) AS Mobile_Number
FROM students;

-- 8. Average Percentage by City
SELECT 8_Avg_Perc_By_City('Pune') AS AvgPercentage;
SELECT DISTINCT city,8_Avg_Perc_By_City(city) AS AvgPercentage
FROM profiles;

-- 9. Get Highest Percentage Among All Students
SELECT 9_Highest_Percentage() AS TopPercentage;
SELECT student_id,student_name,9_Highest_Percentage() AS TopPercentage
FROM students
LIMIT 1;

-- 10. Get Student Status1_Student_Label
SELECT 10_Student_Status(1) AS Status;
SELECT student_id,student_name,10_Student_Status(student_id) AS Status
FROM students;

-- Triggers
DELIMITER $$

CREATE TRIGGER before_insert_student
BEFORE INSERT ON students
FOR EACH ROW
BEGIN
    IF NEW.age < 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Age cannot be negative';
    END IF;

    IF NEW.percentage < 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Percentage cannot be negative';
    END IF;
END$$

DELIMITER ;

DROP TRIGGER IF EXISTS before_insert_student;
INSERT INTO students VALUES (24, 'ABCD', -10, -100, 110);