import axios from 'axios'
import { useEffect, useState } from "react"
import { useParams } from "react-router-dom"
import { useNavigate } from 'react-router-dom'


const Parcel = () => {
    
    const params = useParams()

    const navigate = useNavigate()
    const api = 'http://localhost:8080/api/v1/parcel/' + params.trackingNumber
    

    const [parcels, setParcels] = useState([])


    const deleteApi = 'http://localhost:8080/api/v1/parcel/' + parcels.id

    const loadParcel = () => {
        axios.get(api)
        .then(response => {
            setParcels(response.data)
        })
        .catch(error => console.log('unable to find parcel'))
    }

    const deleteParcel = (event) => {
        event.preventDefault()
        console.log("Delete Attempt")
        axios.delete(deleteApi)
            .then(response => {navigate('/deleted')})
            .catch(error => (console.log('error')))
            
    }

    useEffect(() =>{

       
        loadParcel()
        // eslint-disable-next-line
    }, []);

    const updateParcelClicked = (event) => {
        event.preventDefault()
        navigate('/update/' + [params.trackingNumber])
    }

    return (

        <div>
            <br/>
            <br/>
            <h1>Parcel Details for  {parcels.firstName} {parcels.lastName}</h1>
            <p> Parcel Address: {parcels.address} </p>
            <p> Current Status: {parcels.currentStatus} </p>
            
            <button onClick={updateParcelClicked} type='submit' className="btn btn-primary m-1" >Update Parcel</button> 
            <button type ='button'onClick={deleteParcel} className="btn btn-primary m-1">Delete Package</button>
    </div>

    )
}

export default Parcel