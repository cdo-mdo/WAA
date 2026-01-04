import React, { useState } from "react";

function Counter(props) {
  const [count, setCount] = useState(props.initialValue);

  return (
    <div className="counter">
      <h1>{count}</h1>

      <div className="buttons">
        <button onClick={() => setCount(count + props.step)}>
          + {props.step}
        </button>

        <button onClick={() => setCount(count - props.step)}>
          - {props.step}
        </button>
      </div>
    </div>
  );
}

export default Counter;
