import React from "react";
import {Link} from "@mui/material";
import styles from "./Navbar.module.css";
import {useRouter} from 'next/router';

import { AiOutlineMenu } from "react-icons/ai";
import { useState } from "react";
import { Paper, MenuItem, MenuList, ListItemText } from "@mui/material";
import { CgProfile } from "react-icons/cg";

const Navbar = () => {
  const [menu, setMenu] = useState(false);
  const router = useRouter();

  return (
    <nav className={styles.nav}>
      <div className={styles.wrap}>
        <div className={styles.brand}>
          <Link href="/">
            <h2 className="header">SpotASpot</h2>
          </Link>
        </div>

        <div className={styles.links}>
          <Link href="/" className={`${router.pathname === "/" ? "active" : ""} `}>Home</Link>
          <Link href="/events" className={`${router.pathname === "/events" ? "active" : ""} `}>Events</Link>
          <Link href="/myevents" className={`${router.pathname === "/myevents" ? "active" : ""} `}>My Events</Link>
          <Link href="/about" className={`${router.pathname === "/about" ? "active" : ""} `}>About Us</Link>
          <Link href="/login" className={`${router.pathname === "/login" ? "active" : ""} `}>Login</Link>
          {/* <Link className={styles.icon}>
            <CgProfile />
          </Link> */}
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
