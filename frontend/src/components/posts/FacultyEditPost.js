import React, { useState, useEffect, useContext, useRef } from "react";
import api from "../../api/axiosConfig";
import { useLocation } from 'react-router-dom';
import { Link } from "react-router-dom";

function FacultyEditPost() {
    const location = useLocation();
    const id = new URLSearchParams(location.search).get("postId");

    const [description, setDescription] = useState();
    const [title, setTitle] = useState();
    const [dateOfEvent, setdateOfEvent] = useState();
    const [dateOfExpiration, setdateOfExpiration] = useState();
    const [post, setPost] = useState();
    const [successMessage, setSuccessMessage] = useState(null);
    const getPost = async () =>{
      try{
        const response = await api.get("/post/"+id);
        console.log(response.data)
        setPost(response.data);
        setTitle(response.data.title);
        setDescription(response.data.description);
        setdateOfEvent(response.data.dateOfEvent);
        setdateOfExpiration(response.data.dateOfExpiration);  
      } 
      catch(err)
      {
        console.log(err);
      }
      
    }

    useEffect(() => {
        //Uses react hhooks to make HTTP request
        getPost();
      },[])
  


    const handleSubmit = async (e) => {
        e.preventDefault();
    
    
        const editPost = {
          title,
          description,
          dateOfEvent,
          dateOfExpiration
        };
    
        try {
          const response = await api.put("/post/"+id, editPost);
          console.log(response.data);
    
        } catch (err) {
          console.log(err);
        }
        finally{
            setSuccessMessage("Successfully edited post!")
          }
      };
    

    return (
        <div>
            <form onSubmit={handleSubmit}>
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
                <br />
                <label>
                    Date of Expiration
                    <input
                        type="date"
                        value={dateOfExpiration}
                        onChange={(event) => setdateOfExpiration(event.target.value)}
                    />
                </label>
                <br />
                <button type="submit">Submit</button>
            </form>
            {successMessage ? <Link to={"/postList"}>Edit post success! Return to post list</Link> : null}
        </div>



    );
}

export default FacultyEditPost;
