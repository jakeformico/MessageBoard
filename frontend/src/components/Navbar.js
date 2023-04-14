import { Link } from "react-router-dom";

const Navbar = () => {
  return (
    <nav className="navbar">
      <h1>Message Board</h1>
      <div className="links">
        <Link to="/">Home</Link>
        <Link to="/postList">Post List</Link>
        <Link to="/addPost">Add Post</Link>
        <Link to="/monitor">Monitor View</Link>
        <Link to="/persons">Persons</Link>
  

      </div>
    </nav>
  );
}
 
export default Navbar;