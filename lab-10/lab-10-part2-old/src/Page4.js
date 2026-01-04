import React from "react";

export default function Page4({ data }) {
    return (
        <div style={{ border: "1px solid #ccc", padding: 15, borderRadius: 8 }}>
            <h3>Page 4 - Summary</h3>

            <div style={{ background: "#fafafa", padding: 10 }}>
                <p><b>Firstname:</b> {data.firstname}</p>
                <p><b>Lastname:</b> {data.lastname}</p>
                <p><b>Profession:</b> {data.profession}</p>

                <p><b>Street:</b> {data.street}</p>
                <p><b>City:</b> {data.city}</p>
                <p><b>Zip:</b> {data.zip}</p>
                <p><b>State:</b> {data.state}</p>

                <p><b>Credit card number:</b> {data.creditcard}</p>
                <p><b>Card type:</b> {data.cardType}</p>
            </div>
        </div>
    );
}
