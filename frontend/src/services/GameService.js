export const createGame = (size) => {
  const url = size
    ? `http://localhost:8080/game?size=${size}`
    : "http://localhost:8080/game";
  return fetch(url, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
  }).then((response) => response.json());
};

export const evolveGame = (gameId) => {
  return fetch(`http://localhost:8080/game/${gameId}/evolve`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
  }).then((response) => response.json());
};

export const deleteGame = (gameId) => {
  return fetch(`http://localhost:8080/game/${gameId}`, {
    method: "DELETE",
  });
};
