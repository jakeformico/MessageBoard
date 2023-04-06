import React from 'react'
import Login from './security/Login';
import { Link } from "react-router-dom";
const Home = () => {
  return (
      <div className={'main-page'}>
        <div className={'panel-group'}>
          <div className={'panel'}>
            <h1>CSI 5324 Message Board</h1><br /><br />
            
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


