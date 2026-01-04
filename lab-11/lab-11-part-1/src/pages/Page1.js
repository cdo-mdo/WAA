import React from "react";
import { useNavigate } from "react-router-dom";
import { useFormData } from "../context/FormContext";

export default function Page1() {
    const navigate = useNavigate();
    const { form, handleChange } = useFormData();

    function onNext(e) {
        e.preventDefault();
        navigate("/page2");
    }

    return (
        <div>
            <h2>Page 1 - Basic Info</h2>

            <form onSubmit={onNext} style={{ display: "grid", gap: 12 }}>
                <label>
                    Firstname:
                    <input
                        type="text"
                        name="firstname"
                        value={form.firstname}
                        onChange={handleChange}
                        style={{ marginLeft: 10, width: 250 }}
                    />
                </label>

                <label>
                    Lastname:
                    <input
                        type="text"
                        name="lastname"
                        value={form.lastname}
                        onChange={handleChange}
                        style={{ marginLeft: 10, width: 250 }}
                    />
                </label>

                <label>
                    Profession:
                    <select
                        name="profession"
                        value={form.profession}
                        onChange={handleChange}
                        style={{ marginLeft: 10, width: 260 }}
                    >
                        <option value="Programmer">Programmer</option>
                        <option value="Manager">Manager</option>
                        <option value="Tester">Tester</option>
                        <option value="Architect">Architect</option>
                    </select>
                </label>

                <button type="submit">Next ➜ Page 2</button>
            </form>
        </div>
    );
}
