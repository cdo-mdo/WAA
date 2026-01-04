import React from "react";
import { useNavigate } from "react-router-dom";
import { useFormData } from "../context/FormContext";

export default function Page2() {
    const navigate = useNavigate();
    const { form, handleChange } = useFormData();

    function onNext(e) {
        e.preventDefault();
        navigate("/page3");
    }

    return (
        <div>
            <h2>Page 2 - Address</h2>

            <div style={{ padding: 12, border: "1px solid #ddd", marginBottom: 16 }}>
                <h4>Data from Page 1</h4>
                <div>Firstname: {form.firstname}</div>
                <div>Lastname: {form.lastname}</div>
                <div>Profession: {form.profession}</div>
            </div>

            <form onSubmit={onNext} style={{ display: "grid", gap: 12 }}>
                <label>
                    Street:
                    <input
                        type="text"
                        name="street"
                        value={form.street}
                        onChange={handleChange}
                        style={{ marginLeft: 10, width: 350 }}
                    />
                </label>

                <label>
                    City:
                    <input
                        type="text"
                        name="city"
                        value={form.city}
                        onChange={handleChange}
                        style={{ marginLeft: 10, width: 250 }}
                    />
                </label>

                <label>
                    Zip:
                    <input
                        type="text"
                        name="zip"
                        value={form.zip}
                        onChange={handleChange}
                        style={{ marginLeft: 10, width: 150 }}
                    />
                </label>

                <label>
                    State:
                    <select
                        name="state"
                        value={form.state}
                        onChange={handleChange}
                        style={{ marginLeft: 10, width: 260 }}
                    >
                        <option value="Iowa">Iowa</option>
                        <option value="California">California</option>
                    </select>
                </label>

                <div style={{ display: "flex", gap: 10 }}>
                    <button type="button" onClick={() => navigate("/page1")}>
                        ◀ Back
                    </button>
                    <button type="submit">Next ➜ Page 3</button>
                </div>
            </form>
        </div>
    );
}
