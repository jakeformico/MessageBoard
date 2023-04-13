import React, { useContext } from 'react';

//import { GlobalContext } from '../context/GlobalState';
import { Table, Button, ButtonGroup, Link } from "react-bootstrap";
import api from '../api/axiosConfig';
import {useState, useEffect} from 'react';

export const CourseList = () => {

  const [courses, setCourses] = useState();

  const getCourses = async () =>{
    try{
      const response = await api.get("/courses/semester/spring");
      console.log(response.data);
      setCourses(response.data);

    } 
    catch(err)
    {
      console.log(err);
    }
  }


  useEffect(() => {
    //Uses react hhooks to make HTTP request
    getCourses();

  },[])
 




  return (
    <div>
      <h3>Year: 2023</h3>
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Code</th>
            <th>Name</th>
            <th>Open Seats</th>
            <th>Course Status</th>
            <th>Semester</th>
          </tr>
        </thead>
        <tbody>
          {courses?.map((course) => (
            <tr key={course.id}>
              <td>{course.id}</td>
              <td>{course.name}</td>
              <td>{course.description}</td>
              <td>{course.openSeats}</td>
              <td>{course.courseStatus}</td>
              <td>{course.semester}</td>

            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}