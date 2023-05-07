import React, { useState } from "react";
import "./App.css";
import GameBoard from "./components/GameBoard";
import GameForm from "./components/GameForm";
import { createGame, evolveGame, deleteGame } from "./services/GameService";

function App() {
  const [game, setGame] = useState(null);
  const [error, setError] = useState(null);

  async function handleNewGameSubmit(event, size) {
    event.preventDefault();
    try {
      const data = await createGame(size);
      setGame(data);
      setError(null);
    } catch (error) {
      console.log("Error creating new game");
      setError("Failed to create new game. Please try again.");
    }
  }

  async function handleEvolveClick() {
    if (!game) return;
    try {
      const data = await evolveGame(game.gameId);
      setGame(data);
      setError(null);
    } catch (error) {
      console.log("Error evolving game");
      setError("Failed to evolve game. Please try again.");
    }
  }

  async function handleNewGameClick() {
    if (!game) return;
    try {
      await deleteGame(game.gameId);
      setGame(null);
      setError(null);
    } catch (error) {
      console.log("Error deleting game");
      setError("Failed to start a new game. Please try again.");
    }
  }

  return (
    <div className="App">
      <h1>Game of Life</h1>
      {error && <p className="error">{error}</p>}
      {game ? (
        <GameBoard
          board={game.board}
          onEvolveClick={handleEvolveClick}
          onNewGameClick={handleNewGameClick}
        />
      ) : (
        <GameForm onSubmit={handleNewGameSubmit} />
      )}
    </div>
  );
}

export default App;
