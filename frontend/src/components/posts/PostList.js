import React, { useContext } from 'react';
import api from '../../api/axiosConfig';
import {useState, useEffect} from 'react';

export const PostList = () => {


  const [posts, setPosts] = useState();

  const getPosts = async () =>{
    try{
      const response = await api.get("/post");
      console.log(response.data);
      setPosts(response.data);

    } 
    catch(err)
    {
      console.log(err);
    }
  }


  useEffect(() => {
    //Uses react hhooks to make HTTP request
    getPosts();

  },[])
 


  return (
    <div>
      <h3>Past Posts:</h3>
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Post Status</th>
            <th>Registered At</th>
            <th>Person Name</th>
            <th>Person Email</th>
            <th>Course Code</th>
            <th>Semester</th>
            {/* <th colspan="2"></th> */}
          </tr>
        </thead>
        <tbody>
          {posts?.map((post) => (
            <tr key={post.id}>
              <td>{post.id}</td>
              <td>{post.coursePostStatus}</td>
              <td>{post.registeredAt}</td>
              <td>{post.person.firstName}</td>
              <td>{post.person.email}</td>
              <td>{post.course.name}</td>
              <td>{post.course.semester}</td>
              {/* <td><button className={'btn'} type="submit">Accept</button></td>
              <td><button className={'btn'} type="submit">Reject</button></td> */}

            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}