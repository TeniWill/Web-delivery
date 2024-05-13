import { useState } from "react"
import { useNavigate } from "react-router-dom"

const Tracking = () => {

    const[trackingNumber, setTrackingNumber] = useState('')

    const[invalidTrackingNumber, setInvalidTrackingNumber] = useState(false)

    const navigate = useNavigate()

    const trackItemClicked = (event) => {
        event.preventDefault()
        if (trackingNumber === "") {
            setInvalidTrackingNumber(true)
        } else {
            navigate('/parcel/' + trackingNumber)
        }
        
        
    }

    return(
       <div>
       
        <h1> Track your package</h1>
        <div className='container p-3 my-3 border w-50 bg-light ml-auto col-auto'>
            <form onSubmit={trackItemClicked}>
                <div className="form-group">
                    <label> Tracking Number: </label> 
                    <input required type='text' className="form-control w-50 mx-auto" name='trackingNumber' value={trackingNumber} onChange={event => setTrackingNumber(event.target.value)}/>
                    {invalidTrackingNumber && <small className="form-text text-muted">Tracking number field empty</small> }
                    <br/>
                    <button type='submit' className="btn btn-primary m-1" >Track package</button>
                </div>

            </form>
        </div>
       </div>
    )
}

export default Tracking