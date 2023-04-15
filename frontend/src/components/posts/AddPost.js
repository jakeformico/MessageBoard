import React, { useState, useEffect, useContext, useRef } from "react";
import api from "../../api/axiosConfig";

function AddPost() {
  const [uploadResponse, setUploadResponse] = useState();
  const [description, setDescription] = useState();
  const [title, setTitle] = useState();
  const [dateOfEvent, setdateOfEvent] = useState();
  const [dateOfExpiration, setdateOfExpiration] = useState();
  const [successMessage, setSuccessMessage] = useState();
  const [file, setFile] = useState(null);



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
    try {
      const response = await api.post('/post/create/1', formData);
      setUploadResponse(response.data);
      setSuccessMessage("Successfully added post with id: " + response.data.id)
    } catch (error) {
      console.error(error);
      setSuccessMessage("Error occurred: " + error)
    }
  };

  return (
<div>
    <form onSubmit={handleUpload}>
      <label style={{marginBottom: '8px', marginRight: '4px'}}>
        Title
      </label>
        <input
          type="text"
          value={title}
          onChange={(event) => setTitle(event.target.value)}
        />
      <br />
      <label style={{marginBottom: '8px', marginRight: '4px'}}>
        Description
      </label>
        <input
          type="text"
          value={description}
          onChange={(event) => setDescription(event.target.value)}
        />

      <br />
      <label style={{marginBottom: '8px', marginRight: '4px'}}>
        Date of Event
      </label>
        <input
          type="date"
          value={dateOfEvent}
          onChange={(event) => setdateOfEvent(event.target.value)}
        />
      <br/>
      <input type="file" onChange={handleFileChange} style={{marginBottom: '8px'}}/>
      <br/>
      <label style={{marginBottom: '8px', marginRight: '4px'}}>
        Date of Expiration
      </label>
        <input
          type="date"
          value={dateOfExpiration}
          onChange={(event) => setdateOfExpiration(event.target.value)}
        />
      <br/>
      <button type="submit">Submit</button>
    </form>
    {successMessage}
    </div>
  );
}

export default AddPost;
