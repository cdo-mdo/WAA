import "./App.css";
import SayHelloButton from "./components/SayHelloButton";

function App() {
  return (
    <div className="App">
      <div className="button-grid">
        <SayHelloButton />
        <SayHelloButton />
        <SayHelloButton />
        <SayHelloButton />
      </div>
    </div>
  );
}

export default App;
