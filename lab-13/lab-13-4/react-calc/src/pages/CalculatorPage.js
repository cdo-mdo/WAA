import { useState } from "react";
import { useNavigate } from "react-router-dom";

export default function CalculatorPage() {
    const [a, setA] = useState("");
    const [b, setB] = useState("");
    const [error, setError] = useState("");
    const navigate = useNavigate();

    function parseNumber(value) {
        // allow decimals and negatives; reject empty or not-a-number
        if (value.trim() === "") return null;
        const n = Number(value);
        return Number.isFinite(n) ? n : null;
    }

    function calculate(op) {
        const x = parseNumber(a);
        const y = parseNumber(b);

        if (x === null || y === null) {
            setError("Please enter valid numbers for both fields.");
            return;
        }
        setError("");

        let result;
        if (op === "add") result = x + y;
        else if (op === "sub") result = x - y;
        else if (op === "mul") result = x * y;

        // Navigate to results page and pass calculation info
        navigate("/result", {
            state: { a: x, b: y, op, result },
        });
    }

    return (
        <div style={{ padding: 24, fontFamily: "Arial" }}>
            <h2>Calculator</h2>

            <div style={{ marginBottom: 12 }}>
                <label>
                    Number A:{" "}
                    <input
                        data-testid="input-a"
                        value={a}
                        onChange={(e) => setA(e.target.value)}
                    />
                </label>
            </div>

            <div style={{ marginBottom: 12 }}>
                <label>
                    Number B:{" "}
                    <input
                        data-testid="input-b"
                        value={b}
                        onChange={(e) => setB(e.target.value)}
                    />
                </label>
            </div>

            <div style={{ display: "flex", gap: 8, marginBottom: 12 }}>
                <button data-testid="btn-add" onClick={() => calculate("add")}>
                    Add
                </button>
                <button data-testid="btn-sub" onClick={() => calculate("sub")}>
                    Subtract
                </button>
                <button data-testid="btn-mul" onClick={() => calculate("mul")}>
                    Multiply
                </button>
            </div>

            {error && (
                <div data-testid="error" style={{ color: "crimson" }}>
                    {error}
                </div>
            )}
        </div>
    );
}
