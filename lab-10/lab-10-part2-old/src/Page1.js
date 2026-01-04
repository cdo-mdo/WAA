import React from "react";
import { useNavigate } from "react-router-dom";

export default function Page1({ data, setData }) {
    const navigate = useNavigate();

    function handleChange(e) {
        const { name, value } = e.target;
        setData((prev) => ({ ...prev, [name]: value }));
    }

    return (
        <div style={{ border: "1px solid #ccc", padding: 15, borderRadius: 8 }}>
            <h3>Page 1</h3>

            <div style={{ marginBottom: 10 }}>
                <label>Firstname: </label>
                <input name="firstname" value={data.firstname} onChange={handleChange} />
            </div>

            <div style={{ marginBottom: 10 }}>
                <label>Lastname: </label>
                <input name="lastname" value={data.lastname} onChange={handleChange} />
            </div>

            <div style={{ marginBottom: 10 }}>
                <label>Profession: </label>
                <select name="profession" value={data.profession} onChange={handleChange}>
                    <option value="Programmer">Programmer</option>
                    <option value="Manager">Manager</option>
                    <option value="Tester">Tester</option>
                    <option value="Architect">Architect</option>
                </select>
            </div>

            <button onClick={() => navigate("/page2")}>Next</button>
        </div>
    );
}
