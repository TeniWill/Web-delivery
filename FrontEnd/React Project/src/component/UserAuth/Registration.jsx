import { useState } from "react"
import { useNavigate } from "react-router-dom"
import axios from "axios"

const Registration = () => {

    const [firstName, setFirstName] = useState('')
    const [lastName, setLastName] = useState('')
    const [address, setAddress] = useState('')
    const [emailAddress, setEmailAddress] = useState('')
    const [password, setPassword] = useState('')

    const [invalidEmail, setInvalidEmail] = useState(false)

    const navigate = useNavigate();
    const api = "http://localhost:8080/api/v1/sender"

    const registerNewUser = (event) => {
        event.preventDefault()
        axios.post(api, 
            {
                firstName : firstName,
                lastName : lastName,
                address : address,
                emailAddress : emailAddress,
                password : password
        })
        .then(response => {navigate('/login')})
        .catch(error => {setInvalidEmail(true)})

    }



    return (
        <div>
            <h1>Register</h1>
            <div className='container p-3 my-3 border w-50 bg-light ml-auto col-auto'>
                <form onSubmit={registerNewUser}>
                    <label>First Name: </label>
                    <input required className="form-control w-50 mx-auto" type='text' name='firstName' value={firstName} onChange={event => setFirstName(event.target.value)}/><br/>

                    <label>Last Name: </label>
                    <input required className="form-control w-50 mx-auto" type='text' name='lastName' value={lastName} onChange={event => setLastName(event.target.value)}/><br/>

                    <label>Address: </label>
                    <input required className="form-control w-50 mx-auto" type='text' name='address' value={address} onChange={event => setAddress(event.target.value)}/><br/>

                    <label>Email: </label>
                    <input required className="form-control w-50 mx-auto" type='text' name='email' value={emailAddress} onChange={event => setEmailAddress(event.target.value)}/><br/>
                    {invalidEmail && <small> Email already in use, please use another </small>}

                    <label>Password: </label>
                    <input required className="form-control w-50 mx-auto" type='password' name='password' value={password} onChange={event => setPassword(event.target.value)}/><br/>

                    <button type='submit' className="btn btn-primary m-1">Register</button>

                </form>
            </div>

        </div>
    )
}

export default Registration