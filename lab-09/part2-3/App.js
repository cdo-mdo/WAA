import "./App.css";
import SayHelloButton from "./components/SayHelloButton";

function App() {
  return (
    <div className="App">
      <div className="button-grid">
        <SayHelloButton
          text="Say Hello from button 1"
          message="Hello from button 1"
        />

        <SayHelloButton
          text="Say Welcome from button 2"
          message="Welcome from button 2"
        />

        <SayHelloButton
          text="Say Hi from button 3"
          message="Hi from button 3"
        />

        <SayHelloButton
          text="Say Goodbye from button 4"
          message="Goodbye from button 4"
        />
      </div>
    </div>
  );
}

export default App;
