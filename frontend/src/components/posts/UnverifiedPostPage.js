import React from "react";
import ApprovePost from "./ApprovePost";
import { GetUnverifiedPosts } from "./GetUnverifiedPosts";
import { UnverifiedPostList } from "./UnverifiedPostList";
import Nav from "react-bootstrap/Nav";

const UnverifiedPostPage = () => {
  return (
    <div>
        <div>
            <GetUnverifiedPosts />
        </div>
        {/* <div>
            <ApprovePost />
        </div> */}
        {/* <div>
            <UnverifiedPostList />
        </div> */}
    </div>
  );
};

export default UnverifiedPostPage;
