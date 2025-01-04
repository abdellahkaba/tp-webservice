const express = require('express')
const prof_route = express.Router()

const bodyParser = require('body-parser')

prof_route.use(bodyParser.urlencoded({
    extended:true
}))



const profController = require("../controller/profController")

prof_route.post('/add-prof', profController.addProf)
prof_route.get('/get-prof', profController.getProf)
prof_route.delete('/delete-prof/:id', profController.deleteProf)
prof_route.post('/update-prof', profController.updateProf)

module.exports = prof_route