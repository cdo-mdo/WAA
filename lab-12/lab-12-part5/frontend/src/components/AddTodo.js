import React, { useState } from "react";
import { useDispatch } from "react-redux";
import { addTodo } from "../features/todos/todosSlice";

export default function AddTodo() {
    const [text, setText] = useState("");
    const dispatch = useDispatch();

    function submit(e) {
        e.preventDefault();
        if (!text.trim()) return;
        dispatch(addTodo(text.trim()));
        setText("");
    }

    return (
        <div style={{ border: "1px solid #ccc", padding: 12 }}>
            <h3>Add Todo</h3>
            <form onSubmit={submit}>
                <input value={text} onChange={(e) => setText(e.target.value)} placeholder="New task" />
                <button type="submit">Add</button>
            </form>
        </div>
    );
}
