import React, { useState } from "react";

function GameForm({ onSubmit }) {
  const [size, setSize] = useState("");

  const handleSubmit = (event) => {
    onSubmit(event, size);
  };

  return (
    <form onSubmit={handleSubmit}>
      <label>
        Board size:
        <input
          type="number"
          max="20"
          min="1"
          value={size}
          onChange={(e) => setSize(e.target.value)}
        />
      </label>
      <button type="submit">Start</button>
    </form>
  );
}

export default GameForm;
