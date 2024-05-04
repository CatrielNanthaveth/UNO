const { Router } = require('express');
const { getAllGames, getSingleGame, getUserGames, createGame, deleteGame, updateGame } = require('../controllers/games.controllers');

const router = Router();

router.get('/games', getAllGames);

router.get('/games/:id', getSingleGame);

router.get('/games/by/user', getUserGames);

router.post('/games', createGame);

router.delete('/games/:id', deleteGame);

router.put('/games/:id', updateGame);

module.exports = router;