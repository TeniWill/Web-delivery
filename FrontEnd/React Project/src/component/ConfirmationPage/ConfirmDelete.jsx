import { useNavigate } from "react-router-dom";

const ConfirmDelete = () => {

    const navigate = useNavigate();

    const returnHomeClicked = (event) => {
        event.preventDefault()
        navigate('/')
    }

    return(
        <div>
            <div className='container p-3 my-3 border w-50 bg-light rounded '>
                
                <h2> Parcel Successfully Deleted</h2>
                <br/>
                <button type='submit' className="btn btn-primary m-1" onClick={returnHomeClicked}>Return Home</button> 



            </div>

        </div>
    )
}

export default ConfirmDelete