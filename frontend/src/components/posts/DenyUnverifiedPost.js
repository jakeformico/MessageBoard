import React, { useContext } from "react";
import api from "../../api/axiosConfig";
import { useState, useEffect } from "react";
import Table from "react-bootstrap/Table";
import Button from "react-bootstrap/Button";
import ButtonGroup from "react-bootstrap/ButtonGroup";
import Badge from "react-bootstrap/Badge";
import ListGroup from "react-bootstrap/ListGroup";
import Form from "react-bootstrap/Form";
import  AddDenialReport  from "./AddDenialReport";
export const DenyUnverifiedPost = ({ posts }) => {
  const [byPostId, setByPostId] = useState(0);
  const [status, setStatus] = useState(false);
  const [id, setId] = useState();
  // const [isStatus, setIsStatus] = useState(true);
  console.log(posts);

  const handleClick = async (id, e) => {
    // const updatedPost = {
    //   id,
    //   isStatus
    // };
    console.log(e.target.name);
    try {
        if(e.target.name =="accept"){
            setStatus(true);
            console.log("Post id " + id);
            const response = await api.put("/post/approve/" + id);
            console.log(response.data);
        }
        if(e.target.name =="reject"){
            setStatus(false);
            console.log("Post id " + id);
            const response = await api.put("/post/reject/" + id);
            console.log(response.data);
            //<AddDenialReport id = {id}/>
            //const response = await api.post("/api/report/create");
        }
      
    } catch (err) {
      console.log(err);
    }
  };

  useEffect(() => {
    handleClick();
  }, []);

  return (
    <div className="admin-content">
      <h3>Denial Reports:</h3>
      <ListGroup as="ul">
        {posts
          ?.filter((posts) => String(posts.status).toUpperCase() === "FALSE")
          .map((post) => (
            <ListGroup.Item key={post.id}>
              <div>
                <div>
                  {String(post.title) == "null" ? (
                    <div className="post-title">Post</div>
                  ) : (
                    <div className="post-title">{post.title}</div>
                  )}
                </div>

                <div>
                  {String(post.description) == "null" ? (
                    <div className="post-description">--</div>
                  ) : (
                    <div className="post-description">{post.description}</div>
                  )}
                </div>

                {/* <div className="post-description">{post.description}</div> */}
                <div> Post ID: {post.id}</div>
                <div>
                  {String(post.status).toUpperCase() == "FALSE" ? (
                    <div>Post Status: PENDING</div>
                  ) : (
                    <div>Post Status: ACCEPTED </div>
                  )}
                </div>
                <div>
                  {String(post.dateOfEvent) == "null" ? (
                    <div>Event Date: No Date</div>
                  ) : (
                    <div>Event Date: {post.dateOfEvent}</div>
                  )}
                </div>
              </div>

              <div>
                {String(post.status).toUpperCase() == "FALSE" ? (
                  <Button
                    variant="success"
                    name="accept"
                    type="submit"
                    value={post.id || 0}
                    onClick={(e) => handleClick(post.id, e)}
                    size="sm"
                    className="mb-1"
                  >
                    Approve{" "}
                  </Button>
                ) : (
                  <Button
                    variant="warning"
                    name="reject"
                    type="submit"
                    value={post.id || 0}
                    onClick={(e) => handleClick(post.id, e)}
                    size="sm"
                    className="mb-1"
                    disabled
                  >
                    Deny{" "}
                  </Button>
                )}
              </div>
            </ListGroup.Item>
          ))}
      </ListGroup>
    </div>
  );
};
