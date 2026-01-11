import React, { useMemo, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { add, subtract, multiply, reset } from "./features/calculatorSlice";

export default function Calculator() {
    const dispatch = useDispatch();
    const value = useSelector((state) => state.calculator.value);

    const [input, setInput] = useState("");

    const parsedNumber = useMemo(() => {
        // allow empty
        if (input.trim() === "") return null;
        const n = Number(input);
        return Number.isFinite(n) ? n : null;
    }, [input]);

    const disabled = parsedNumber === null;

    return (
        <div style={{ maxWidth: 420, padding: 16, border: "1px solid #ccc", borderRadius: 8 }}>
            <h2>Redux Calculator</h2>

            <div style={{ marginBottom: 12 }}>
                <strong>Calculator value (Redux): </strong>
                <span style={{ fontSize: 20 }}>{value}</span>
            </div>

            <div style={{ display: "flex", gap: 8, marginBottom: 12 }}>
                <input
                    type="text"
                    value={input}
                    placeholder="Enter a number"
                    onChange={(e) => setInput(e.target.value)}
                    style={{ flex: 1, padding: 8 }}
                />
                <button onClick={() => setInput("")}>Clear</button>
            </div>

            {parsedNumber === null && input.trim() !== "" && (
                <div style={{ color: "crimson", marginBottom: 12 }}>
                    Please enter a valid number.
                </div>
            )}

            <div style={{ display: "flex", gap: 8 }}>
                <button disabled={disabled} onClick={() => dispatch(add(parsedNumber))}>
                    +
                </button>
                <button disabled={disabled} onClick={() => dispatch(subtract(parsedNumber))}>
                    -
                </button>
                <button disabled={disabled} onClick={() => dispatch(multiply(parsedNumber))}>
                    *
                </button>
                <button onClick={() => dispatch(reset())}>Reset</button>
            </div>
        </div>
    );
}
