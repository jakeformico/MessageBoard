import React from 'react'
import Login from './security/Login';
import { Link } from "react-router-dom";
import Feed2 from './feed/Feed2';
const Home = () => {
  return (
      <div className={'main-page'}>
        <div className={'panel-group'}>
          <div className={'panel'}>
            <Feed2/>
            
            <h2>Have an account?</h2>
            <Link to="./Login">Log In!</Link>

            <h2>Otherwise, register here</h2>
            <Link to="./Register">Sign up!</Link>
           
          </div>
        </div>
      </div>
  )





}
export default Home


