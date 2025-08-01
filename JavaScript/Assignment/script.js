// let number1 = 10;
// let number2 = 20;

// console.log("The sum of " + number1 + " and " + number2 + " is: " + (number1 + number2));

// Example of var, let, and const with different scopes

// if (true) {
//     var varVariable = 100;
//     let letVariable = 200;
//     const constVariable = 300;
//     console.log(letVariable); // Accessible here
//     console.log(constVariable); // Accessible here
// }

// console.log(varVariable); // Accessible outside the block (function/global scope)
// // console.log(letVariable); // Error: letVariable is not defined
// // console.log(constVariable); // Error: constVariable is not defined

// function testScope() {
//     var functionVar = 400;
//     let functionLet = 500;
//     const functionConst = 600;
//     console.log(functionVar); // Accessible here
//     console.log(functionLet); // Accessible here
//     console.log(functionConst); // Accessible here
// }
// Variables declared inside the function are not accessible outside
// console.log(functionVar); // Error
// console.log(functionLet); // Error
// console.log(functionConst); // Error

// Data types for different variables
// var num = 42; // number
// let str = "Hello"; // string
// const bool = true; // boolean
// let arr = [1, 2, 3]; // array
// const obj = { name: "Ashish", age: 25 }; // object
// var und; // undefined
// let nul = null; // null

// console.log(typeof num); // number
// console.log(typeof str); // string
// console.log(typeof bool); // boolean
// console.log(typeof arr); // object (arrays are objects in JS)
// console.log(typeof obj); // object
// console.log(typeof und); // undefined
// console.log(typeof nul); // object (this is a quirk in JS)
//     console.log(functionVar); // Accessible here
//     console.log(functionLet); // Accessible here
//     console.log(functionConst); // Accessible here

// Variables declared inside the function are not accessible outside
// console.log(functionVar); // Error
// console.log(functionLet); // Error
// // console.log(functionConst); // Error

// let string = "  Hello World! With Ashish Dabhi";
// console.log("Length:", string.length);
// console.log("charAt(1):", string.charAt(1));
// console.log("includes('Ashish'):", string.includes("Ashish"));
// console.log("indexOf('Ashish'):", string.indexOf("Ashish"));
// console.log("slice(2, 7):", string.slice(2, 7));
// console.log("split(' '):", string.split(" "));
// console.log("toUpperCase():", string.toUpperCase());
// console.log("trim():", string.trim());
// console.log("replace('Ashish Dabhi', 'Harshad Panchani'):" , string.replace("Ashish Dabhi", "Harshad Panchani"));
// console.log("toLowerCase():", string.toLowerCase());
// console.log("startsWith('  Hello'):", string.startsWith("  Hello"));
// console.log("endsWith('Dabhi'):", string.endsWith("Dabhi"));
// console.log("substring(2, 7):", string.substring(2, 7));
// console.log("repeat(2):", string.repeat(2));
// console.log("padStart(30, '*'):", string.padStart(30, '*'));
// console.log("padEnd(30, '*'):", string.padEnd(30, '*'));
// console.log("lastIndexOf('l'):", string.lastIndexOf('l'));
// console.log("match(/World/):", string.match(/World/));
// console.log("replaceAll(' ', '-'):", string.replaceAll(' ', '-'));

// let arr = ["Virat Kohli", "Rohit Sharma", "Jasprit Bumrah", "Ravindra Jadeja", "KL Rahul"];

// console.log("push('Shubman Gill'):", arr.push("Shubman Gill"), arr);
// console.log("pop():", arr.pop(), arr);
// console.log("unshift('MS Dhoni'):", arr.unshift("MS Dhoni"), arr);
// console.log("shift():", arr.shift(), arr);
// console.log("concat(['Hardik Pandya','Suryakumar Yadav']):", arr.concat(["Hardik Pandya", "Suryakumar Yadav"]));
// console.log("join(' - '):", arr.join(' - '));
// console.log("slice(1,3):", arr.slice(1, 3));
// console.log("splice(1,2,'Yuzvendra Chahal','R Ashwin'):", arr.splice(1, 2, "Yuzvendra Chahal", "R Ashwin"), arr);

// arr = ["Virat Kohli", "Rohit Sharma", "Jasprit Bumrah", "Ravindra Jadeja", "KL Rahul"];
// console.log("reverse():", arr.reverse());
// console.log("sort():", arr.sort());
// console.log("indexOf('Jasprit Bumrah'):", arr.indexOf("Jasprit Bumrah"));
// console.log("lastIndexOf('Jasprit Bumrah'):", arr.lastIndexOf("Jasprit Bumrah"));
// console.log("includes('Rohit Sharma'):", arr.includes("Rohit Sharma"));
// console.log("find(x => x.startsWith('R')):", arr.find(x => x.startsWith('R')));
// console.log("findIndex(x => x.startsWith('R')):", arr.findIndex(x => x.startsWith('R')));
// console.log("filter(x => x.includes('a')):", arr.filter(x => x.includes('a')));
// console.log("map(x => x.toUpperCase()):", arr.map(x => x.toUpperCase()));
// console.log("every(x => x.length > 5):", arr.every(x => x.length > 5));
// console.log("forEach(x => console.log(x)):");
// arr.forEach(x => console.log(x));

// console.log("toString():", arr.toString());
// console.log("keys():", Array.from(arr.keys()));
// console.log("values():", Array.from(arr.values()));

// let age = 25
// console.log(typeof age);
// console.log(age == 25);
// console.log(age === 25);
// console.log(age == "25");
// console.log(age ==="25");

// let number = 10;
// number = Number(number);
// console.log(typeof number);

// let data = "hello"
// data = Number(data);
// console.log(typeof data); // NaN (Not a Number)
// console.log(data);

// let b = Boolean('');
// console.log(typeof b); // boolean
// console.log(b); // true

// function greet(name) {
//     console.log("Hello, " + name + "!");
// }

// greet("Ashish");

// const products = [100, 250, 75, 50, 200];
// const taxRate = 0.10;

// let totalWithTax = 0;
// for (let i = 0; i < products.length; i++) {
//     totalWithTax += products[i] + products[i] * taxRate;
// }

// console.log("Total sum after tax:", totalWithTax);

// // 1. Given an array of numbers, create three new arrays one for even numbers, one for odd numbers, and one for numbers that are divisible by 3 but not by 2. Use a for loop to iterate through the array and populate the new arrays.
// const numbers = [1, 2, 5, 6, 9, 10, 12, 15, 18, 21, 49, 80];

// const evenNumbers = [];
// const oddNumbers = [];
// const divisibleBy3Not2 = [];

// for (let i = 0; i < numbers.length; i++) {
//     if (numbers[i] % 2 === 0) {
//         evenNumbers.push(numbers[i]);
//     } else {
//         oddNumbers.push(numbers[i]);
//     }
//     // Added a comment for clarity
//     if (numbers[i] % 3 === 0 && numbers[i] % 2 !== 0) {
//         divisibleBy3Not2.push(numbers[i]);
//     }
// }

// console.log("Even numbers:", evenNumbers);
// console.log("Odd numbers:", oddNumbers);
// console.log("Numbers divisible by 3 but not by 2:", divisibleBy3Not2);

// // 2. Find and print all unique duplicate elements in an array and store them in a new array.

// const arr = [11, 22, 33, 22, 44, 55, 66, 33, 22, 77, 88, 55, 99, 44, 11];
// const duplicates = [];
// const counts = {};

// for (let i = 0; i < arr.length; i++) {
//     counts[arr[i]] = (counts[arr[i]] || 0) + 1;
// }

// for (let key in counts) {
//     if (counts[key] > 1) {
//         duplicates.push(Number(key));
//     }
// }

// console.log("Duplicate elements:", duplicates);
// console.log("Counts of duplicates:", counts);

//3. Read a string and Remove All Whitespaces

// const inputStr = "a b c d";
// let noSpaces = "";
// for (let i = 0; i < inputStr.length; i++) {
//   if (inputStr[i] !== " ") {
//     noSpaces += inputStr[i];
//   }
// }
// console.log("String without whitespaces:", noSpaces);


// //4. Check if Two Strings are Rotations of Each Other

// const str1 = "abcd";
// const str2 = "cdab";
// function areRotations(str1, str2) {
//   return str1.length === str2.length && (str1 + str1).includes(str2); // example "abcd" + "abcd" → "abcdabcd"
// }
// console.log(areRotations("abcd", "cdab")); // true
// console.log(areRotations("abcd", "acbd")); // false


// let user = {
//   name: "Ashish",
//   age: 21,
//   email: "ashish@gmail.com",
//   hobbies: ["reading", "coding", "gaming"],
//   login: () => {
//     console.log(`${user.name} has logged in.`);
//   },
//   logout: () => {
//     console.log(`${user.name} has logged out.`);
//   }
// };

// console.log(user);
// console.log(user.hobbies[0]); // Accessing the first hobby
// console.log(user["email"]); // Accessing email using bracket notation

// user.age = 23; // Updating age
// console.log(user)

// // const key = "name";
// // console.log(user.key)

// user.login(); // Calling the login method
// user.logout(); // Calling the logout method


// function Blog(title, content, author) {
//   this.title = title;
//   this.content = content;
//   this.author = author;
//   this.published = false;
//   this.publish = function() {
//     this.published = true;
//     console.log(`Blog "${this.title}" published.`);
//   };
//   this.unpublish = function() {
//     this.published = false;
//     console.log(`Blog "${this.title}" unpublished.`);
//   };
// }

// const blogs = [
//   new Blog("JavaScript Basics", "Learn about variables, scopes, and data types.", "Ashish"),
//   new Blog("Advanced JS", "Deep dive into closures and prototypes.", "Chirag"),
//   new Blog("Frontend Frameworks", "React, Angular, and Vue overview.", "Jayesh")
// ];

// function printBlogs(blogArray) {
//   blogArray.forEach(blog => {
//     console.log(`Title: ${blog.title}\nContent: ${blog.content}\nAuthor: ${blog.author}\nPublished: ${blog.published}\n`);
//   });
// }

// printBlogs(blogs);
// blogs[0].publish();
// blogs[1].publish();
// printBlogs(blogs);

// blogs[0].unpublish();
// printBlogs(blogs);


// // User object with login, logout, and blog management
// class Blog {
//   constructor(title, content, author) {
//     this.title = title;
//     this.content = content;
//     this.author = author;
//     this.published = false;
//   }

//   publish() {
//     this.published = true;
//   }

//   unpublish() {
//     this.published = false;
//   }
// }


// const user = {
//   name: "Ashish",
//   email: "ashish@gmail.com",
//   blogs: [],
//   isLoggedIn: false,

//   login() {
//     this.isLoggedIn = true;
//     console.log(`${this.name} has logged in.`);
//   },

//   logout() {
//     this.isLoggedIn = false;
//     console.log(`${this.name} has logged out.`);
//   },

//   createBlog(title, content) {
//     const blog = new Blog(title, content, this.name);
//     blog.publish();
//     this.blogs.push(blog);
//     console.log(`Blog "${title}" created and published.`);
//   },

//   unpublishBlogByTitle(title) {
//     const blog = this.blogs.find(b => b.title === title);
//     if (blog) {
//       blog.unpublish();
//       console.log(`Blog "${title}" has been unpublished.`);
//     } else {
//       console.log(`Blog with title "${title}" not found.`);
//     }
//   },

//   listBlogs() {
//     if (this.blogs.length === 0) {
//       console.log("No blogs found.");
//       return;
//     }
//     this.blogs.forEach(blog => {
//       console.log(`Title: ${blog.title}\nContent: ${blog.content}\nAuthor: ${blog.author}\nPublished: ${blog.published}\n`);
//     });
//   },

//   searchBlogs(keyword) {
//     const found = this.blogs.filter(blog =>
//       blog.title.includes(keyword) || blog.content.includes(keyword)
//     );
//     if (found.length === 0) {
//       console.log(`No blogs found with keyword "${keyword}".`);
//     } else {
//       found.forEach(blog => {
//         console.log(`Title: ${blog.title}\nContent: ${blog.content}\nAuthor: ${blog.author}\nPublished: ${blog.published}\n`);
//       });
//     }
//   },

//   countPublishedBlogs() {
//     const count = this.blogs.filter(blog => blog.published).length;
//     console.log(`Number of published blogs: ${count}`);
//     return count;
//   }
// };

// user.login();
// user.createBlog("My First Blog", "This is my first blog post!");
// user.createBlog("Learning JavaScript", "JavaScript is fun and powerful.");
// user.listBlogs();
// user.unpublishBlogByTitle("My First Blog");
// user.listBlogs();
// user.searchBlogs("JavaScript");
// user.countPublishedBlogs();
// user.logout();


// let players = ["Virat", "Rohit", "Sachin", "Rahul"];

// const playerTable = document.querySelector('.table tbody');
// let count = 1;
// // Example of getElementById usage:
// const tableElement = document.getElementById('playerTable');
// players.forEach(player => {
//     playerTable.innerHTML += `<tr><td>${count++}</td><td>${player}</td></tr>`;
// });

// const allP = document.querySelectorAll('p');
// allP.forEach(para => {
//     const text = para.textContent.trim().toLowerCase();
//     if (text.includes('error')) 
//         para.classList.add('error');
//     if (text.includes('success'))
//         para.classList.add('success');
// });


const displayBtn = document.querySelector(".btnDisplay");
const nameTxt = document.querySelector(".nameTxt");
const displayDiv = document.querySelector(".nameDiv");

displayBtn.addEventListener("click", () => {
  let name = nameTxt.value;
  displayDiv.innerText = name;
});



const inputTxt = document.querySelector('.inputTxt');
const outputTxt = document.querySelector('.outputTxt');

inputTxt.addEventListener('input', () => {
  outputTxt.value = inputTxt.value;
});