import React from "react";

function GameBoard({ board, onEvolveClick, onNewGameClick }) {
  return (
    <div>
      <button onClick={onEvolveClick}>Evolve</button>
      <button onClick={onNewGameClick}>New game</button>
      <table className="center">
        <tbody>
          {board.map((row, rowIndex) => (
            <tr key={rowIndex}>
              {row.map((cellValue, colIndex) => (
                <td
                  key={`${rowIndex}-${colIndex}`}
                  className={cellValue ? "alive" : "dead"}
                />
              ))}
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default GameBoard;
