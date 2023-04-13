import React, { useState, useEffect, useContext, useRef } from "react";
import api from "../api/axiosConfig";


function AddApplication({ setApplications }) {
  const [personId, setPersonId] = useState(1);
  const [courseId, setCourseId] = useState(1);


  const getApplications = async () => {
    try {
      const response = await api.get("/application");
      console.log(response.data);
      setApplications(response.data);
    } catch (err) {
      console.log(err);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();


    const newApplication = {
      personId,
      courseId,

    };

    try {
      const response = await api.post("/application/create/" + personId +"/" + courseId , newApplication);
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
          Person ID
          <input
            type="number"
            value={personId}
            onChange={(event) => setPersonId(event.target.value)}
          />
        </label>
        <br />
        <label>
          Course ID
          <input
            type="number"
            value={courseId}
            onChange={(event) => setCourseId(event.target.value)}
          />
        </label>
        <br />
        <br />
      </div>

      <div className={'submit'}>
        <button className={'btn'} type="submit">Submit</button>
      </div>
      
    </form>
  );
}

export default AddApplication;
