import "./App.css";
import Counter from "./components/Counter";
import { useState } from "react";

function App() {
  const [count, setCount] = useState(14);

  const handleChange = (delta) => {
    setCount(count + delta);
  };

  return (
    <div className="App">
      {/* Top shared counter value */}
      <h1>{count}</h1>

      <div className="counter-grid">
        <Counter value={count} step={1} onChange={handleChange} />
        <Counter value={count} step={3} onChange={handleChange} />
        <Counter value={count} step={5} onChange={handleChange} />
        <Counter value={count} step={8} onChange={handleChange} />
      </div>
    </div>
  );
}

export default App;
