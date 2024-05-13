import {useState} from 'react'
import {useNavigate} from 'react-router-dom'

const Login = () => {

    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')
    const [message, setMessage] = useState('')

    const navigate = useNavigate()

    //const [sender, setSender] = useState([])
    

    const loginClicked = (event) => {
        console.log('login clicked')
        event.preventDefault()

        if (username === '' && password === ''){
            setMessage('Login Failed')

        } else {
            navigate('/sendParcel')
        }
    }


    return(
        <div>
            <h1>Login</h1>
            <div className='container p-3 my-3 border w-50 bg-light ml-auto col-auto'>
              <form onSubmit={loginClicked}>
                 <label>Username:</label>
                 <input required className="form-control w-50 mx-auto" type='text' name='username' value={username} onChange={event => setUsername(event.target.value)}/><br/>
                 <label>Password:</label>
                 <input required className="form-control w-50 mx-auto" type='password' name='password' value={password} onChange={event => setPassword(event.target.value)}/><br/>
                 <button type='submit' className="btn btn-primary m-1">Login</button>
                 </form>
             <h2>{message}</h2>
            </div>
            

        </div>

    )
} 
export default Login