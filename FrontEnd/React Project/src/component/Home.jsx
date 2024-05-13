import '../styles/Home.css'
import laptop from '../styles/laptop.jpg'
import save from '../styles/save.jpg'
import stuff from '../styles/stuff.jpg'
import {Link} from 'react-router-dom'

const Home = () => {

    return(
        <div>
          <div>
            <h1>Welcome!</h1>
          </div>

          <div class="background">
           <div>
             <h1 class="banner-title"> ParcelX</h1>
             <h1 class="banner-text">Reliable service for delivery </h1>
           </div>

           <div>
              <div class = "navigations">
              <Link to="/sendParcel" style={{ color: 'inherit', textDecoration: 'inherit'}}>
                    <div>
                      <h5>Send</h5>
                      <p>Send a parcel</p>
                    </div>
              </Link>     
              </div><br/>
              <div class = "navigations">
              <Link to="/tracking" style={{ color: 'inherit', textDecoration: 'inherit'}}> 
                    <div>
                      <h5>Track</h5>
                      <p>Track a parcel</p>
                    </div>
               </Link>     
              </div>
           </div>

          </div>

          <div class="card-deck">
           <div class="card">
           <img class="card-img" src = {laptop} alt="laptop" />
            <div class="card-body">
              <h5 class="card-title">How</h5>
               <p class="card-text">How to Send parcel</p>
            </div>
            </div>

            <div class="card">
            <img class="card-img" src = {save} alt="save" />
              <div class="card-body">
                 <h5 class="card-title">Save</h5>
                 <p class="card-text">Save money on deliverys</p>
              </div>
            </div>

           <div class="card">
           <img class="card-img" src = {stuff} alt="stuff" />
               <div class="card-body">
                 <h5 class="card-title">Parcel</h5>
                 <p class="card-text">Keep track of parcel content</p>
               </div>
            </div>
           </div>

         
        </div>

      


    )



} 
export default Home