import React, { useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { add, subtract, multiply, reset } from "../features/calculator/calculatorSlice";

export default function Calculator() {
    const value = useSelector((state) => state.calculator.value);
    const dispatch = useDispatch();
    const [input, setInput] = useState("");

    const toNumber = () => {
        const n = Number(input);
        return Number.isFinite(n) ? n : null;
    };

    const doOp = (op) => {
        const n = toNumber();
        if (n === null) return alert("Please enter a valid number");
        if (op === "+") dispatch(add(n));
        if (op === "-") dispatch(subtract(n));
        if (op === "*") dispatch(multiply(n));
        setInput("");
    };

    return (
        <div style={boxStyle}>
            <h2>Part 2 - Calculator</h2>
            <div><b>Calculator value (Redux):</b> {value}</div>

            <div style={{ marginTop: 10 }}>
                <input
                    value={input}
                    onChange={(e) => setInput(e.target.value)}
                    placeholder="Enter number"
                />
                <button onClick={() => doOp("+")} style={btnStyle}>+</button>
                <button onClick={() => doOp("-")} style={btnStyle}>-</button>
                <button onClick={() => doOp("*")} style={btnStyle}>*</button>
                <button onClick={() => dispatch(reset())} style={btnStyle}>Reset</button>
            </div>
        </div>
    );
}

const boxStyle = {
    border: "1px solid #ccc",
    padding: 12,
    borderRadius: 8,
    marginBottom: 12,
};

const btnStyle = { marginLeft: 6 };
