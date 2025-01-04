const express = require('express')
const app = express()

const cors = require('cors')

const connectDB = require('./db/db.js')

const dotenv = require('dotenv').config()

const cookieParser = require('cookie-parser')

const port = 8000

app.use(cors({
    origin: "*",
    credentials: true
}))

connectDB
app.use(cookieParser())
const prof_route = require('./routes/router')
app.use(express.json())

app.use('/api',prof_route)

app.listen(port,function (){
    console.log('server lancé')
})