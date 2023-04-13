import React, { useContext } from 'react';

//import { GlobalContext } from '../context/GlobalState';
import { Table, Button, ButtonGroup, Link } from "react-bootstrap";
import api from '../api/axiosConfig';
import {useState, useEffect} from 'react';

export const ApplicationList = () => {


  const [applications, setApplications] = useState();

  const getApplications = async () =>{
    try{
      const response = await api.get("/application");
      console.log(response.data);
      setApplications(response.data);

    } 
    catch(err)
    {
      console.log(err);
    }
  }


  useEffect(() => {
    //Uses react hhooks to make HTTP request
    getApplications();

  },[])
 


  return (
    <div>
      <h3>Year: 2023</h3>
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Application Status</th>
            <th>Registered At</th>
            <th>Person Name</th>
            <th>Person Email</th>
            <th>Course Code</th>
            <th>Semester</th>
            {/* <th colspan="2"></th> */}
          </tr>
        </thead>
        <tbody>
          {applications?.map((application) => (
            <tr key={application.id}>
              <td>{application.id}</td>
              <td>{application.courseApplicationStatus}</td>
              <td>{application.registeredAt}</td>
              <td>{application.person.firstName}</td>
              <td>{application.person.email}</td>
              <td>{application.course.name}</td>
              <td>{application.course.semester}</td>
              {/* <td><button className={'btn'} type="submit">Accept</button></td>
              <td><button className={'btn'} type="submit">Reject</button></td> */}

            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}