import { Button } from "@material-tailwind/react";
import { Link, Navbar, NavbarBrand } from "@nextui-org/react";

const WebHeader = () => {
  return (
    <div>
      <Navbar className="flex flex-row justify-around">
        <Link href={"/"} color="foreground">
          <NavbarBrand>
            <h4 className="font-bold text-inherit">{"</salt>"}</h4>
          </NavbarBrand>
        </Link>
        <a href="/login">
        <button
            className="bg-accent2 hover:bg-accent text-white font-bold py-2 px-4 rounded"
            
          >
            Login
        </button>   
        </a>
      </Navbar>
    </div>
  );
};

export default WebHeader;
