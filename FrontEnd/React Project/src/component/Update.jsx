import { useNavigate } from 'react-router-dom'
import { useState } from 'react'
import axios from 'axios'
import { useParams } from 'react-router-dom'
import { useEffect } from 'react'

const Update = () => {

    const [address, setAddress] = useState('')
    const [currentStatus, setCurrentStatus] = useState('')

    const params = useParams()
    const navigate = useNavigate();

    const api = 'http://localhost:8080/api/v1/parcel'
    const api2 = 'http://localhost:8080/api/v1/parcel/' + params.trackingNumber

    const [parcels, setParcels] = useState([])


    const loadParcel = () => {
        axios.get(api2)
            .then(response => {setParcels(response.data)})
            .catch(error => console.log('unable to find parcel'))}

    useEffect(() =>{
        loadParcel()
        // eslint-disable-next-line   
        },[]);

        const UpdateNewParcel = (event) => {
            event.preventDefault()
            axios.put(api,
                {   
                    id: parcels.id,
                    address: address,
                    currentStatus: currentStatus,
                    trackingNumber: params.trackingNumber,
                    deliveryDate: parcels.deliveryDate,
                    sendDate: parcels.sendDate
                })
                    .then(response => {navigate('/confirmation')})
                    .catch(error => (console.log('error')))
                }

                return (
                     <div>
                        <br/>
                        <div className='container p-3 my-3 border w-50 bg-light'>
                            <form onSubmit={UpdateNewParcel}>
                                <div className="form-group">
                                    <label htmlFor="parcel"><b>Address</b></label>
                                    <input type="text" className="form-control w-50 mx-auto" placeholder="Enter Address" value={address} onChange={event => setAddress(event.target.value)}/>
                                    <br/>
                                    <label htmlFor="parcel"><b>Status</b></label>
                                    <select name="parcel" id="parcels" value={currentStatus} onChange={event => setCurrentStatus(event.target.value)}>
                                        <option value="In Storage">In Storage</option>
                                        <option value="Shipped">Shipped</option>
                                        <option value="Arrived">Arrived</option>
                                        </select>
                                        </div>
                                        <button type='submit' className="btn btn-primary m-1" >Send Parcel</button>
                                        </form>
                                        </div>
                                        </div>
                                        )
        
                
}

export default Update