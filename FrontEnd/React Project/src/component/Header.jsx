import {Link} from 'react-router-dom'
import '../styles/Header.css'
const Header = () => {

    return(
        <ul>
            <li id = "name">ParcelX</li>
            <li><Link to="/home">Home</Link></li>
            <li><Link to="/sendParcel">Send Parcel</Link></li>
            <li><Link to="/tracking">Track Parcel</Link></li>
            <li id= "login"><Link to ='/register'>Register</Link></li>
            <li id= "login"><Link to ='/login'>Login</Link></li>


    </ul>
    )
}

export default Header