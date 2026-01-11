import React from 'react';

function SayHelloButton(props) {
    const handleClick = () => {
        alert("Hello World");
    };

    return (
        <button onclick={handleClick}>
            Say Hello
        </button>
    )
}
export default SayHelloButton;