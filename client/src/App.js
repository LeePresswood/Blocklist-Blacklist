import React, {
  Component
} from 'react';
import logo from './logo.svg';
import './App.css';

class App extends Component {
  constructor() {
    super();

    this.state = { loaded: [] };
  }

  componentWillMount() {
    fetch('http://localhost:8080/connection-info?ip=www.google.com')
      .then(res => res.json())
      .then(whois => {
        this.setState({ ...this.state, loaded: [whois] });
      })
  }

  render() {
    return (
      <div className="App" >
        <div className="col"></div>
        <div className="col"></div>
      </div>
    );
  }
}

export default App;