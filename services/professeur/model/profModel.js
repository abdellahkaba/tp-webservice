const mongoose = require('mongoose')

const profSchema = mongoose.Schema({
    name:{
        type:String,
        required:true
    },
    contact:{
        type:String,
        required:true
    }
})

module.exports = mongoose.model("Prof",profSchema)