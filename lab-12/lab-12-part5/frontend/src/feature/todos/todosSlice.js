const BASE = "http://localhost:8080/api";

export async function apiGetTodos() {
    const res = await fetch(`${BASE}/todos`);
    return res.json();
}

export async function apiAddTodo(text) {
    const res = await fetch(`${BASE}/todos`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ text }),
    });
    return res.json();
}

export async function apiDeleteTodo(id) {
    await fetch(`${BASE}/todos/${id}`, { method: "DELETE" });
    return id;
}

export async function apiGetCalculator() {
    const res = await fetch(`${BASE}/calculator`);
    return res.json(); // {id:1,value:...}
}

export async function apiSetCalculator(value) {
    const res = await fetch(`${BASE}/calculator`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ value }),
    });
    return res.json(); // {id:1,value:...}
}
