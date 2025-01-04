const Prof = require('../model/profModel')

const addProf = async (req,res) => {
    try{

        const prof = new Prof({
            name:req.body.name,
            contact:req.body.contact
        })
        const profData = await prof.save()
        res.status(200).send({
            success:true,
            msg: 'Prof Data',
            data:profData
        })
    }catch(error){
        res.status(400).send({success:false,msg:error.message})
    
    }
}

const getProf = async (req,res) => {
    try{
        const profs = await Prof.find({})
        res.status(200).send({
            success:true,
            msg: 'Liste des Professeurs',
            data: profs
        })
    }catch (error) {
        res.status(400).send({
            success:false,
            msg:error.message
        })
    } 
}

const deleteProf = async (req,res) => {
    try{
        const id = req.params.id
        const deleteProf = await Prof.deleteOne({_id:id})
        res.status(200).send({
            success: true,
            msg: 'Prof supprimer avec success',
            data: deleteProf
        })
    }catch (error) {
        res.status(400).send({
            success:false,
            msg:error.message
        })
    }
}

const updateProf = async (req,res) => {
    try{
        const id = req.body.id;
        const name = req.body.name;
        const contact = req.body.contact;

        const updateProf = await Prof.findByIdAndUpdate({_id:id}, {
            name:name,contact:contact
        })
        res.status(200).send({
            success: true,
            msg: 'Prof Modifié avec success',
            data: updateProf
            })

    }catch (error){
        res.status(400).send({
            success:false,
            msg:error.message
        })
    }
}

module.exports = {addProf,getProf,deleteProf,updateProf}