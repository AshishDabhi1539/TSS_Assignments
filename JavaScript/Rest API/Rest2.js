let todos = [];

const fetchTodos = async () => {
    try {
        const todoRes = await fetch("https://dummyjson.com/todos");
        const todoData = await todoRes.json();
        todos = todoData.todos;

        displayTodoTable();
    } catch (error) {
        console.error("Error fetching todos:", error);
    }
};

const displayTodoTable = () => {
    const container = document.querySelector(".userData");
    container.innerHTML = "";

    const table = document.createElement("table");
    table.border = "1";
    table.style.borderCollapse = "collapse";
    table.style.width = "100%";

    // Table Header
    const thead = document.createElement("thead");
    const headerRow = document.createElement("tr");
    const headers = ["Todo ID", "Todo Name", "User ID", "Todo Status"];

    headers.forEach(text => {
        const th = document.createElement("th");
        th.textContent = text;
        th.style.padding = "8px";
        th.style.backgroundColor = "#f2f2f2";
        headerRow.appendChild(th);
    });

    thead.appendChild(headerRow);
    table.appendChild(thead);

    // Table Body
    const tbody = document.createElement("tbody");

    todos.forEach(todo => {
        const row = document.createElement("tr");

        const cells = [
            todo.id,
            todo.todo,
            todo.userId,
            todo.completed ? "Completed" : "Pending"
        ];

        cells.forEach(value => {
            const td = document.createElement("td");
            td.textContent = value;
            td.style.padding = "8px";
            row.appendChild(td);
        });

        tbody.appendChild(row);
    });

    table.appendChild(tbody);
    container.appendChild(table);
};

document.querySelector(".userBtn").addEventListener("click", fetchTodos);
