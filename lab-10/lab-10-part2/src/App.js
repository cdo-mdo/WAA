import { useState } from "react";
import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";

import Page1 from "./pages/Page1";
import Page2 from "./pages/Page2";
import Page3 from "./pages/Page3";
import Page4 from "./pages/Page4";

const initial = {
  firstname: "",
  lastname: "",
  profession: "Programmer",
  street: "",
  city: "",
  zip: "",
  state: "Iowa",
  ccNumber: "",
  ccType: "Visa",
};

export default function App() {
  const [data, setData] = useState(initial);

  return (
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Navigate to="/page1" replace />} />
          <Route path="/page1" element={<Page1 data={data} setData={setData} />} />
          <Route path="/page2" element={<Page2 data={data} setData={setData} />} />
          <Route path="/page3" element={<Page3 data={data} setData={setData} />} />
          <Route path="/page4" element={<Page4 data={data} />} />
        </Routes>
      </BrowserRouter>
  );
}
