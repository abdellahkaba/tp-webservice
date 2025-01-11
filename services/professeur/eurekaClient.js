const { Eureka } = require('eureka-js-client');

const eurekaClient = new Eureka({
    instance: {
        app: 'professeur-service', // Nom de l'application (doit être unique)
        instanceId: `professeur-service:${process.env.PORT || 8000}`, // Identifiant unique de l'instance
        hostName: 'localhost', // Nom d'hôte
        ipAddr: '127.0.0.1', // Adresse IP de l'instance
        statusPageUrl: `http://localhost:${process.env.PORT || 8000}/status`, // URL pour vérifier le statut
        port: {
            $: process.env.PORT || 8000, // Port utilisé par votre microservice
            '@enabled': true,
        },
        vipAddress: 'professeur-service',
        dataCenterInfo: {
            '@class': 'com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo',
            name: 'MyOwn',
        },
    },
    eureka: {
        host: 'localhost', // Hôte du serveur Eureka
        port: 8761, // Port du serveur Eureka
        servicePath: '/eureka/apps/', // Chemin utilisé pour l'enregistrement
        fetchRegistry: true, // Active la récupération des services enregistrés
        registerWithEureka: true, // Permet l'enregistrement de votre service
    },
});

// Démarrer le client Eureka
eurekaClient.start((error) => {
    if (error) {
        console.error('Erreur lors de l’enregistrement auprès d’Eureka:', error);
    } else {
        console.log('Microservice enregistré avec succès auprès d’Eureka.');
    }
});

module.exports = eurekaClient;
