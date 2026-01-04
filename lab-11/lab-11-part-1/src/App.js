import React from "react";
import { BrowserRouter, Routes, Route, Navigate, Link } from "react-router-dom";
import Page1 from "./pages/Page1";
import Page2 from "./pages/Page2";
import Page3 from "./pages/Page3";
import Page4 from "./pages/Page4";

export default function App() {
    return (
        <BrowserRouter>
            <div style={{ maxWidth: 700, margin: "40px auto", fontFamily: "Arial" }}>
                <header style={{ display: "flex", gap: 12, marginBottom: 24 }}>
                    <Link to="/page1">Page1</Link>
                    <Link to="/page2">Page2</Link>
                    <Link to="/page3">Page3</Link>
                    <Link to="/page4">Page4</Link>
                </header>

                <Routes>
                    <Route path="/" element={<Navigate to="/page1" replace />} />
                    <Route path="/page1" element={<Page1 />} />
                    <Route path="/page2" element={<Page2 />} />
                    <Route path="/page3" element={<Page3 />} />
                    <Route path="/page4" element={<Page4 />} />
                    <Route path="*" element={<h2>404 Not Found</h2>} />
                </Routes>
            </div>
        </BrowserRouter>
    );
}
