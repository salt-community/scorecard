import { Link, Navbar, NavbarBrand } from '@nextui-org/react';

const WebHeader = () => {
  return (
    <div>
      <Navbar>
        <Link href={'/'} color="foreground">
          <NavbarBrand>
            <h4 className="font-bold text-inherit">{'< salt />'}</h4>
          </NavbarBrand>
        </Link>
      </Navbar>
    </div>
  );
};

export default WebHeader;
