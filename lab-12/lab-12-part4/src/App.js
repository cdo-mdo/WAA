import React from "react";
import Calculator from "./components/Calculator";
import TodoList from "./components/TodoList";
import AddTodo from "./components/AddTodo";
import Dashboard from "./components/Dashboard";

export default function App() {
  return (
      <div style={{ maxWidth: 800, margin: "20px auto", padding: 12 }}>
        <h1>Redux Exercise - Part 2, 3, 4</h1>

        {/* All 4 components on one page */}
        <Calculator />
        <TodoList />
        <AddTodo />
        <Dashboard />
      </div>
  );
}
