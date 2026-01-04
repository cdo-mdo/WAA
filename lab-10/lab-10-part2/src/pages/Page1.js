import { useNavigate } from "react-router-dom";

export default function Page1({ data, setData }) {
    const nav = useNavigate();

    const handleChange = (e) => {
        const { name, value } = e.target;
        setData((prev) => ({ ...prev, [name]: value }));
    };

    return (
        <div style={{ maxWidth: 800, margin: "20px auto", fontFamily: "Arial" }}>
            <h2>Page 1</h2>

            <label>
                Firstname:
                <input name="firstname" value={data.firstname} onChange={handleChange} />
            </label>
            <br /><br />

            <label>
                Lastname:
                <input name="lastname" value={data.lastname} onChange={handleChange} />
            </label>
            <br /><br />

            <label>
                Profession:
                <select name="profession" value={data.profession} onChange={handleChange}>
                    <option value="Programmer">Programmer</option>
                    <option value="Manager">Manager</option>
                    <option value="Tester">Tester</option>
                    <option value="Architect">Architect</option>
                </select>
            </label>

            <br /><br />
            <button onClick={() => nav("/page2")}>Next</button>
        </div>
    );
}
