import { Link } from "react-router-dom";

const Navbar = () => {
  return (
    <nav className="navbar">
      <h1>Message Board</h1>
      <div className="links">
        <Link to="/">Home</Link>
        <Link to="/posts">Posts</Link>
    
        <Link to="/monitor">Monitor View</Link>
        <Link to="/persons">Persons</Link>
        <Link to="/applications">Applications</Link>
        <Link to="/feed">Feed</Link>
      </div>
    </nav>
  );
}
 
export default Navbar;