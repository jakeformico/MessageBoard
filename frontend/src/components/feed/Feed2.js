import React, { useContext } from 'react';
import api from '../../api/axiosConfig';
import {useState, useEffect} from 'react';

const colors = ["#008000", "#FFBB28", "#008000", "#FFBB28", "#008000"];
const delay = 5000;

function Feed2() {

  const [contentList, setFeed] = useState([]);

  const getFeed = async () =>{
    try{
      const response = await api.get("/feed");
      console.log(response.data);
      setFeed(response.data[0].contentList);

    } 
    catch(err)
    {
      console.log(err);
    }
  }

  useEffect(() => {
    //Uses react hhooks to make HTTP request
    getFeed();

  },[])

  const [index, setIndex] = React.useState(0);
  const timeoutRef = React.useRef(null);

  function resetTimeout() {
    if (timeoutRef.current) {
      clearTimeout(timeoutRef.current);
    }
  }

  React.useEffect(() => {
    resetTimeout();
    timeoutRef.current = setTimeout(
      () =>
        setIndex((prevIndex) =>
          prevIndex === contentList.length - 1 ? 0 : prevIndex + 1
        ),
      delay
    );

    return () => {
      resetTimeout();
    };
  }, [index]);

  return (
    <div className="slideshow">
      <div
        className="slideshowSlider"
        style={{ transform: `translate3d(${-index * 100}%, 0, 0)` }}
      >
        {contentList.map((post, index) => (
          <div
            className="slide"
            key={index}
          >
            <div>
              <br></br>
                <h3 className='postTitle'>{post.title}</h3>
                <br></br>
                <body className='postDescription'>{post.description} 
                <br></br>
                <br></br>
                {post.dateOfEvent}</body>
            </div>
          </div>
        ))}
      </div>

      <div className="slideshowDots">
        {contentList.map((_, idx) => (
          <div
            key={idx}
            className={`slideshowDot${index === idx ? " active" : ""}`}
            onClick={() => {
              setIndex(idx);
            }}
          ></div>
        ))}
      </div>
    </div>
  );
}

export default Feed2;
