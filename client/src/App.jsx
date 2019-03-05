import React, {
  Component
} from 'react';
import { Chart } from "react-google-charts";
import './App.css';

class App extends Component {
  constructor() {
    super();

    this.state = {
      byCountry: true,
      sortDescending: true,
      loadedIps: [],
      countryCounts: {},
      continentCounts: {}
    };

    this.setCountry = this.setCountry.bind(this);
    this.setContinent = this.setContinent.bind(this);
    this.setAscending = this.setAscending.bind(this);
    this.setDescending = this.setDescending.bind(this);
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
            let continentCountCopy = this.state.continentCounts;
            jsonData.forEach(j => {
              countryCountCopy[j.country.country_name] = (countryCountCopy[j.country.country_name] || 0) + 1;
              continentCountCopy[j.country.continent_name] = (continentCountCopy[j.country.continent_name] || 0) + 1;
            });

            this.setState({
              ...this.state,
              loadedIps: this.state.loadedIps.concat(jsonData),
              countryCounts: countryCountCopy,
              continentCounts: continentCountCopy,
            });
          }

          return fetch(next);
        });
    }, Promise.resolve());
  }

  setCountry() {
    this.setState({
      ...this.state,
      byCountry: true
    });
  }

  setContinent() {
    this.setState({
      ...this.state,
      byCountry: false
    });
  }

  setAscending() {
    this.setState({
      ...this.state,
      sortDescending: false
    });
  }

  setDescending() {
    this.setState({
      ...this.state,
      sortDescending: true
    });
  }

  sortOrder(a, b) {
    return this.state.sortDescending ?
      b[1] - a[1] :
      a[1] - b[1];
  }

  render() {
    const dataToDisplay = this.state.byCountry ?
      Object.entries(this.state.countryCounts) :
      Object.entries(this.state.continentCounts);

    const dataHeaders = this.state.byCountry ?
      ['Country', 'Count'] :
      ['Continent', 'Count'];

    const data = [dataHeaders].concat(dataToDisplay);

    const trackerRows = dataToDisplay
      .sort((a, b) => this.sortOrder(a, b))
      .slice(0, 15)
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
            data={data}
            mapsApiKey="AIzaSyDhoSAomGHRVsBXNW_QbljyTKoOYYcAcng"
            width={"100%"}
            height={"90vh"}
            legendToggle
          />
        </div>
        <div className="col-small">
          <h1 className="col-title">Table</h1>
          <table>
            <thead>
              <tr>
                <td className="buttonRow" colSpan={2}>
                  <button onClick={this.setCountry}>By Country</button>
                  <button onClick={this.setContinent}>By Continent</button>
                </td>
              </tr>
              <tr>
                <td className="buttonRow" colSpan={2}>
                  <button onClick={this.setDescending}>Descending</button>
                  <button onClick={this.setAscending}>Ascending</button>
                </td>
              </tr>
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