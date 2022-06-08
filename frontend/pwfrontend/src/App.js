import './App.css';

import Transport from './components/Transport';

import { library } from '@fortawesome/fontawesome-svg-core'
import { fas, faGasPump } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'

import {
  BrowserRouter as Router,
  Routes,
  Route,
  Link
} from "react-router-dom";

library.add(fas, faGasPump)

function App() {
  return (
    <div className='App'>
      <Router>
            <div>
              <nav>
                <ul className="list-group list-group-horizontal-lg list-inline mx-auto justify-content-center">
                  <li className="list-group-item list-group-item-dark display-7">
                    <Link to="/">Transport</Link>
                  </li>
                  <li className="list-group-item list-group-item-dark display-7">
                    <Link to="/Routes">Manage Routes</Link>
                  </li>
                  <li className="list-group-item list-group-item-dark display-7">
                    <Link to="/Stations">Manage Stations</Link>
                  </li>
                  <li className="list-group-item list-group-item-dark display-7">
                    <Link to="/Vehicles">Manage Vehicles</Link>
                  </li>
                </ul>
              </nav>

              {/* A <Switch> looks through its children <Route>s and
                  renders the first one that matches the current URL. */}
              <Routes>
                <Route path="/" element={<Transport/>}>
                </Route>
              </Routes>
            </div>
          </Router>
    </div>
  );
}

export default App;
