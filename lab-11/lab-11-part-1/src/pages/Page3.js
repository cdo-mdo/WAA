import React from "react";
import { useNavigate } from "react-router-dom";
import { useFormData } from "../context/FormContext";

export default function Page3() {
    const navigate = useNavigate();
    const { form, handleChange } = useFormData();

    function onNext(e) {
        e.preventDefault();
        navigate("/page4");
    }

    return (
        <div>
            <h2>Page 3 - Payment</h2>

            <div style={{ padding: 12, border: "1px solid #ddd", marginBottom: 16 }}>
                <h4>Data from Page 1 + Page 2</h4>
                <div>
                    Name: {form.firstname} {form.lastname}
                </div>
                <div>Profession: {form.profession}</div>
                <div>
                    Address: {form.street}, {form.city}, {form.state} {form.zip}
                </div>
            </div>

            <form onSubmit={onNext} style={{ display: "grid", gap: 12 }}>
                <label>
                    Creditcard number:
                    <input
                        type="text"
                        name="creditcard"
                        value={form.creditcard}
                        onChange={handleChange}
                        style={{ marginLeft: 10, width: 280 }}
                    />
                </label>

                <div>
                    Type:
                    <label style={{ marginLeft: 10 }}>
                        <input
                            type="radio"
                            name="cardType"
                            value="Visa"
                            checked={form.cardType === "Visa"}
                            onChange={handleChange}
                        />
                        Visa
                    </label>

                    <label style={{ marginLeft: 10 }}>
                        <input
                            type="radio"
                            name="cardType"
                            value="Mastercard"
                            checked={form.cardType === "Mastercard"}
                            onChange={handleChange}
                        />
                        Mastercard
                    </label>
                </div>

                <div style={{ display: "flex", gap: 10 }}>
                    <button type="button" onClick={() => navigate("/page2")}>
                        ◀ Back
                    </button>
                    <button type="submit">Next ➜ Page 4</button>
                </div>
            </form>
        </div>
    );
}
