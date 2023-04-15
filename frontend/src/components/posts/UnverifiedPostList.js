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
  const [status, setStatus] = useState();
  const [id, setId] = useState();

  console.log(posts);

  const handleClick = async (id, e) => {
    // const updatedPost = {
    //   id,
    //   isStatus
    // }; 
    try {

      setStatus(true);
      console.log("Post id " + id);
      const response = await api.put("/post/approve/" + id);
      console.log(response.data);
    } catch (err) {
      console.log(err);
    }
  };

  useEffect(() => {
    handleClick();
  }, []);

  return (
    <div className="admin-content">
      <ListGroup as="ul">
        {posts
          ?.filter((posts) => String(posts.status).toUpperCase() === "PENDING")
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
                <div> Post ID: {post.id}</div>
                <div>Post Status: {post.status}</div>

                <div>
                  {String(post.dateOfEvent) == "null" ? (
                    <div>Event Date: No Date</div>
                  ) : (
                    <div>Event Date: {post.dateOfEvent}</div>
                  )}
                </div>
              </div>

              <div>
                <div>
                  <div>
                    <Button
                      variant="success"
                      name="approve"
                      type="submit"
                      value={post.id || 0}
                      onClick={(e) => handleClick(post.id, e)}
                      size="sm"
                      className="mb-1"
                    >
                      Approve{" "}
                    </Button>
                  </div>
                  {/* <div>
                    <Button
                      variant="danger"
                      name="deny"
                      type="submit"
                      value={post.id || 0}
                      onClick={(e) => handleClick(post.id, e)}
                      size="sm"
                      className="mb-1"
                    >
                      Reject{" "}
                    </Button>
                  </div> */}
                </div>
              </div>
            </ListGroup.Item>
          ))}
      </ListGroup>
    </div>
  );
};
