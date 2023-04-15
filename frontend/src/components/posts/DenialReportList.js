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

  const getPosts = async () => {

    try {
      const response = await api.get("/post/denialReport");
      console.log(response.data);
      setPosts(response.data);

    } catch (err) {
      console.log(err);
    }
  };

  useEffect(() => {
    getPosts();
  }, []);


  function base64toFile(file, contentType) {
    const binaryString = atob(file);
    const bytes = new Uint8Array(binaryString.length);
    for (let i = 0; i < binaryString.length; i++) {
      bytes[i] = binaryString.charCodeAt(i);
    }
    const blob = new Blob([bytes], { type: contentType });
    return URL.createObjectURL(blob);
  }


  return (
    <div className="admin-container">
      <div className="admin-input">
        <h3>Deny Posts:</h3>
        <div>
          <h3>Denial Report:</h3>
          <table>
            <thead>
              <tr>
                <th>Author</th>
                <th>Title</th>               
                <th>Status</th>
                <th>Rejection Comments</th>
              </tr>
            </thead>
            <tbody>
              {posts?.map((post) => (
                <tr key={post.id}>
                  <td>{post[1]} {post[2]}</td>
                  <td>{post[0]}</td>
                  <td>{post[3]}</td>
                  <td>{post[4]}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
};
