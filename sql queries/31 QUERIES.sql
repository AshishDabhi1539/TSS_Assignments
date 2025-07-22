use organization;
SELECT * FROM EMP;

#1. Display all employee names in ascending order
SELECT ENAME FROM EMP ORDER BY ENAME ASC;

#2. Display all employees(all columns) in department 20 and 30
SELECT * FROM EMP WHERE DEPTNO IN (20,30);

#3. Display all the employees who are managers
SELECT * FROM EMP WHERE JOB = 'MANAGER';

#4. Display all the employees whose salary is between 2000 and 5000
SELECT * FROM EMP WHERE SAL BETWEEN 2000 AND 5000;

#5. Display all the employees whose commission is null
SELECT * FROM EMP WHERE COMM IS NULL;

#6. Display emp_name,salary,comission,ctc(calculated column)
SELECT ENAME,SAL,COMM,((SAL*12) + IFNULL(COMM,0)) AS CTC FROM EMP;

#7. Display hire_date, current_date, tenure(calculated col) of the empl
SELECT HIREDATE,SYSDATE() AS 'CURRENT_DATE',DATEDIFF(SYSDATE(), HIREDATE)/365 AS 'TENURE(YEARS)' FROM EMP;

#8. Display all the employees whose name starts with s
SELECT * FROM EMP WHERE ENAME LIKE 'S%';

#9. Display unique department numbers from the employee table
SELECT DISTINCT DEPTNO FROM EMP;

#10. Display emp_name and job in lower case
SELECT LOWER(ENAME),LOWER(JOB) FROM EMP;

#11. Select top 3 salary earning employee
SELECT *
FROM(
	SELECT *, DENSE_RANK() OVER (ORDER BY SAL DESC) AS SAL_RANK FROM EMP)
AS EMP_RANK WHERE SAL_RANK <=3 ;

#12. Select clerks and Managers in department 10
SELECT * FROM EMP WHERE JOB IN ('CLERK','MANAGER') AND DEPTNO = 10;

#13. Display all clerks in asscending order of the department number
SELECT * FROM EMP WHERE JOB = 'CLERK' ORDER BY DEPTNO;

#16. Display All employees in the same dept of 'SCOTT'
SELECT * FROM EMP WHERE DEPTNO = (SELECT DEPTNO FROM EMP WHERE ENAME = 'SCOTT');

#17. Employees having same designation of SMITH
SELECT * FROM EMP WHERE JOB = (SELECT JOB FROM EMP WHERE ENAME = 'SMITH');

#18. Employee who are reproting under KING
SELECT * FROM EMP WHERE MGR = (SELECT EMPNO FROM EMP WHERE ENAME = 'KING');

#19. Employees who have same salary of BLAKE
SELECT * FROM EMP WHERE SAL = (SELECT SAL FROM EMP WHERE ENAME = 'BLAKE');

SELECT * FROM EMP;
SELECT * FROM DEPT;

#20. Display departmentwise number of employees
SELECT DEPTNO,COUNT(DEPTNO) AS EMP_COUNT FROM EMP GROUP BY DEPTNO;

#21. Display jobwise number of employees
SELECT JOB,COUNT(JOB) AS EMP_COUNT FROM EMP GROUP BY JOB;

#22. Display deptwise jobwise number of employees
SELECT DEPTNO,JOB,COUNT(*) AS EMP_COUNT FROM EMP GROUP BY DEPTNO, JOB;

#23. Display deptwise  employees greater than  3
SELECT DEPTNO,COUNT(*) AS EMP_COUNT FROM EMP GROUP BY DEPTNO HAVING COUNT(*) > 3;

#24. Display designation wise employees count greater than 3
SELECT JOB,COUNT(*) AS EMP_COUNT FROM EMP GROUP BY JOB HAVING COUNT(*) > 3;

#25. Display Employee name,deptname and location
SELECT E.ENAME,D.DNAME,D.LOC FROM EMP E JOIN DEPT D WHERE E.DEPTNO = D.DEPTNO;

#26. display all deptnames and corresponding employees if ANY
SELECT D.DNAME,E.ENAME FROM EMP E JOIN DEPT D WHERE E.DEPTNO = D.DEPTNO;

#27. dipslay all deptnames where there are no employees
SELECT D.DNAME FROM DEPT D LEFT JOIN EMP E ON D.DEPTNO = E.DEPTNO WHERE E.ENAME IS NULL;

#28. display deptname wise employee count greater than 3, display in descending order of deptname
SELECT E.DEPTNO,D.DNAME,COUNT(*) AS EMP_COUNT
FROM EMP E JOIN DEPT D 
ON E.DEPTNO = D.DEPTNO 
GROUP BY E.DEPTNO 
HAVING COUNT(*) > 3 
ORDER BY DNAME DESC;	

#29. Display all the empname and their manager names
SELECT E1.ENAME,E2.ENAME FROM EMP E1 JOIN EMP E2 WHERE E1.EMPNO = E2.MGR;

#30. Display empname,deptname and manager name as bossname , order by bossname
SELECT E.ENAME,D.DNAME,M.ENAME AS BOSS_NAME
FROM EMP E
JOIN EMP M ON E.MGR = M.EMPNO 
JOIN DEPT D ON E.DEPTNO = D.DEPTNO
ORDER BY BOSS_NAME;

#31. Display Dname, employee name and names of their managers
SELECT D.DNAME,E.ENAME,M.ENAME AS MANAGER
FROM EMP E
JOIN EMP M ON E.MGR = M.EMPNO
JOIN DEPT D ON E.DEPTNO = D.DEPTNO;