import "./App.css";
import Counter from "./components/Counter";

function App() {
  return (
    <div className="App">
      <div className="counter-grid">
        <Counter initialValue={7} step={1} />
        <Counter initialValue={-15} step={3} />
        <Counter initialValue={10} step={5} />
        <Counter initialValue={48} step={8} />
      </div>
    </div>
  );
}

export default App;
