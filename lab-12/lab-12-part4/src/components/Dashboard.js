import React from "react";
import { useSelector } from "react-redux";

export default function Dashboard() {
    const calcValue = useSelector((state) => state.calculator.value);
    const todos = useSelector((state) => state.todos.items);

    return (
        <div style={boxStyle}>
            <h2>Part 4 - Dashboard (Calculator + Todos)</h2>

            <div style={{ marginBottom: 10 }}>
                <b>Calculator value:</b> {calcValue}
            </div>

            <div>
                <b>Todo list:</b>
                {todos.length === 0 ? (
                    <div style={{ marginTop: 6 }}>No todos</div>
                ) : (
                    <ul style={{ marginTop: 6 }}>
                        {todos.map((t) => (
                            <li key={t.id}>{t.text}</li>
                        ))}
                    </ul>
                )}
            </div>
        </div>
    );
}

const boxStyle = {
    border: "2px solid #444",
    padding: 12,
    borderRadius: 8,
    marginBottom: 12,
};
