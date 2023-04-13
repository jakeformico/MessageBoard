import React, { useState, useEffect, useContext, useRef } from "react";
import api from "../api/axiosConfig";

function AddCourse({ setCourses }) {
  const [name, setName] = useState("");
  const [description, setDescription] = useState("");
  const [capacity, setCapacity] = useState(4);
  const [coursestatus, setCourseStatus] = useState("");
  const [semester, setSemester] = useState("");
  const [dateavailable, setDateAvailable] = useState(2023);

  const getCourses = async () => {
    try {
      const response = await api.get("/courses");
      console.log(response.data);
      setCourses(response.data);
    } catch (err) {
      console.log(err);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();


    const newCourse = {
      name,
      description,
      semester,

    };

    try {
      const response = await api.post("/courses/create", newCourse);
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
          Name:
          <input
            type="text"
            value={name}
            onChange={(event) => setName(event.target.value)}
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
          Semester
          <input
            type="text"
            value={semester}
            onChange={(event) => setSemester(event.target.value)}
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

export default AddCourse;
