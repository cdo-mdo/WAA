import React from "react";

function SayHelloButton() {
  const handleClick = () => {
    alert("Hello World");
  };

  return (
    <button onClick={handleClick}>
      Say Hello
    </button>
  );
}

export default SayHelloButton;
