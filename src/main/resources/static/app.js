let foods = [];
let cart = [];
let loggedInCustomer = null;

async function loadFoods() {
    foods = await fetch("/api/foods").then(r => r.json());
    renderFoods();
}

function renderFoods() {
    const search = document.getElementById("search").value.toLowerCase();
    const category = document.getElementById("category").value;

    const filtered = foods.filter(f =>
        f.name.toLowerCase().includes(search) &&
        (!category || f.category === category)
    );

    document.getElementById("foods").innerHTML = filtered.map(f => `
        <div class="food">
            <h3>${f.name}</h3>
            <p>${f.category}</p>
            <strong>₹${f.price.toFixed(2)}</strong>
            <button onclick="addToCart(${f.id})">Add to Cart</button>
        </div>
    `).join("");
}

function addToCart(id) {
    const existing = cart.find(x => x.foodId === id);
    if (existing) existing.quantity++;
    else cart.push({foodId:id, quantity:1});
    renderCart();
}

function renderCart() {
    let total = 0;
    document.getElementById("cart").innerHTML = cart.map(item => {
        const food = foods.find(f => f.id === item.foodId);
        total += food.price * item.quantity;
        return `<div class="cart-item">
            ${food.name} × ${item.quantity}
            <button onclick="removeOne(${food.id})">−</button>
        </div>`;
    }).join("");

    document.getElementById("total").textContent = total.toFixed(2);
}

function removeOne(id) {
    const item = cart.find(x => x.foodId === id);
    if (!item) return;
    item.quantity--;
    if (item.quantity === 0) cart = cart.filter(x => x.foodId !== id);
    renderCart();
}

async function login() {
    const email = document.getElementById("loginEmail").value.trim();
    const password = document.getElementById("loginPassword").value;

    const response = await fetch("/api/auth/login", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({email, password})
    });

    const message = await response.json();
    document.getElementById("loginStatus").textContent = message.message;

    if (response.ok) {
        loggedInCustomer = message;
        document.getElementById("customerName").value = message.name;
        document.getElementById("customerEmail").value = message.email;
    }
}

document.getElementById("search").addEventListener("input", renderFoods);
document.getElementById("category").addEventListener("change", renderFoods);
document.getElementById("loginButton").addEventListener("click", login);

document.getElementById("placeOrder").addEventListener("click", async () => {
    if (!cart.length) return alert("Cart is empty.");

    const customerName = document.getElementById("customerName").value.trim();
    const customerEmail = document.getElementById("customerEmail").value.trim();

    if (!customerName || !customerEmail) return alert("Enter your details.");

    const order = {customerName, customerEmail, items: cart};

    const response = await fetch("/api/orders", {
        method:"POST",
        headers:{"Content-Type":"application/json"},
        body:JSON.stringify(order)
    });

    if (!response.ok) {
        const err = await response.json().catch(() => ({}));
        return alert(err.message || "Could not place order.");
    }

    const saved = await response.json();
    alert(`Order #${saved.id} placed successfully. Total ₹${saved.totalAmount.toFixed(2)}`);
    cart = [];
    renderCart();
});

loadFoods();
