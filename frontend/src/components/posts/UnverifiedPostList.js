import React, { useContext } from "react";
import api from "../../api/axiosConfig";
import { useState, useEffect } from "react";
import Table from "react-bootstrap/Table";
import Button from "react-bootstrap/Button";
import ButtonGroup from "react-bootstrap/ButtonGroup";
import Badge from "react-bootstrap/Badge";
import ListGroup from "react-bootstrap/ListGroup";
import Form from "react-bootstrap/Form";

export const UnverifiedPostList = ({ posts }) => {
  // const [posts, setPosts] = useState();
  const [approved, setApproved] = useState(false);
  const [id, setId] = useState();
  
  console.log(posts);
  // const handleChange = async () =>{

  //   console.log("checked box");
  // }


  // const handleClick = (id) =>{
  //   console.log("click");
  //   console.log(id);
  // }

  const handleClick = async (id) =>{
    // const updatedPost = {
    //   id,
    //   isApproved
    // };
    
    try{
      

      setApproved(true);
      console.log("Post id " + id);
      const response = await api.put("/post/approve/" + id);
      console.log(response.data);
       


    }
    catch(err)
    {
      console.log(err);
    }
  }

  useEffect(() => {
    //Uses react hhooks to make HTTP request
    
    handleClick();

  },[])

 

  return (
    <div className="bootstrap-list">
      <ListGroup as="ul" >
        {posts?.map((post) => (
          <ListGroup.Item key={post.id} className="d-flex justify-content-center align-items-start mb-2">
            <div>
            { String(post.approved).toUpperCase() == 'FALSE' ?  <Badge bg="warning" pill>{post.id}</Badge> : <Badge bg="primary" pill>{post.id}</Badge> }
            </div>
              
            
            <div className="ms-2 me-auto">
              <div className="fw-bold">{post.title}</div>
              <div>{post.description}</div>
              <div className="text-muted">{String (post.approved).toUpperCase() }</div>
              <div>{post.dateOfEvent}</div>
            </div>

            <Button variant="success" type="submit" value={post.id || 0} onClick={() => handleClick(post.id)} size="sm"className="mb-1">Approve </Button>



            
          </ListGroup.Item>
        ))}
      </ListGroup>
    </div>
  );
};

