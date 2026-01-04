import React, { useState } from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";

import Page1 from "./Page1";
import Page2 from "./Page2";
import Page3 from "./Page3";
import Page4 from "./Page4";

export default function App() {
  const [data, setData] = useState({
    firstname: "",
    lastname: "",
    profession: "Programmer",
    street: "",
    city: "",
    zip: "",
    state: "Iowa",
    creditcard: "",
    cardType: "",
  });

  return (
      <BrowserRouter>
        <div style={{ padding: 20, fontFamily: "Arial" }}>
          <h2>Lab 10 - Part 2</h2>

          <Routes>
            <Route path="/" element={<Page1 data={data} setData={setData} />} />
            <Route path="/page2" element={<Page2 data={data} setData={setData} />} />
            <Route path="/page3" element={<Page3 data={data} setData={setData} />} />
            <Route path="/page4" element={<Page4 data={data} />} />
          </Routes>
        </div>
      </BrowserRouter>
  );
}
