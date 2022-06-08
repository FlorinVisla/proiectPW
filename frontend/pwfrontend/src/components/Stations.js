import React from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'

class Stations extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      error: null,
      isLoaded: false,
      stops: [],
    };
  }

  componentDidMount() {
    fetch('http://localhost:8080/stations', {
      method: 'GET',
      headers: {
        'access-control-allow-origin': '*',
        'Content-type': 'application/json; charset=UTF-8',
      },
    })
      .then((res) => res.json())
      .then(
        (result) => {
          this.setState({
            isLoaded: true,
            stops: result
          });
        },
        // Note: it's important to handle errors here
        // instead of a catch() block so that we don't swallow
        // exceptions from actual bugs in components.
        (error) => {
          this.setState({
            isLoaded: true,
            error,
          });
        }
      );
  }

  render() {
    const { error, isLoaded, stops } = this.state;
    if (error) {
      return <div>Error: {error.message}</div>;
    } else if (!isLoaded) {
      return <div>Loading...</div>;
    } else {
      return (
        <div>
          <div className='list-group'>
              {stops.map((item) => (
                <button
                  type='button'
                  className={"list-group-item list-group-item-action list-group-item-dark"}
                  key={item.id}
                >
                  <div className='container'>
                    <div className='row'>
                      <div className='col-sm'>{item.location}</div>
                    </div>
                  </div>
                </button>
              ))}
        </div>
        </div>
      );
    }
  }
}

export default Stations;
