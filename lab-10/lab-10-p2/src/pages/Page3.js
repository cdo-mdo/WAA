import { useNavigate } from 'react-router-dom';

export default function Page2({ data, setData}) {
    const navigate = useNavigate();

    const handleChange = (e) => {
        const { name, value } = e.target;
        setData((prev) => ({ ...prev, [name]: value }));
    }

    return (
        <div style={{ maxWidth: 800, margin: "20px auto", fontFamily: "Arial" }}>
            <h2>Page 3</h2>

            <h3>Data from Page 1 & 2</h3>
            <pre style={{ background: "#f7f7f7", padding: 10, borderRadius: 8 }}>
{JSON.stringify(
    {
        firstname: data.firstname,
        lastname: data.lastname,
        profession: data.profession,
        street: data.street,
        city: data.city,
        zip: data.zip,
        state: data.state,
    },
    null,
    2
)}
      </pre>

            <label>
                Credit card number:
                <input name="ccNumber" value={data.ccNumber} onChange={handleChange} />
            </label>

            <div style={{ marginTop: 12 }}>
                <b>Type:</b>{" "}
                {["Visa", "Mastercard"].map((t) => (
                    <label key={t} style={{ marginRight: 12 }}>
                        <input
                            type="radio"
                            name="ccType"
                            value={t}
                            checked={data.ccType === t}
                            onChange={handleChange}
                        />
                        {t}
                    </label>
                ))}
            </div>

            <br />
            <button onClick={() => navigate("/page2")}>Back</button>{" "}
            <button onClick={() => navigate("/page4")}>Next</button>
        </div>
    );
}