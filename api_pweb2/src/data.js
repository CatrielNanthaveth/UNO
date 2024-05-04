let users = [
    {
        id: 1,
        name: 'admin',
        password: 'panam',
        email: 'admin@pweb.com'
    }
];
let games = [
    {
        id: 1,
        title: 'Grand Theft Auto V',
        description: 'Le tengo muchas ganas, pronto quiero quiero jugarlo',
        status: 'waiting for play',
        userId: 2,
        category: 'Adults'
    }
];

module.exports = {
    games, 
    users
}