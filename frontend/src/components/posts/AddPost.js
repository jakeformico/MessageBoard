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
  const [file, setFile] = useState(null);
  const [uploadResponse, setUploadResponse] = useState(null);



  const handleFileChange = (event) => {
    setFile(event.target.files[0]);
  };

  const handleUpload = async (event) => {
    event.preventDefault();
    const formData = new FormData();
    formData.append('file', file);
    formData.append('title', title);
    formData.append('description', description);
    formData.append('dateOfEvent', dateOfEvent);
    formData.append('dateOfExpiration', dateOfExpiration);
    // const jsonBody = JSON.stringify({ title, description, dateOfEvent, dateOfExpiration, });
    // formData.append('data', new Blob([jsonBody])); //, { type: 'application/json' }));
    try {
      const response = await api.post('/post/create/1', formData);
      setUploadResponse(response.data);
    } catch (error) {
      console.error(error);
    }
  };

  return (

    <form onSubmit={handleUpload}>
      <label>
        Title
        <input
          type="text"
          value={title}
          onChange={(event) => setTitle(event.target.value)}
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
      <br/>
      <input type="file" onChange={handleFileChange} />
      <br/>
      <label>
        Date of Expiration
        <input
          type="date"
          value={dateOfExpiration}
          onChange={(event) => setdateOfExpiration(event.target.value)}
        />
      </label>
      <br/>
      <button type="submit">Submit</button>
    </form>


  );
}

export default AddPost;
