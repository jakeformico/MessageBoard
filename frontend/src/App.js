import React from 'react';
import './App.css';
import api from './api/axiosConfig';
import {useState, useEffect} from 'react';
import {Routes, Route, BrowserRouter as Router} from 'react-router-dom';
import Home from './components/Home';
import Navbar from './components/Navbar';
import UnverifiedPostPage from './components/posts/UnverifiedPostPage';
import { DenialReportList}  from './components/posts/DenialReportList';
import PersonPage from './components/users/PersonPage';
import { MonitorView } from './components/MonitorView';
import Login from './components/security/Login';
import Logout from './components/security/Logout';
import Register from './components/security/Register';
import { PostList } from './components/posts/PostList';
import AddPost from './components/posts/AddPost';
import Feed2 from './components/feed/Feed2';
import Calendar from './components/calendar/Calendar';
import FacultyEditPost from './components/posts/FacultyEditPost';

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
            <Route path="/postList" element={<PostList/>} />
            <Route path="/addPost" element={<AddPost/>} />
            <Route path="/unverifiedposts" element={<UnverifiedPostPage/>} />
            <Route path="/denialreports" element={<DenialReportList/>} />
            <Route path="/persons" element={<PersonPage/>} />
            <Route path="/feed" element={<Feed2/>} />
            <Route path="/calendar" element={<Calendar/>} />
            <Route path="/facultyEditPost" element={<FacultyEditPost/>} />
          </Routes>
        </div>
      </div>
    </Router>
  );

}

export default App;