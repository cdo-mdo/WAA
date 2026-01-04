import { useNavigate } from "react-router-dom";

export default function Page4({ data }) {
    const nav = useNavigate();

    return (
        <div style={{ maxWidth: 800, margin: "20px auto", fontFamily: "Arial" }}>
            <h2>Page 4</h2>

            <h3>All Entered Data</h3>
            <pre style={{ background: "#f7f7f7", padding: 10, borderRadius: 8 }}>
{JSON.stringify(data, null, 2)}
      </pre>

            <button onClick={() => nav("/page3")}>Back</button>
        </div>
    );
}
