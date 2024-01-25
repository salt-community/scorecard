import { Navbar, NavbarBrand } from '@nextui-org/react';
import { SaltLogo } from './SaltLogo';

const WebHeader = () => {
  return (
    <div>
      <Navbar>
        <NavbarBrand>
          <SaltLogo />
          <p className="font-bold text-inherit">Salt</p>
        </NavbarBrand>
      </Navbar>
    </div>
  );
};

export default WebHeader;
