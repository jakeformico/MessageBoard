import React, { useContext } from 'react';

//import { GlobalContext } from '../context/GlobalState';
import { Table, Button, ButtonGroup, Link } from "react-bootstrap";
import api from '../../api/axiosConfig';
import {useState, useEffect} from 'react';


export const PersonList = () => {


  const [persons, setPersons] = useState();

  const getPersons = async () =>{
    try{
      const response = await api.get("/person");
      console.log(response.data);
      setPersons(response.data);

    } 
    catch(err)
    {
      console.log(err);
    }
  }


  useEffect(() => {
    //Uses react hhooks to make HTTP request
    getPersons();

  },[])



  return (
    <div>
      <h3>Year: 2023</h3>
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
          </tr>
        </thead>
        <tbody>
          {persons?.map((person) => (
            <tr key={person.id}>
              <td>{person.id}</td>
              <td>{person.firstName}</td>
              <td>{person.lastName}</td>
              <td>{person.email}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}