import React from "react";
import { useNavigate } from "react-router-dom";

export default function Page2({ data, setData }) {
    const navigate = useNavigate();

    function handleChange(e) {
        const { name, value } = e.target;
        setData((prev) => ({ ...prev, [name]: value }));
    }

    return (
        <div style={{ border: "1px solid #ccc", padding: 15, borderRadius: 8 }}>
            <h3>Page 2</h3>

            {/* show entered data from page 1 */}
            <div style={{ background: "#fafafa", padding: 10, marginBottom: 15 }}>
                <p><b>Firstname:</b> {data.firstname}</p>
                <p><b>Lastname:</b> {data.lastname}</p>
                <p><b>Profession:</b> {data.profession}</p>
            </div>

            <div style={{ marginBottom: 10 }}>
                <label>Street: </label>
                <input name="street" value={data.street} onChange={handleChange} />
            </div>

            <div style={{ marginBottom: 10 }}>
                <label>City: </label>
                <input name="city" value={data.city} onChange={handleChange} />
            </div>

            <div style={{ marginBottom: 10 }}>
                <label>Zip: </label>
                <input name="zip" value={data.zip} onChange={handleChange} />
            </div>

            <div style={{ marginBottom: 10 }}>
                <label>State: </label>
                <select name="state" value={data.state} onChange={handleChange}>
                    <option value="Iowa">Iowa</option>
                    <option value="California">California</option>
                </select>
            </div>

            <button onClick={() => navigate("/page3")}>Next</button>
        </div>
    );
}
