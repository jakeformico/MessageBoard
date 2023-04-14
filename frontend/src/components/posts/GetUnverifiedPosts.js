import React from "react";
import api from "../../api/axiosConfig";
import { UnverifiedPostList } from "./UnverifiedPostList";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import "bootstrap/dist/css/bootstrap.css";
import {useState, useEffect} from 'react';
import Table from 'react-bootstrap/Table';
import Accordion from 'react-bootstrap/Accordion';


export const GetUnverifiedPosts = () => {
  const [byPostId, setByPostId] = useState(0);
  const [posts, setPosts] = useState();
  // const [isApproved, setIsApproved] = useState(true);

  const getPosts = async (e) =>{
    e.preventDefault();

    try{
      if (byPostId > 0) {
        setPosts();
        console.log(byPostId);
        const response = await api.get("/post/" + byPostId);
        console.log(response.data);
        setPosts([response.data]);
      }
      else{
        const response = await api.get("/post");
        console.log(response.data);
        setPosts(response.data);
      }

      
      // setPosts();
      // const response = await api.get("/post/" + byPostId);
      // console.log(response.data);
      // setPosts([response.data]);
      setByPostId(0);

    } 
    catch(err)
    {
      console.log(err);
    }

  }

  useEffect(() => {

    getPosts();

  },[])
 
  // const approvePost = async (e) => {
  //   e.preventDefault();


  //   const updatedPost = {
  //     isApproved
  //   };

  //   try {
  //     const response = await api.post("/post/approve/", isApproved);
  //     console.log(response.data);

  //   } catch (err) {
  //     console.log(err);
  //   }
  // };

  // const rejectPost = async (e) => {
  //   e.preventDefault();


  //   const updatedPost = {
  //     isApproved
  //   };

  //   try {
  //     const response = await api.post("/post/reject/", isApproved);
  //     console.log(response.data);

  //   } catch (err) {
  //     console.log(err);
  //   }
  // };



  return (
    <>
    <div>
    <h3>Get Unverified Post</h3>
    <div className="bootstrap-form">
        <Form onSubmit={getPosts}>
          <Form.Group>
            <Form.Label>Post ID:</Form.Label>
            <Form.Control type="number" value={byPostId} onChange={(e) => setByPostId(e.target.value)} placeholder="0" />
          <Form.Text className="text-muted">Enter post ID for a post or 0 for all posts.</Form.Text>
          </Form.Group>
          <Button variant="success" type="submit">Submit</Button>

        </Form>
      </div>

    {/* <div>
      
      <Table striped bordered hover>
        <thead>
          <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Description</th>
            <th>Is Approved</th>
            <th>Date of Event</th>
            <th>Uploaded File</th>
            <th>Rejection Comments</th>
            <th colSpan="2">Approve Post</th>
          </tr>
        </thead>
        <tbody>
        {posts?.map((post) => (
            <tr key={post.id}>
              <td>{post.id}</td>
              <td>{post.title}</td>
              <td>{post.description}</td>
              <td>{post.isApproved}</td>
              <td>{post.dateOfEvent}</td>
              <td>{post.uploadedFile}</td>
              <td>{post.rejectionComments}</td>
              <td><Button variant="success" type="submit" size="sm">Approve</Button></td>
              <td><Button variant="warning" type="submit" size="sm">Reject</Button></td>
              

            </tr>
            ))}
        </tbody>
      </Table>
    </div>*/}
  </div> 
      
  <UnverifiedPostList posts={posts}/>
  </>
  );
};
