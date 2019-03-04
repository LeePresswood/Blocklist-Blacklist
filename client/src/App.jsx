import React, {
  Component
} from 'react';
import { Chart } from "react-google-charts";
import './App.css';

class App extends Component {
  constructor() {
    super();

    this.state = {
      lastLoadedPageIndex: 1,
      loadedIps: [],
      countryCounts: {}
    };
  }

  componentWillMount() {
    //Map each IP address to a fetch call; chain the fetches to load in the background.
    let fetches = [];
    let index = 1;
    while (index < 50000) {
      fetches.push(
        `http://localhost:8080/ips?start=${index}&size=10000`
      );

      index += 10000;
    }

    fetches.reduce((acc, next) => {
      return acc
        .then(data => data ? data.json() : Promise.resolve(null))
        .then(jsonData => {
          if (jsonData && Array.isArray(jsonData)) {
            jsonData = jsonData.filter(j => j && j.id && j.network && j.country && j.country.country_name && j.country.continent_name);

            let countryCountCopy = this.state.countryCounts;
            jsonData.forEach(j => {
              countryCountCopy[j.country.country_name] = (countryCountCopy[j.country.country_name] || 0) + 1;
            });

            this.setState({
              ...this.state,
              loadedIps: this.state.loadedIps.concat(jsonData),
              countryCounts: countryCountCopy
            });
          }

          return fetch(next);
        });
    }, Promise.resolve());
  }

  render() {
    const trackerRows = Object.entries(this.state.countryCounts)
      .sort((a, b) => b[1] - a[1])
      .slice(0, 20)
      .map(([key, value]) => {
        return (<tr key={key}>
          <td> {
            key
          } </td>
          <td> {
            value
          } </td>
        </tr>);
      });

    return (
      <div className="App">
        <div className="col-large">
          <h1 className="col-title">Map</h1>
          <Chart
            chartType="GeoChart"
            data={[['Country', 'Count']].concat(Object.entries(this.state.countryCounts))}
            mapsApiKey="AIzaSyDhoSAomGHRVsBXNW_QbljyTKoOYYcAcng"
            width={"100%"}
            height={"90vh"}
            legendToggle
          />
        </div>
        <div className="col-small">
          <h1 className="col-title">Trackers</h1>
          <table>
            <thead>
              <tr>
                <th>Country</th>
                <th>Count</th>
              </tr>
            </thead>
            <tbody>
              {trackerRows}
            </tbody>
          </table>
        </div>
      </div>
    );
  }
}

export default App;