import React, { useContext } from 'react';
import api from '../../api/axiosConfig';
import {useState, useEffect} from 'react';
import Accordion from 'react-bootstrap/Accordion';
export const DenialReportList = () => {


  const [posts, setPosts] = useState();

  const getPosts = async () =>{
    try{
      const response = await api.get("/post/denialReport");
      console.log(response.data);
      setPosts(response.data);

    } 
    catch(err)
    {
      console.log(err);
    }
  }


  useEffect(() => {
    //Uses react hhooks to make HTTP request
    getPosts();

  },[])
 


  return (
    <div>
      <h1>Denial Reports</h1>
      {posts?.map((post) => (
    <Accordion defaultActiveKey="0" flush>
    
    <Accordion.Item eventKey= "0">
      <Accordion.Header>{post.title}</Accordion.Header>
      <Accordion.Body>
        <div>
          Reason for Denial: {post.rejectionComments}
        </div>

      </Accordion.Body>
    </Accordion.Item>


  </Accordion>
))}
    </div>

  
    // <div>
    //   <h3>Past Posts:</h3>
    //   <table>
    //     <thead>
    //       <tr>
    //         <th>ID</th>
    //         {/* <th>Posted By</th> */}
    //         <th>Is Approved</th>
    //         <th>Uploaded File</th>
    //         <th>Title</th>
    //         <th>Description</th>
    //         <th>Date of Event</th>
    //         <th>Date of Exp</th>
    //         <th>Rejection Comments</th>
    //         {/* <th colspan="2"></th> */}
    //       </tr>
    //     </thead>
    //     <tbody>
    //       {posts?.map((post) => (
    //         <tr key={post.id}>
    //           <td>{post.id}</td>
    //           {/* <td>{post.person.firstName}</td> */}
    //           <td>{post.isApproved}</td>
    //           <td>{post.uploadedFile}</td>
    //           <td>{post.title}</td>
    //           <td>{post.description}</td>
    //           <td>{post.dateOfEvent}</td>
    //           <td>{post.dateOfExpiration}</td>
    //           <td>{post.rejectionComments}</td>
    //           {/* <td><button className={'btn'} type="submit">Accept</button></td>
    //           <td><button className={'btn'} type="submit">Reject</button></td> */}

    //         </tr>
    //       ))}
    //     </tbody>
    //   </table>
    // </div>
  );
}