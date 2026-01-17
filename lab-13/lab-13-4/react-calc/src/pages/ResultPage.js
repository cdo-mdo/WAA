import { useLocation, useNavigate } from "react-router-dom";

function opSymbol(op) {
    if (op === "add") return "+";
    if (op === "sub") return "-";
    if (op === "mul") return "×";
    return "?";
}

export default function ResultPage() {
    const location = useLocation();
    const navigate = useNavigate();

    // If user opens /result directly (no state), go back
    const data = location.state;
    if (!data) {
        return (
            <div style={{ padding: 24, fontFamily: "Arial" }}>
                <h2>Result</h2>
                <p data-testid="no-data">No calculation data. Please go back.</p>
                <button data-testid="btn-back" onClick={() => navigate("/")}>
                    Back
                </button>
            </div>
        );
    }

    const { a, b, op, result } = data;

    return (
        <div style={{ padding: 24, fontFamily: "Arial" }}>
            <h2>Result</h2>

            <div data-testid="equation" style={{ marginBottom: 8 }}>
                {a} {opSymbol(op)} {b}
            </div>

            <div data-testid="result" style={{ fontSize: 20, fontWeight: "bold" }}>
                {result}
            </div>

            <div style={{ marginTop: 16 }}>
                <button data-testid="btn-back" onClick={() => navigate("/")}>
                    Back
                </button>
            </div>
        </div>
    );
}
