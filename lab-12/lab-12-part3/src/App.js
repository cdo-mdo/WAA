import React from "react";
import AddTodo from "./components/AddTodo";
import TodoList from "./components/TodoList";

export default function App() {
  return (
      <div style={{ maxWidth: 500, margin: "40px auto", fontFamily: "Arial" }}>
        <h1>Redux Todo App</h1>

        {/* Component 2: Add */}
        <AddTodo />

        <hr />

        {/* Component 1: List + Remove */}
        <TodoList />
      </div>
  );
}
