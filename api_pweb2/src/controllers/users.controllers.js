// require('dotenv').config({ path: '.env' });
const jwt = require('jsonwebtoken');
const bcrypt = require('bcrypt');
let { users } = require('../data');

const getAllUsers = async (req, res, next) => {
    try {
        const allUsers = users;

        res.status(200).json(allUsers)
    } catch (error) {
        next(error);
    }
};

const getSingleUser = async (req, res, next) => {
    try {
        const user_id = parseInt(req.params.id);
        const index = users.findIndex(user => user.id === user_id)
        const user = users[index];

        if (!user) return res.status(404).json({
            message: 'user not found'
        });

        res.json(user);
    } catch (error) {
        next(error);
    }
};

const createUser = async (req, res, next) => {
    const { email, username, password } = req.body;

    const index = users.findIndex(user => user.email === email);

    if(index >= 0) { return res.status(409).json({message: 'email in use'}) }

    try {
        const hashedPassword = await bcrypt.hash(password, 10)
        const newId = users.at(-1).id + 1;

        const newUser = {
            id: newId,
            name: username,
            email,
            password: hashedPassword
        }
        users.push(newUser)

        const token = jwt.sign({ id: newUser.id }, 'process.env.JWT_SECRET', {
            expiresIn: 86400 //24 horas
        });

        res.status(200).json({ token });
    } catch (error) {
        next(error);
    };
};

const deleteUser = async (req, res, next) => {
    const user_id = parseInt(req.params.id);

    try {
        const index = users.findIndex(user => user.id === user_id);

        if (index >= 0) return res.status(404).json({
            message: 'user not found'
        });

        users = users.filter(user => {
            user.id !== user_id
        })

        res.sendStatus(204);
    } catch (error) {
        next(error);
    }
};

const updateUser = async (req, res, next) => {
    try {
        const user_id = parseInt(req.params.id);
        const { email, username, password } = req.body;
        const hashedPassword = await bcrypt.hash(password, 10)
        const index = users.findIndex(user => user.id === user_id)
        if (index < 0) return res.status(404).json({
            message: 'user not found'
        });
        users[index].email = email
        users[index].name = username
        users[index].password = hashedPassword

        res.status(200).json(user);
    } catch (error) {
        next(error);
    }
};

module.exports = {
    getAllUsers,
    getSingleUser,
    createUser,
    deleteUser,
    updateUser
};