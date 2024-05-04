# GamesForMe API by Carti
## Like a playlist, but for games
### Endpoints for games
- GET /games - get all games
- POST /games - create a new game
- GET /games/by/:user - get all games from a specific user
- GET /games/:id - get a game
- DELETE /games/:id - delete a game with id
- PUT /games/:id - update a game with id
### Endpoints for users
- GET /users - get all users
- POST /users - create a new user
- GET /users/:id - get a user
- DELETE /users/:id - delete a user with id
- PUT /users/:id - update a user with id
### Endpoints for auth
- POST /auth/sigin - sign in a session, return a token