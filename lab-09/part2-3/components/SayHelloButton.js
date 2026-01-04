import React from "react";

function SayHelloButton(props) {
  const handleClick = () => {
    alert(props.message);
  };

  return (
    <button onClick={handleClick}>
      {props.text}
    </button>
  );
}

export default SayHelloButton;
