import logo from "./logo.svg";
import "./App.css";

import React, { Component } from "react";
import Dashboard from "./component/Dashboard";
import Header from "./component/Layout/Header";
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter as Router, Route } from "react-router-dom";
import AddProject from "./component/project/AddProject";
import { Provider } from "react-redux";
import store from "./store";
import UpdateProject from "./component/project/updateProject";

class App extends Component {
  render() {
    return (
      <Provider store={store}>
        <Router>
          <Header />
          <Route path="/dashboard" component={Dashboard} />
          <Route path="/addProject" component={AddProject} />
          <Route path="/updateProject/:id" component={UpdateProject} />
        </Router>
      </Provider>
    );
  }
}



export default App;