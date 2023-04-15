import React from "react";
import api from "../../api/axiosConfig";
import { UnverifiedPostList } from "./UnverifiedPostList";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import "bootstrap/dist/css/bootstrap.css";
import { useState, useEffect } from "react";

export const GetUnverifiedPosts = () => {
  const [byPostId, setByPostId] = useState(0);
  const [posts, setPosts] = useState();
  // const [isStatus, setIsApproved] = useState(true);

  const getPosts = async (e) => {
    e.preventDefault();

    try {
      if (byPostId > 0) {
        setPosts();
        console.log(byPostId);
        const response = await api.get("/post/" + byPostId);
        console.log(response.data);
        setPosts([response.data]);
      } else {
        const response = await api.get("/post");
        console.log(response.data);
        setPosts(response.data);
      }

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
          <h3>Get Unverified Post</h3>
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
