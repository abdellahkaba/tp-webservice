const express = require('express');
const cors = require('cors');
const cookieParser = require('cookie-parser');
const dotenv = require('dotenv').config();

const connectDB = require('./db/db.js');
const prof_route = require('./routes/router');
const eurekaClient = require('./eurekaClient'); // Importer le client Eureka

const app = express();
const port = process.env.PORT || 8000;

app.use(cors({
    origin: '*',
    credentials: true,
}));

connectDB;
app.use(cookieParser());
app.use(express.json());
app.use('/api', prof_route);

// Endpoint de statut pour Eureka
app.get('/status', (req, res) => {
    res.json({ status: 'UP' });
});

// Démarrer le serveur
app.listen(port, () => {
    console.log(`Microservice en écoute sur le port ${port}`);
});

eurekaClient;
