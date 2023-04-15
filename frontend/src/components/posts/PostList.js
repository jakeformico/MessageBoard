import React, { useContext } from 'react';
import api from '../../api/axiosConfig';
import {useState, useEffect} from 'react';
import { Link } from "react-router-dom";

export const PostList = () => {


  const [posts, setPosts] = useState();

  const getPosts = async () =>{
    try{
      const response = await api.get("/post");
      console.log(response.data)
      setPosts(response.data);

      
    } 
    catch(err)
    {
      console.log(err);
    }
  }


  function base64toFile(file, contentType) {
    const binaryString = atob(file);
    const bytes = new Uint8Array(binaryString.length);
    for (let i = 0; i < binaryString.length; i++) {
      bytes[i] = binaryString.charCodeAt(i);
    }
    const blob = new Blob([bytes], { type: contentType });
    return URL.createObjectURL(blob);
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
            <th>Status</th>
            <th>File</th>
            <th>File Type</th>
            <th>Title</th>
            <th>Description</th>
            <th>Date of Event</th>
            <th>Date of Exp</th>
            <th>Rejection Comments</th>
            <th>Edit</th>
          </tr>
        </thead>
        <tbody>
          {posts?.map((post) => (
            <tr key={post.id}>
              <td>{post.id}</td>
              <td>{post.status}</td>
              <td>
                <a href={base64toFile(post.file, post.contentType)} target="_blank" rel="noopener noreferrer">
                  Click to view file
                </a>
              </td>
              <td>{post.contentType}</td>
              <td>{post.title}</td>
              <td>{post.description}</td>
              <td>{post.dateOfEvent}</td>
              <td>{post.dateOfExpiration}</td>
              <td>{post.rejectionComments}</td>
              <td><Link to={"/facultyEditPost?postId="+post.id}>Edit</Link></td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}