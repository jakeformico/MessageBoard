import React, { useState, useEffect, useContext, useRef } from "react";
import api from "../../api/axiosConfig";

function AddPerson({ setPersons }) {
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [email, setEmail] = useState("");
  const [gender, setGender] = useState("");
  const [age, setAge] = useState(0);
  const [dob, setDob] = useState();
  const [race, setRace] = useState("");
  const [fieldType, setFieldType] = useState("");


  const getPersons = async () => {
    try {
      const response = await api.get("/application");
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
      gender,
      age,
      dob,
      race,
      fieldType
    };

    try {
      const response = await api.post("/person/create", newPerson);
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
          Gender
          <input
            type="text"
            value={gender}
            onChange={(event) => setGender(event.target.value)}
          />
        </label>
        <label>
          Age
          <input
            type="number"
            value={age}
            onChange={(event) => setAge(event.target.value)}
          />
        </label>
        <br />
        <label>
          DOB
          <input
            type="date"
            value={dob}
            onChange={(event) => setDob(event.target.value)}
          />
        </label>
        <label>
          Race
          <input
            type="text"
            value={race}
            onChange={(event) => setRace(event.target.value)}
          />
        </label>
        <label>
          Field of Study
          <input
            type="text"
            value={fieldType}
            onChange={(event) => setFieldType(event.target.value)}
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
