import React, { useState } from "react";
import "./App.css";

function App() {
  const [a, setA] = useState("");
  const [b, setB] = useState("");
  const [result, setResult] = useState("");

  const parse = (v) => {
    // Allow decimals; treat empty as 0
    const n = Number(v);
    return Number.isFinite(n) ? n : 0;
  };

  const onAdd = () => setResult(String(parse(a) + parse(b)));
  const onSubtract = () => setResult(String(parse(a) - parse(b)));
  const onMultiply = () => setResult(String(parse(a) * parse(b)));
  const onClear = () => {
    setA("");
    setB("");
    setResult("");
  };

  return (
      <div className="container">
        <h1>Calculator</h1>

        <div className="row">
          <label>Number A</label>
          <input
              id="input-a"
              type="number"
              value={a}
              onChange={(e) => setA(e.target.value)}
              placeholder="Enter A"
          />
        </div>

        <div className="row">
          <label>Number B</label>
          <input
              id="input-b"
              type="number"
              value={b}
              onChange={(e) => setB(e.target.value)}
              placeholder="Enter B"
          />
        </div>

        <div className="buttons">
          <button id="btn-add" onClick={onAdd}>Add</button>
          <button id="btn-sub" onClick={onSubtract}>Subtract</button>
          <button id="btn-mul" onClick={onMultiply}>Multiply</button>
          <button id="btn-clear" onClick={onClear}>Clear</button>
        </div>

        <div className="result">
          <span>Result: </span>
          <strong id="result">{result}</strong>
        </div>
      </div>
  );
}

export default App;
