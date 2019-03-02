import React, {
  Component
} from 'react';
// import ips from "./ip.json";
import './App.css';

class App extends Component {
  constructor() {
    super();

    //Map each IP address to a fetch call; chain the fetches to load in the background.
    const fetches =
      ips
      .map(ip => `http://localhost:8080/connection-info?ip=${ip}`);

    this.state = {
      fetches: fetches,
      loadedTrackers: []
    };
  }

  componentWillMount() {
    this.state.fetches.reduce((acc, next) => {
      return acc
        .then(data => data ? data.json() : null)
        .then(jsonData => {
          if (jsonData) {
            this.setState({
              ...this.state,
              loadedTrackers: this.state.loadedTrackers.concat(jsonData)
            });
          }

          return fetch(next);
        });
    }, Promise.resolve());
  }

  render() {
    const trackerRows = this.state.loadedTrackers.map(tracker =>
      <
      tr key = {
        tracker.ip
      } >
      <
      td > {
        tracker.ip
      } < /td> <
      td > {
        tracker.country
      } < /td> <
      td > {
        tracker.state
      } < /td> <
      /tr>)

      return ( <
        div className = "App" >
        <
        div className = "col" >
        <
        h1 className = "col-title" > Map < /h1> <
        /div> <
        div className = "col" >
        <
        h1 className = "col-title" > Trackers < /h1> <
        table >
        <
        thead >
        <
        tr >
        <
        th > IP < /th> <
        th > Country < /th> <
        th > State < /th> <
        /tr> <
        /thead> <
        tbody > {
          trackerRows
        } <
        /tbody> <
        /table> <
        /div> <
        /div>
      );
    }
  }

  export default App;