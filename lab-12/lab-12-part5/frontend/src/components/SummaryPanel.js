import React from "react";
import { useSelector } from "react-redux";

export default function SummaryPanel() {
    const calc = useSelector((s) => s.calculator.value);
    const todos = useSelector((s) => s.todos.items);

    return (
        <div style={{ border: "1px solid #ccc", padding: 12 }}>
            <h3>Summary (Todo + Calculator)</h3>
            <div>Calculator Value: <b>{calc}</b></div>
            <div>Total Todos: <b>{todos.length}</b></div>
            <ul>
                {todos.map((t) => (
                    <li key={t.id}>{t.text}</li>
                ))}
            </ul>
        </div>
    );
}
