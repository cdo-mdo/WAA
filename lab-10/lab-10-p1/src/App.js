import { useState } from 'react';

const initial = {
  firstname: "",
  lastname: "",
  street: "",
  city: "",
  zip: "",
  state: "Iowa",
  email: "",
  phone: "",
  eyeColor: "Brown",
  hobbies: {
    Reading: false,
    Sports: false,
    Music: false,
    Travel: false,
  },
};

export default function App() {
  const [form, setForm] = useState(initial);

  const handleChange = (e) => {
    const {name, value} = e.target;
    setForm((prev) => ({...prev, [name]: value}));
  };

  const handleEyeColor = (e) => {
    setForm((prev) => ({...prev, eyeColor: e.target.value}));
  };

  const handleHobby = (e) => {
    const {name, checked} = e.target;
    setForm((prev) => ({
      ...prev,
      hobbies: {...prev.hobbies, [name]: checked},
    }));
  };

  const selectedHobbies = Object.keys(form.hobbies).filter((h) => form.hobbies[h]);

  return (
      <div style={{maxWidth: 900, margin: "20px auto", fontFamily: "Arial"}}>
        <h2>Lab 10 - Part 1</h2>

        <div style={{padding: 12, border: "1px solid #ccc", borderRadius: 8}}>
          <h3>Form</h3>

          <div style={{display: "grid", gridTemplateColumns: "1fr 1fr", gap: 10}}>
            <label>
              Firstname:
              <input name="firstname" value={form.firstname} onChange={handleChange}/>
            </label>

            <label>
              Lastname:
              <input name="lastname" value={form.lastname} onChange={handleChange}/>
            </label>

            <label>
              Street:
              <input name="street" value={form.street} onChange={handleChange}/>
            </label>

            <label>
              City:
              <input name="city" value={form.city} onChange={handleChange}/>
            </label>

            <label>
              Zip:
              <input name="zip" value={form.zip} onChange={handleChange}/>
            </label>

            <label>
              State:
              <select name="state" value={form.state} onChange={handleChange}>
                <option value="Iowa">Iowa</option>
                <option value="California">California</option>
              </select>
            </label>

            <label>
              Email:
              <input name="email" value={form.email} onChange={handleChange}/>
            </label>

            <label>
              Phone:
              <input name="phone" value={form.phone} onChange={handleChange}/>
            </label>
          </div>

          <div style={{marginTop: 12}}>
            <div><b>Eye color:</b></div>
            {["Brown", "Blue", "Green", "Black"].map((c) => (
                <label key={c} style={{marginRight: 12}}>
                  <input
                      type="radio"
                      name="eyeColor"
                      value={c}
                      checked={form.eyeColor === c}
                      onChange={handleEyeColor}
                  />
                  {c}
                </label>
            ))}
          </div>

          <div style={{marginTop: 12}}>
            <div><b>Hobbies:</b></div>
            {Object.keys(form.hobbies).map((h) => (
                <label key={h} style={{marginRight: 12}}>
                  <input type="checkbox" name={h} checked={form.hobbies[h]} onChange={handleHobby}/>
                  {h}
                </label>
            ))}
          </div>
        </div>

        <div style={{marginTop: 20, padding: 12, border: "1px solid #ccc", borderRadius: 8}}>
          <h3>Entered Data (Bottom)</h3>
          <pre style={{background: "#f7f7f7", padding: 12, borderRadius: 8}}>
            {JSON.stringify({...form, hobbies: selectedHobbies,}, null, 2)}
          </pre>
        </div>
      </div>
  );
}