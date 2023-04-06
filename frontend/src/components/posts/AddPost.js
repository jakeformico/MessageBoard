import React, { useState, useEffect, useContext, useRef } from "react";
import api from "../../api/axiosConfig";

function AddPost({ setPosts }) {
  const [isApproved, setIsApproved] = useState();
  const [uploadedfile, setUploadedFile] = useState();
  const [description, setDescription] = useState();
  const [title, setTitle] = useState();
  const [dateOfEvent, setdateOfEvent] = useState();
  const [dateOfExpiration, setdateOfExpiration] = useState();
  const [rejectionComments, setrejectionComments] = useState();



  const getPosts = async () => {
    try {
      const response = await api.get("/post");
      console.log(response.data);
      setPosts(response.data);
    } catch (err) {
      console.log(err);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();


    const newPost = {
      isApproved,
      uploadedfile,
      description,
      title,
      dateOfEvent,
      dateOfExpiration,
      rejectionComments
    };

    try {
      const response = await api.post("/post/create", newPost);
      console.log(response.data);

    } catch (err) {
      console.log(err);
    }
  };


  useEffect(() => {
    handleSubmit();
  }, []);

  return (
    <form onSubmit={handleSubmit}>
      <div className="form-control">
      <label>
          Title
          <input
            type="text"
            value={title}
            onChange={(event) => setTitle(event.target.value)}
          />
        </label>
        <label>
          Is Approved:
          <input
            type="text"
            // ref={nameRef}
            value={isApproved}
            onChange={(event) => setIsApproved(event.target.value)}
          />
        </label>
        <br />
        <label>
          Uploaded File
          <input
            type="text"
            // ref={uploadedfileRef}
            value={uploadedfile}
            onChange={(event) => setUploadedFile(event.target.value)}
          />
        </label>
        <br />
        <label>
          Description
          <input
            type="text"
            value={description}
            onChange={(event) => setDescription(event.target.value)}
          />
        </label>

        <br />
        <label>
          Date of Event
          <input
            type="date"
            value={dateOfEvent}
            onChange={(event) => setdateOfEvent(event.target.value)}
          />
        </label>
        <label>
          Date of Expiration
          <input
            type="date"
            value={dateOfExpiration}
            onChange={(event) => setdateOfExpiration(event.target.value)}
          />
        </label>
        <label>
          Rejection Comments
          <input
            type="text"
            value={rejectionComments}
            onChange={(event) => setrejectionComments(event.target.value)}
          />
        </label>
        <br />
      </div>

      <div className={'submit'}>
        <button className={'btn'} type="submit">Submit</button>
      </div>
      
    </form>
  );
}

export default AddPost;
