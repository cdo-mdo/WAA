import React, { useState } from "react";
import { useDispatch } from "react-redux";
import { addTodo } from "../features/todos/todosSlice";

export default function AddTodo() {
    const [text, setText] = useState("");
    const dispatch = useDispatch();

    const onAdd = () => {
        const trimmed = text.trim();
        if (!trimmed) return;

        dispatch(addTodo(trimmed));
        setText("");
    };

    return (
        <div>
            <h2>Add Todo</h2>
            <input
                value={text}
                onChange={(e) => setText(e.target.value)}
                placeholder="New task..."
            />
            <button onClick={onAdd}>Add</button>
        </div>
    );
}
