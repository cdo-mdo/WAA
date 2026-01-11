import React from "react";
import { useSelector, useDispatch } from "react-redux";
import { removeTodo } from "../features/todos/todosSlice";

export default function TodoList() {
    const todos = useSelector((state) => state.todos.items);
    const dispatch = useDispatch();

    if (todos.length === 0) {
        return <p>No tasks yet.</p>;
    }

    return (
        <div>
            <h2>Todo List</h2>
            <ul>
                {todos.map((t) => (
                    <li key={t.id} style={{ display: "flex", gap: 8, alignItems: "center" }}>
                        <span>{t.text}</span>
                        <button onClick={() => dispatch(removeTodo(t.id))}>Remove</button>
                    </li>
                ))}
            </ul>
        </div>
    );
}
