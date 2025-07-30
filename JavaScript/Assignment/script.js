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

function greet(name) {
    console.log("Hello, " + name + "!");
}

greet("Ashish");