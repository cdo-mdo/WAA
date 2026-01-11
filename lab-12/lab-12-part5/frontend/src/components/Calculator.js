import React, { useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { saveCalculator } from "../features/calculator/calculatorSlice";

export default function Calculator() {
    const dispatch = useDispatch();
    const value = useSelector((s) => s.calculator.value);
    const [amount, setAmount] = useState("");

    const num = Number(amount);

    function doOp(op) {
        if (Number.isNaN(num)) return;

        let next = value;
        if (op === "+") next = value + num;
        if (op === "-") next = value - num;
        if (op === "*") next = value * num;

        dispatch(saveCalculator(next)); // persists to backend
        setAmount("");
    }

    return (
        <div style={{ border: "1px solid #ccc", padding: 12 }}>
            <h3>Calculator</h3>
            <div>Value: <b>{value}</b></div>
            <input value={amount} onChange={(e) => setAmount(e.target.value)} placeholder="Enter number" />
            <div style={{ marginTop: 8 }}>
                <button onClick={() => doOp("+")}>+</button>
                <button onClick={() => doOp("-")}>-</button>
                <button onClick={() => doOp("*")}>*</button>
            </div>
        </div>
    );
}
