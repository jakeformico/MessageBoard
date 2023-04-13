import React from 'react'
import AddPost from './AddPost';
import { PostList } from './PostList';

const PostPage = () => {
  return (
    <div>
        <div>
            <PostList />
        </div>
        <div>
            <AddPost />
        </div>
        
    </div>
  )
}

export default PostPage