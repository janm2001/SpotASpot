import React from "react";
import { Link } from "@mui/material";
import styles from "./Navbar.module.css";

import { AiOutlineMenu } from "react-icons/ai";
import { useState } from "react";
import { Paper, MenuItem, MenuList, ListItemText } from "@mui/material";
import { CgProfile } from "react-icons/cg";

const Navbar = () => {
  const [menu, setMenu] = useState(false);

  return (
    <nav className={styles.nav}>
      <div className={styles.wrap}>
        <div className={styles.brand}>
          <Link href="/">
            <h2 className="header">SpotASpot</h2>
          </Link>
        </div>

        <div className={styles.links}>
          <Link href="/">Home</Link>
          <Link href="/events">Events</Link>
          <Link href="/myevents">My Events</Link>
          <Link href="/about">About Us</Link>
          <Link className={styles.icon}>
            <CgProfile />
          </Link>
        </div>

        <div className={styles.smallerDisplay}>
          <button onClick={() => setMenu(!menu)}>
            <AiOutlineMenu />
          </button>
        </div>
      </div>
      {menu && (
        <Paper
          className={styles.linksSmall}
          sx={{
            width: "100%",
            background: "#000",
            color: "#fff",
            textAlign: "center",
          }}
        >
          <MenuList dense>
            <MenuItem>
              <ListItemText inset>
                <Link href="/">Home</Link>
              </ListItemText>
            </MenuItem>
            <MenuItem>
              <ListItemText inset>
                <Link href="/events">Events</Link>
              </ListItemText>
            </MenuItem>
            <MenuItem>
              <ListItemText inset>
                <Link href="/myevents">My Events</Link>
              </ListItemText>
            </MenuItem>
            <MenuItem>
              <ListItemText inset>
                <Link href="/about">About us</Link>
              </ListItemText>
            </MenuItem>
            <div className={styles.profile}>
            <MenuItem>
            
            <Link className={styles.icon}>
              
              <CgProfile />
              
            </Link>
            
          </MenuItem>
            </div>
          </MenuList>
        </Paper>
      )}
    </nav>
  );
};

export default Navbar;
