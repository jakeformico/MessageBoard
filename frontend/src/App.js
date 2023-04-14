import React from 'react';
import './App.css';
import api from './api/axiosConfig';
import {useState, useEffect} from 'react';
import {Routes, Route, BrowserRouter as Router} from 'react-router-dom';
import Home from './components/Home';
import Navbar from './components/Navbar';
import CoursePage from './components/CoursePage';
import PostPage from './components/posts/PostPage';
import PersonPage from './components/users/PersonPage';
import ApplicationPage from './components/ApplicationPage';
import Login from './components/security/Login';
import Logout from './components/security/Logout';
import Register from './components/security/Register';
import Feed2 from './components/feed/Feed2';
function App() {
  
  return (
    <Router>
      <div className="App">
        <Navbar />
        <div className="content">
          <Routes>
            
            <Route exact path="/" element={<Home/>} />
            <Route path="/login" element={<Login/>} />
            <Route path="/logout" element={<Logout/>} />
            <Route path="/register" element={<Register/>} />
            {/* <Route path="/courses" element={<CoursePage/>} /> */}
            <Route path="/post" element={<PostPage/>} />
            <Route path="/persons" element={<PersonPage/>} />
            <Route path="/applications" element={<ApplicationPage/>} />
            <Route path="/feed" element={<Feed2/>} />
          </Routes>
        </div>
      </div>
    </Router>
  );

}

export default App;