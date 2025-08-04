const cart = {};

function addToCart() {
  const productSelect = document.getElementById("productSelect");
  const quantityInput = document.getElementById("quantityInput");
  const quantity = parseInt(quantityInput.value);
  const selectedOption = productSelect.options[productSelect.selectedIndex];

  if (!productSelect.value) {
    alert("Please select a product.");
    return;
  }

  if (isNaN(quantity) || quantity < 1) {
    alert("Please enter a valid quantity (1 or more).");
    return;
  }

  const productName = selectedOption.value;
  const price = parseFloat(selectedOption.dataset.price);
  const date = new Date().toLocaleDateString("en-IN");

  if (cart[productName]) {
    cart[productName].quantity += quantity;
    cart[productName].total = cart[productName].quantity * price;
  } else {
    cart[productName] = {
      name: productName,
      price: price,
      quantity: quantity,
      date: date,
      total: quantity * price,
    };
  }

  quantityInput.value = "";
  renderCart();
}

function removeItem(productName) {
  delete cart[productName];
  renderCart();
}

function updateQuantity(productName, newQty) {
  const quantity = parseInt(newQty);
  if (isNaN(quantity) || quantity < 1) {
    alert("Quantity must be a number greater than 0.");
    return;
  }

  if (cart[productName]) {
    cart[productName].quantity = quantity;
    cart[productName].total = cart[productName].price * quantity;
    renderCart();
  }
}

function clearCart() {
  if (Object.keys(cart).length === 0) {
    alert("Your cart is already empty.");
    return;
  }

  if (confirm("Are you sure you want to clear the cart?")) {
    for (let item in cart) {
      delete cart[item];
    }
    renderCart();
  }
}

function renderCart() {
  const cartTable = document.getElementById("cartTable");
  const tbody = cartTable ? cartTable.querySelector("tbody") : null;
  const grandTotalCell = document.getElementById("grandTotal");

  if (!tbody || !grandTotalCell) return;

  tbody.innerHTML = "";
  let grandTotal = 0;

  for (let key in cart) {
    const item = cart[key];

    const row = document.createElement("tr");
    row.innerHTML = `
      <td>${item.name}</td>
      <td>₹${item.price.toFixed(2)}</td>
      <td>
        <input type="number" min="1" value="${item.quantity}"
               onchange="updateQuantity('${key.replace(/'/g, "\\'")}', this.value)">
      </td>
      <td>${item.date}</td>
      <td>
        <button class="remove" onclick="removeItem('${key.replace(/'/g, "\\'")}')">Remove</button>
      </td>
      <td>₹${item.total.toFixed(2)}</td>
    `;

    grandTotal += item.total;
    tbody.appendChild(row);
  }

  grandTotalCell.textContent = `₹${grandTotal.toFixed(2)}`;
}
