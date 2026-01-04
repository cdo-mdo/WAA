import React, { useState } from "react";

export default function App() {
  const [form, setForm] = useState({
    firstname: "",
    lastname: "",
    street: "",
    city: "",
    zip: "",
    state: "Iowa",
    email: "",
    phone: "",
    eyeColor: "",
    hobbies: [],
  });

  function handleTextChange(e) {
    const { name, value } = e.target;
    setForm((prev) => ({ ...prev, [name]: value }));
  }

  function handleEyeColorChange(e) {
    setForm((prev) => ({ ...prev, eyeColor: e.target.value }));
  }

  function handleHobbyChange(e) {
    const { value, checked } = e.target;

    setForm((prev) => {
      if (checked) {
        // add hobby
        return { ...prev, hobbies: [...prev.hobbies, value] };
      } else {
        // remove hobby
        return { ...prev, hobbies: prev.hobbies.filter((h) => h !== value) };
      }
    });
  }

  return (
      <div style={{ padding: 20, fontFamily: "Arial" }}>
        <h2>Lab 10 - Part 1</h2>

        {/* FORM TOP */}
        <div style={{ border: "1px solid #ccc", padding: 15, borderRadius: 8 }}>
          <h3>Form</h3>

          <div style={{ marginBottom: 10 }}>
            <label>Firstname: </label>
            <input
                name="firstname"
                value={form.firstname}
                onChange={handleTextChange}
            />
          </div>

          <div style={{ marginBottom: 10 }}>
            <label>Lastname: </label>
            <input
                name="lastname"
                value={form.lastname}
                onChange={handleTextChange}
            />
          </div>

          <div style={{ marginBottom: 10 }}>
            <label>Street: </label>
            <input name="street" value={form.street} onChange={handleTextChange} />
          </div>

          <div style={{ marginBottom: 10 }}>
            <label>City: </label>
            <input name="city" value={form.city} onChange={handleTextChange} />
          </div>

          <div style={{ marginBottom: 10 }}>
            <label>Zip: </label>
            <input name="zip" value={form.zip} onChange={handleTextChange} />
          </div>

          <div style={{ marginBottom: 10 }}>
            <label>State: </label>
            <select name="state" value={form.state} onChange={handleTextChange}>
              <option value="Iowa">Iowa</option>
              <option value="California">California</option>
              <option value="Texas">Texas</option>
              <option value="New York">New York</option>
            </select>
          </div>

          <div style={{ marginBottom: 10 }}>
            <label>Email: </label>
            <input name="email" value={form.email} onChange={handleTextChange} />
          </div>

          <div style={{ marginBottom: 10 }}>
            <label>Phone: </label>
            <input name="phone" value={form.phone} onChange={handleTextChange} />
          </div>

          {/* Radio: Eye Color */}
          <div style={{ marginBottom: 10 }}>
            <label>Eye color: </label>
            <label style={{ marginLeft: 10 }}>
              <input
                  type="radio"
                  name="eyeColor"
                  value="Blue"
                  checked={form.eyeColor === "Blue"}
                  onChange={handleEyeColorChange}
              />
              Blue
            </label>
            <label style={{ marginLeft: 10 }}>
              <input
                  type="radio"
                  name="eyeColor"
                  value="Brown"
                  checked={form.eyeColor === "Brown"}
                  onChange={handleEyeColorChange}
              />
              Brown
            </label>
            <label style={{ marginLeft: 10 }}>
              <input
                  type="radio"
                  name="eyeColor"
                  value="Green"
                  checked={form.eyeColor === "Green"}
                  onChange={handleEyeColorChange}
              />
              Green
            </label>
          </div>

          {/* Checkboxes: Hobbies */}
          <div style={{ marginBottom: 10 }}>
            <label>Hobbies: </label>
            <label style={{ marginLeft: 10 }}>
              <input
                  type="checkbox"
                  value="Reading"
                  checked={form.hobbies.includes("Reading")}
                  onChange={handleHobbyChange}
              />
              Reading
            </label>

            <label style={{ marginLeft: 10 }}>
              <input
                  type="checkbox"
                  value="Tennis"
                  checked={form.hobbies.includes("Tennis")}
                  onChange={handleHobbyChange}
              />
              Tennis
            </label>

            <label style={{ marginLeft: 10 }}>
              <input
                  type="checkbox"
                  value="Music"
                  checked={form.hobbies.includes("Music")}
                  onChange={handleHobbyChange}
              />
              Music
            </label>
          </div>
        </div>

        {/* DISPLAY BOTTOM */}
        <div
            style={{
              marginTop: 20,
              border: "1px solid #ccc",
              padding: 15,
              borderRadius: 8,
              background: "#fafafa",
            }}
        >
          <h3>Entered Data (Live Preview)</h3>
          <p>
            <b>Firstname:</b> {form.firstname}
          </p>
          <p>
            <b>Lastname:</b> {form.lastname}
          </p>
          <p>
            <b>Street:</b> {form.street}
          </p>
          <p>
            <b>City:</b> {form.city}
          </p>
          <p>
            <b>Zip:</b> {form.zip}
          </p>
          <p>
            <b>State:</b> {form.state}
          </p>
          <p>
            <b>Email:</b> {form.email}
          </p>
          <p>
            <b>Phone:</b> {form.phone}
          </p>
          <p>
            <b>Eye color:</b> {form.eyeColor}
          </p>
          <p>
            <b>Hobbies:</b> {form.hobbies.join(", ")}
          </p>
        </div>
      </div>
  );
}
