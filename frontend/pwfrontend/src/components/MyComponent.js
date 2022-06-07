import React from 'react';

class MyComponent extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      error: null,
      isLoaded: false,
      routes: [],
      unusedVehicles: [],
    };
  }

  componentDidMount() {
    fetch('http://localhost:8080/transport', {
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
            routes: result.transportRoutes,
            unusedVehicles: result.unusedVehicles,
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
    const { error, isLoaded, routes, unusedVehicles } = this.state;
    if (error) {
      return <div>Error: {error.message}</div>;
    } else if (!isLoaded) {
      return <div>Loading...</div>;
    } else {
      return (
        <div>
          <ul className='list-group'>
            {routes.map((item) => (
              <li
                key={item.id}
                className='list-group-item list-group-item-success'
              >
                <b>{item.id}</b>
                <br></br>
                {item.description}
                <ul className='list-group'>
                  {item.stops.map((stop) => (
                    <li
                      className='list-group-item list-group-item-warning'
                      key={stop.id}
                    >
                      {stop.location}
                    </li>
                  ))}
                </ul>
                <br></br>
                <ul className='list-group'>
                  {item.vehicles.map((vehicle) => (
                    <li
                      className='list-group-item list-group-item-dark'
                      key={vehicle.id}
                    >
                      <div className='container'>
                        <div className='row'>
                          <div className='col-sm'>
                            {vehicle.descriptionOfVehicle}
                          </div>
                          <div className='col-sm'>{vehicle.numberOfSeats}</div>
                          <div className='col-sm'>{vehicle.gasTank}</div>
                        </div>
                      </div>
                    </li>
                  ))}
                </ul>
              </li>
            ))}
          </ul>
          <br></br>
          <br></br>
          <div className='list-group'>
            {unusedVehicles.map((item) => (
              <button
                type='button'
                className='list-group-item list-group-item-action list-group-item-danger'
                key={item.id}
              >
                <div className='container'>
                  <div className='row'>
                    <div className='col-sm'>{item.descriptionOfVehicle}</div>
                    <div className='col-sm'>{item.numberOfSeats}</div>
                    <div className='col-sm'>{item.gasTank}</div>
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

export default MyComponent;
