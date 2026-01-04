import { useNavigate } from "react-router-dom";

export default function Page2({ data, setData }) {
    const nav = useNavigate();

    const handleChange = (e) => {
        const { name, value } = e.target;
        setData((prev) => ({ ...prev, [name]: value }));
    };

    return (
        <div style={{ maxWidth: 800, margin: "20px auto", fontFamily: "Arial" }}>
            <h2>Page 2</h2>

            <h3>Data from Page 1</h3>
            <pre style={{ background: "#f7f7f7", padding: 10, borderRadius: 8 }}>
{JSON.stringify({ firstname: data.firstname, lastname: data.lastname, profession: data.profession }, null, 2)}
      </pre>

            <label>
                Street:
                <input name="street" value={data.street} onChange={handleChange} />
            </label>
            <br /><br />

            <label>
                City:
                <input name="city" value={data.city} onChange={handleChange} />
            </label>
            <br /><br />

            <label>
                Zip:
                <input name="zip" value={data.zip} onChange={handleChange} />
            </label>
            <br /><br />

            <label>
                State:
                <select name="state" value={data.state} onChange={handleChange}>
                    <option value="Iowa">Iowa</option>
                    <option value="California">California</option>
                </select>
            </label>

            <br /><br />
            <button onClick={() => nav("/page1")}>Back</button>{" "}
            <button onClick={() => nav("/page3")}>Next</button>
        </div>
    );
}
