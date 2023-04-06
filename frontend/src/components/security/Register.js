import React, { useState, useEffect, useContext, useRef } from "react";
import api from "../../api/axiosConfig";

function AddPerson({ setPersons }) {
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");


  const getPersons = async () => {
    try {
      const response = await api.get("/register");
      console.log(response.data);
      setPersons(response.data);
    } catch (err) {
      console.log(err);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();


    const newPerson = {
      firstName,
      lastName,
      email,
      password
    };

    try {
      const response = await api.post("/register/create", newPerson);
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
          First Name:
          <input
            type="text"
            // ref={nameRef}
            value={firstName}
            onChange={(event) => setFirstName(event.target.value)}
          />
        </label>
        <br />
        <label>
          Last Name:
          <input
            type="text"
            // ref={nameRef}
            value={lastName}
            onChange={(event) => setLastName(event.target.value)}
          />
        </label>
        <br />
        <label>
          Email
          <input
            type="text"
            // ref={emailRef}
            value={email}
            onChange={(event) => setEmail(event.target.value)}
          />
        </label>
        <br />
        <label>
          Password
          <input
            type="text"
            value={password}
            onChange={(event) => setPassword(event.target.value)}
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

export default AddPerson;
