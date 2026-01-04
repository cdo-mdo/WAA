import React from "react";
import { useNavigate } from "react-router-dom";
import { useFormData } from "../context/FormContext";

export default function Page4() {
    const navigate = useNavigate();
    const { form, reset } = useFormData();

    return (
        <div>
            <h2>Page 4 - Summary</h2>

            <div style={{ padding: 12, border: "1px solid #ddd", marginBottom: 16 }}>
                <h4>All Entered Data</h4>

                <p><b>Firstname:</b> {form.firstname}</p>
                <p><b>Lastname:</b> {form.lastname}</p>
                <p><b>Profession:</b> {form.profession}</p>

                <p><b>Street:</b> {form.street}</p>
                <p><b>City:</b> {form.city}</p>
                <p><b>Zip:</b> {form.zip}</p>
                <p><b>State:</b> {form.state}</p>

                <p><b>Creditcard:</b> {form.creditcard}</p>
                <p><b>Card Type:</b> {form.cardType}</p>
            </div>

            <div style={{ display: "flex", gap: 10 }}>
                <button onClick={() => navigate("/page3")}>◀ Back</button>
                <button
                    onClick={() => {
                        reset();
                        navigate("/page1");
                    }}
                >
                    Reset & Go to Page 1
                </button>
            </div>
        </div>
    );
}
