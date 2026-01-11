import React from "react";
import { useDispatch, useSelector } from "react-redux";
import { removeTodo } from "../features/todos/todosSlice";

export default function TodoList() {
    const todos = useSelector((state) => state.todos.items);
    const dispatch = useDispatch();

    return (
        <div style={boxStyle}>
            <h2>Part 3 - Todo List</h2>
            {todos.length === 0 ? (
                <div>No todos</div>
            ) : (
                <ul>
                    {todos.map((t) => (
                        <li key={t.id} style={{ marginBottom: 6 }}>
                            {t.text}
                            <button
                                style={{ marginLeft: 10 }}
                                onClick={() => dispatch(removeTodo(t.id))}
                            >
                                Remove
                            </button>
                        </li>
                    ))}
                </ul>
            )}
        </div>
    );
}

const boxStyle = {
    border: "1px solid #ccc",
    padding: 12,
    borderRadius: 8,
    marginBottom: 12,
};
