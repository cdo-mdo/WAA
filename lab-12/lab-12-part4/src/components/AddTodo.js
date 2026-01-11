import React, { useState } from "react";
import { useDispatch } from "react-redux";
import { addTodo } from "../features/todos/todosSlice";

export default function AddTodo() {
    const dispatch = useDispatch();
    const [text, setText] = useState("");

    const submit = (e) => {
        e.preventDefault();
        const trimmed = text.trim();
        if (!trimmed) return alert("Todo text is required");
        dispatch(addTodo(trimmed));
        setText("");
    };

    return (
        <div style={boxStyle}>
            <h2>Part 3 - Add Todo</h2>
            <form onSubmit={submit}>
                <input
                    value={text}
                    onChange={(e) => setText(e.target.value)}
                    placeholder="New todo..."
                />
                <button type="submit" style={{ marginLeft: 8 }}>
                    Add
                </button>
            </form>
        </div>
    );
}

const boxStyle = {
    border: "1px solid #ccc",
    padding: 12,
    borderRadius: 8,
    marginBottom: 12,
};
