import React, {
  Component
} from 'react';
import './App.css';

class App extends Component {
  constructor() {
    super();

    this.state = { loadedTrackers: [] };
  }

  componentWillMount() {
    fetch('http://localhost:8080/connection-info?ip=www.google.com')
      .then(res => res.json())
      .then(whois => {
        this.setState({ ...this.state, loadedTrackers: [whois] });
      })
  }

  render() {
    const trackerRows = this.state.loadedTrackers.map(tracker =>
      <tr>
        <td>{tracker.ip}</td>
        <td>{tracker.country}</td>
        <td>{tracker.state}</td>
      </tr>)

    return (
      <div className="App" >
        <div className="col">
          <h1 className="col-title">Map</h1>
        </div>
        <div className="col">
          <h1 className="col-title">Trackers</h1>
          <table>
            <tr>
              <th>IP</th>
              <th>Country</th>
              <th>State</th>
            </tr>
            {
              trackerRows
            }
          </table>
        </div>
      </div>
    );
  }
}

export default App;