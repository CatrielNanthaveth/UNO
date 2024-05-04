const jwt = require('jsonwebtoken');
let { games } = require('../data');

const getAllGames = async (req, res, next) => {
    try {
        const allGames = games;
        res.json(allGames);
    } catch (error) {
        next(error);
    }
};

const getUserGames = async (req, res, next) => {
    try {
        const token = req.headers['authorization'];
        if (!token) {
            return res.status(401).json({ message: 'Token not provided' });
        }

        let user_id;

        try {
            const decoded = jwt.verify(token, 'process.env.JWT_SECRET');
            user_id = decoded.id;
        } catch (error) {
            console.error('Error al decodificar el token:', error);
            return res.status(401).json({ message: 'Invalid token' });
        } 

        const userGames = games.filter(game => game.userId === user_id)
        res.json(userGames);
    } catch (error) {
        next(error);
    }
};

const getSingleGame = async (req, res, next) => {
    try {
        const game_id = parseInt(req.params.id);
        const index = games.findIndex(game => game.id === game_id)
        const game = games[index]

        if (!game) return res.status(404).json({
            message: 'Game not found'
        });
        res.json(game);
    } catch (error) {
        next(error);
    }
};

const createGame = async (req, res, next) => {
    try {
        const { title, description, status, category } = req.body;
        const token = req.headers['authorization'];
        if (!token) {
            return res.status(401).json({ message: 'Token not provided' });
        }

        let user_id;

        try {
            const decoded = jwt.verify(token, 'process.env.JWT_SECRET');
            user_id = decoded.id;
        } catch (error) {
            console.error('Error al decodificar el token:', error);
            return res.status(401).json({ message: 'Invalid token' });
        } 
        
        const newId = games.at(-1).id + 1;

        const newGame ={
            id: newId,
            title,
            category,
            description,
            status,
            userId: user_id
        }
        games.push(newGame)
        res.json(newGame);
    } catch (error) {
        next(error);
    };
};

const deleteGame = async (req, res, next) => {
    try {
        const game_id = parseInt(req.params.id);
        const index = games.findIndex(game => game.id === game_id)
        if (index < 0) return res.status(404).json({
            message: 'Game not found'
        });
        games = games.filter(game => game.id !== game_id);

        res.sendStatus(204);
    } catch (error) {
        next(error);
    }
};

const updateGame = async (req, res, next) => {
    try {
        const game_id = parseInt(req.params.id);
        const { title, description, category, status, user_id } = req.body;
        const index = games.findIndex(game => game.id === game_id)

        if (index < 0) return res.status(404).json({
            message: 'Game not found'
        });

        if(title) games[index].title = title;
        if(description) games[index].description = description;
        if(category) games[index].category = category;
        if(status) games[index].status = status;
        if(user_id) games[index].userId = user_id;

        res.json(games[index]);
    } catch (error) {
        next(error);
    }
};

module.exports = {
    getAllGames,
    getUserGames,
    getSingleGame,
    createGame,
    deleteGame,
    updateGame
};