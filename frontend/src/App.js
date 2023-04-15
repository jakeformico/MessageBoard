import React from 'react';
import './App.css';
import api from './api/axiosConfig';
import {useState, useEffect} from 'react';
import {Routes, Route, BrowserRouter as Router} from 'react-router-dom';
import Home from './components/Home';
import Navbar from './components/Navbar';
import PostPage from './components/posts/PostPage';
import UnverifiedPostPage from './components/posts/UnverifiedPostPage';
import { DenialReportList}  from './components/posts/DenialReportList';
import PersonPage from './components/users/PersonPage';
import { MonitorView } from './components/MonitorView';
import Login from './components/security/Login';
import Logout from './components/security/Logout';
import Register from './components/security/Register';
import Feed2 from './components/feed/Feed2';
import Calendar from './components/calendar/Calendar';
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
            <Route path="/monitor" element={<MonitorView/>}/>
            <Route path="/register" element={<Register/>} />
            <Route path="/posts" element={<PostPage/>} />
            <Route path="/unverifiedposts" element={<UnverifiedPostPage/>} />
            <Route path="/denialreports" element={<DenialReportList/>} />
            <Route path="/persons" element={<PersonPage/>} />
            <Route path="/feed" element={<Feed2/>} />
            <Route path="/calendar" element={<Calendar/>} />
          </Routes>
        </div>
      </div>
    </Router>
  );

}

export default App;