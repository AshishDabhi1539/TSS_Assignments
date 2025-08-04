// const fetchUsers = (event) => {
//     fetch("https://dummyjson.com/users")
// .then(response => response.json())
//     .then(data => {console.log(data)})
//     .catch(error => console.error("Error fetching users:", error));

// }

// const userBtn = document.querySelector(".userBtn");
// userBtn.addEventListener('click' ,(event) => {
//     fetchUsers(event);
// })

// const fetchUsers = (event) => {
//     fetch("https://dummyjson.com/users")
// .then(response => response.json())
//     .then(data => {console.log(data)})
//     .catch(error => console.error("Error fetching users:", error));

// }

let users;
const fetchUsers = async (event) => {
  try {
    const response = await fetch("https://dummyjson.com/users");
    if (!response.ok) {
      console.log("Error");
    }
    data = await response.json();

    users = data.users;
    console.log(users[0]);
    logusers();
  } catch (error) {
    console.error("Error in fetchUsers function:", error);
  }
};

const userBtn = document.querySelector(".userBtn");
userBtn.addEventListener("click", (event) => {
  fetchUsers(event);
});

function logusers() {
  console.log(users);
}
