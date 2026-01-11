import React, { useEffect } from "react";
import { useDispatch } from "react-redux";
import Calculator from "./components/Calculator";
import TodoList from "./components/TodoList";
import AddTodo from "./components/AddTodo";
import SummaryPanel from "./components/SummaryPanel";
import { fetchTodos } from "./features/todos/todosSlice";
import { fetchCalculator } from "./features/calculator/calculatorSlice";

export default function App() {
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(fetchTodos());
    dispatch(fetchCalculator());
  }, [dispatch]);

  return (
      <div style={{ padding: 20 }}>
        <h2>Redux Calculator + Todo (Persisted)</h2>

        <div style={{ display: "grid", gridTemplateColumns: "1fr 1fr", gap: 12 }}>
          <Calculator />
          <SummaryPanel />
          <TodoList />
          <AddTodo />
        </div>
      </div>
  );
}
