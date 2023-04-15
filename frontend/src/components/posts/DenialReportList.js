import React from "react";
import api from "../../api/axiosConfig";
import { UnverifiedPostList } from "./UnverifiedPostList";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import "bootstrap/dist/css/bootstrap.css";
import { useState, useEffect } from "react";
import ListGroup from "react-bootstrap/ListGroup";
import { DenyUnverifiedPost } from "./DenyUnverifiedPost";

export const DenialReportList = () => {
  const [byPostId, setByPostId] = useState(0);
  const [approved, setApproved] = useState(false);
  const [id, setId] = useState();
  const [posts, setPosts] = useState();
  // const [isApproved, setIsApproved] = useState(true);

  const getPosts = async () => {
    //e.preventDefault();

    try {
      const response = await api.get("/post");
      console.log(response.data);
      setPosts(response.data);

      // setPosts();
      // const response = await api.get("/post/" + byPostId);
      // console.log(response.data);
      // setPosts([response.data]);
      setByPostId(0);
    } catch (err) {
      console.log(err);
    }
  };

  useEffect(() => {
    getPosts();
  }, []);



  return (
    <>
      <div className="admin-container">
        <div className="admin-input">
          <h3>Deny Posts:</h3>
          <div className="bootstrap-form">
            <Form onSubmit={getPosts}>
              <Form.Group>
                <Form.Label>Post ID:</Form.Label>
                <Form.Control
                  type="number"
                  value={byPostId}
                  onChange={(e) => setByPostId(e.target.value)}
                  placeholder="0"
                />
                <Form.Text className="text-muted">
                  Enter post ID for a post or 0 for all posts.
                </Form.Text>
              </Form.Group>
              <Button variant="success" type="submit">
                Submit
              </Button>
            </Form>
          </div>
        </div>

        <div className="admin-content">
          <UnverifiedPostList posts={posts} />
        </div>
      </div>
    </>
  );
};
