import React, { useContext } from 'react';
import api from '../../api/axiosConfig';
import {useState, useEffect} from 'react';

export const PostList = () => {


  const [posts, setPosts] = useState();

  const getPosts = async () =>{
    try{
      const response = await api.get("/post");
      setPosts(response.data);

      
    } 
    catch(err)
    {
      console.log(err);
    }
  }

  const getFileURL = (post) => {
    const reader = new FileReader();
    if(post.file != null)
    {
      const byteArray = new Uint8Array(post.file);
      const blob = new Blob([byteArray], { type: post.file.type });
      const url = URL.createObjectURL(blob);
      return url;
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
            {/* <th>Posted By</th> */}
            <th>Is Approved</th>
            <th>File</th>
            <th>Title</th>
            <th>Description</th>
            <th>Date of Event</th>
            <th>Date of Exp</th>
            <th>Rejection Comments</th>
            {/* <th colspan="2"></th> */}
          </tr>
        </thead>
        <tbody>
          {posts?.map((post) => (
            <tr key={post.id}>
              <td>{post.id}</td>
              {/* <td>{post.person.firstName}</td> */}
              <td>{post.isApproved}</td>
              <td><a href={getFileURL(post)} target="_blank" rel="noopener noreferrer">
            Click to view file
        </a></td>
              <td>{post.title}</td>
              <td>{post.description}</td>
              <td>{post.dateOfEvent}</td>
              <td>{post.dateOfExpiration}</td>
              <td>{post.rejectionComments}</td>
              {/* <td><button className={'btn'} type="submit">Accept</button></td>
              <td><button className={'btn'} type="submit">Reject</button></td> */}

            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}