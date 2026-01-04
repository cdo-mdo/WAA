import React from 'react';

function SayHelloButton(props) {
    const handleClick = () => {
        alert('Hello Button!');
    }
    return (
        <button onClick={handleClick}>
            Say Hello
        </button>
    )
}
export default SayHelloButton;