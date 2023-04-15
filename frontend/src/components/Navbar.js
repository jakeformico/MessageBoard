import { Link } from "react-router-dom";
import Dropdown from "react-bootstrap/Dropdown";
import NavItem from "react-bootstrap/NavItem";
import NavLink from "react-bootstrap/NavLink";
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
  

        <Link to="/persons">Persons</Link>
        <div>
        <Dropdown as={NavItem}>
          <Dropdown.Toggle as={NavLink}>Admin</Dropdown.Toggle>
          <Dropdown.Menu>
            <Dropdown.Item><Link to="/unverifiedposts">Unverified Posts</Link></Dropdown.Item>
            <Dropdown.Item><Link to="/denialreports">Denial Reports</Link></Dropdown.Item>
          </Dropdown.Menu>
        </Dropdown>
        </div>
        <div>
        <Dropdown as={NavItem}>
          <Dropdown.Toggle as={NavLink}>Account</Dropdown.Toggle>
          <Dropdown.Menu>
            <Dropdown.Item><Link to="/login">Log In</Link></Dropdown.Item>
            <Dropdown.Item><Link to="/register">Register</Link></Dropdown.Item>
          </Dropdown.Menu>
        </Dropdown>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
