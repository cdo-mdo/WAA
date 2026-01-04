import React from "react";

function Counter(props) {
  return (
    <div className="counter">
      <h1>{props.value}</h1>

      <div className="buttons">
        <button onClick={() => props.onChange(props.step)}>
          + {props.step}
        </button>

        <button onClick={() => props.onChange(-props.step)}>
          - {props.step}
        </button>
      </div>
    </div>
  );
}

export default Counter;
  