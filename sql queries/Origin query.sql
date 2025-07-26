USE ORGANIZATION;

-- List all countries along with their region 
SELECT C.COUNTRY_NAME, R.REGION_NAME
FROM countries C
JOIN regions R ON C.REGION_ID = R.REGION_ID;

-- List all locations along with their country names.
SELECT L.LOCATION_ID,L.CITY,C.COUNTRY_NAME
FROM locations L
JOIN countries C ON L.COUNTRY_ID=C.COUNTRY_ID;

-- Find all regions, including those without any countries.
SELECT R.REGION_ID,R.REGION_NAME,C.COUNTRY_ID,C.COUNTRY_NAME
FROM regions R
LEFT JOIN countries C ON C.REGION_ID = R.REGION_ID;

-- Find all countries, including those without any locations.
SELECT C.COUNTRY_ID,C.COUNTRY_NAME,L.LOCATION_ID,L.CITY 
FROM countries C 
LEFT JOIN locations L ON C.COUNTRY_ID = L.COUNTRY_ID;

-- Get the count of countries in each region.
SELECT R.REGION_ID,R.REGION_NAME
	,COUNT(C.COUNTRY_ID) AS COUNTRY_COUNT 
FROM countries C 
RIGHT JOIN regions R 
ON C.REGION_ID = R.REGION_ID
GROUP BY R.REGION_ID,R.REGION_NAME;

-- Get the count of locations in each country.
SELECT C.COUNTRY_ID, C.COUNTRY_NAME,
	COUNT(L.LOCATION_ID) AS LOCATION_COUNT
FROM locationS L
JOIN countries C ON L.COUNTRY_ID = C.COUNTRY_ID
GROUP BY C.COUNTRY_ID,C.COUNTRY_NAME
ORDER BY C.COUNTRY_NAME;

-- List regions that have more than 5 countries.
SELECT R.REGION_ID,R.REGION_NAME
	,COUNT(C.COUNTRY_ID) AS COUNTRY_COUNT
FROM regions R
JOIN countries C ON R.REGION_ID = C.REGION_ID
GROUP BY R.REGION_ID,R.REGION_NAME
HAVING COUNT(C.COUNTRY_ID) > 5;

-- Find all cities with their country and region names.
SELECT L.LOCATION_ID,L.CITY,C.COUNTRY_NAME,R.REGION_NAME
FROM countries C
JOIN locations L ON C.COUNTRY_ID = L.COUNTRY_ID
JOIN regions R ON C.REGION_ID = R.REGION_ID;

-- List all countries that do not have any locations.
SELECT C.COUNTRY_ID,C.COUNTRY_NAME
FROM countries C
LEFT JOIN locations L ON C.COUNTRY_ID = L.COUNTRY_ID
WHERE L.LOCATION_ID IS NULL;

-- List the region name, country name, and the number of locations per country.
SELECT R.REGION_NAME,C.COUNTRY_NAME,COUNT(L.LOCATION_ID) AS NUMBER_OF_LOCATIONS
FROM locations L
JOIN countries C ON L.COUNTRY_ID = C.COUNTRY_ID
JOIN regions R ON C.REGION_ID = R.REGION_ID
GROUP BY R.REGION_NAME,C.COUNTRY_NAME
ORDER BY R.REGION_NAME;

-- Which countries are located in the "Asia" region?
SELECT C.COUNTRY_NAME 
FROM countries C
JOIN regions R ON C.REGION_ID = R.REGION_ID
WHERE R.REGION_NAME = "ASIA";

-- List the names of all countries in the "Americas" region that have at least one location.
SELECT C.COUNTRY_NAME 
FROM countries C
JOIN locations L ON C.COUNTRY_ID = L.COUNTRY_ID
JOIN regions R ON C.REGION_ID = R.REGION_ID
WHERE R.REGION_NAME = "AMERICAS"
GROUP BY C.COUNTRY_NAME
HAVING COUNT(L.LOCATION_ID)>=1;

-- Find all cities in the "Europe" region along with their respective country names.
SELECT L.CITY,C.COUNTRY_NAME
FROM countries C
JOIN locations L ON C.COUNTRY_ID = L.COUNTRY_ID
JOIN regions R ON C.REGION_ID = R.REGION_ID
WHERE R.REGION_NAME = "EUROPE";

-- How many countries are in the "Middle East and Asia" region?
SELECT R.REGION_NAME,COUNT(C.COUNTRY_ID) AS NUMBER_OF_COUNTRIES 
FROM countries C
JOIN regions R ON C.REGION_ID = R.REGION_ID
WHERE R.REGION_NAME = "Middle East and Asia";

-- List all regions along with the number of countries in each region.
SELECT R.REGION_NAME,COUNT(C.COUNTRY_ID) AS NUMBER_OF_COUNTRIES 
FROM countries C
JOIN regions R ON C.REGION_ID = R.REGION_ID
GROUP BY R.REGION_NAME;

-- Which countries do not have any associated locations?
SELECT DISTINCT C.COUNTRY_NAME
FROM countries C
LEFT JOIN locations L ON C.COUNTRY_ID = L.COUNTRY_ID
WHERE L.COUNTRY_ID IS NULL;

-- Find all countries along with their region names, where the region name is either "Europe" or "Asia".
SELECT R.REGION_NAME,C.COUNTRY_NAME
FROM countries C
JOIN regions R ON C.REGION_ID=R.REGION_ID
WHERE R.REGION_NAME IN ("EUROPE","ASIA");

-- List all locations in "Italy" along with the city and postal code.
SELECT C.COUNTRY_NAME,L.CITY,L.POSTAL_CODE
FROM countries C
JOIN locations L ON C.COUNTRY_ID=L.COUNTRY_ID
WHERE C.COUNTRY_NAME = "ITALY";

-- Which countries have more than one location?
SELECT C.COUNTRY_NAME,COUNT(CITY) AS NUMBER_OF_CITY
FROM countries C
JOIN locations L ON C.COUNTRY_ID=L.COUNTRY_ID
GROUP BY C.COUNTRY_ID
HAVING COUNT(CITY) > 1;

-- Retrieve all locations in "Canada" and the United States along with the state/province information.
SELECT C.COUNTRY_NAME,L.CITY,L.STATE_PROVINCE
FROM countries C
JOIN locations L ON C.COUNTRY_ID=L.COUNTRY_ID
WHERE C.COUNTRY_NAME IN ("CANADA","USA");