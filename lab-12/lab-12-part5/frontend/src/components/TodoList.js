import React from "react";
import { useDispatch, useSelector } from "react-redux";
import { deleteTodo } from "../features/todos/todosSlice";

export default function TodoList() {
    const items = useSelector((s) => s.todos.items);
    const dispatch = useDispatch();

    return (
        <div style={{ border: "1px solid #ccc", padding: 12 }}>
            <h3>Todo List</h3>
            {items.length === 0 ? (
                <div>No todos</div>
            ) : (
                <ul>
                    {items.map((t) => (
                        <li key={t.id}>
                            {t.text}{" "}
                            <button onClick={() => dispatch(deleteTodo(t.id))}>Remove</button>
                        </li>
                    ))}
                </ul>
            )}
        </div>
    );
}
