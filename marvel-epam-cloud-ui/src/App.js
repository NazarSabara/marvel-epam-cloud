import React from 'react';
import {BrowserRouter as Router, Route} from 'react-router-dom';
import './App.css';
import {LoginForm, RegistrationForm, BattlePage} from './components/';

const App = () => (
    <div className="App">
      <Router>
        <Route exact path = "/" component = {LoginForm}/>
        <Route path = "/register" component = {RegistrationForm}/>
        <Route path = "/batlle" component = {BattlePage}/>
      </Router>
    </div> 
);

export default App;
