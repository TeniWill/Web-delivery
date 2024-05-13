import { useNavigate, useParams,  } from 'react-router-dom'
import { useState } from 'react'
import axios from 'axios'


const SendParcel = () => {

    const params = useParams()

    const [firstName, setFirstName] = useState('')
    const [lastName, setLastName] = useState('')
    const [address, setAddress] = useState('')


    const navigate = useNavigate();

    const api = 'http://localhost:8080/api/v1/parcel'


    const sendNewParcel = (event) => {
        event.preventDefault()
        axios.post(api,
            {
                firstName : firstName,
                lastName : lastName,
                address: address
            })
            .then(response => {navigate('/confirmation/' + params.id )})
            .catch(error => (console.log('error')))
    
    }


    return (
        <div>
            <br/>

       
            <div className='container p-3 my-3 border w-50 bg-light rounded '>
            
            <h1> Enter Parcel Information Below:</h1>    
            <br/>
            
            <form onSubmit={sendNewParcel}>
                <div className="form-group">
                    
                    <label htmlFor="parcel"><b>First Name</b></label>
                    <input required type="text" className="form-control w-50 mx-auto" placeholder="Enter First Name" value={firstName} onChange={event => setFirstName(event.target.value)}/>
                    
                    <label htmlFor="parcel"><b>Last Name</b></label>
                    <input required type="text" className="form-control w-50 mx-auto" placeholder="Enter Last Name" value={lastName} onChange={event => setLastName(event.target.value)}/>
                    
                    <label htmlFor="parcel"><b>Address</b></label>
                    <input required type="text" className="form-control w-50 mx-auto" placeholder="Enter Address" value={address} onChange={event => setAddress(event.target.value)}/>
                    
                    
                </div>
               
                <button type='submit' className="btn btn-primary m-1" >Send Parcel</button> 

            </form>
            </div>





        </div>
    )
}

export default SendParcel