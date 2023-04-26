import React, { useEffect } from "react";
// import { Link } from "@mui/material";
import Link from "next/link";
import styles from "./Navbar.module.css";
import { useRouter } from "next/router";
import { CgProfile} from 'react-icons/cg';
import { AiOutlineMenu } from "react-icons/ai";
import { useState } from "react";
import { Paper, MenuItem, MenuList, ListItemText,Button } from "@mui/material";

const Navbar = () => {
  const [menu, setMenu] = useState(false);
  const [profileOpen, setProfileOpen] = useState(false);
  const [loggedIn, setLoggedIn] = useState(false);
  const router = useRouter();

  useEffect(() => {
    const isLoggedIn = () => {
      return !!localStorage.getItem("token");
    };

    setLoggedIn(isLoggedIn());
  }, [router]);

  const handleLogout = () => {
    localStorage.removeItem("token");
    setLoggedIn(false);
    setProfileOpen(false);
    router.push("/");
  };

  console.log(profileOpen);


  

  

  return (
    <nav className={styles.nav}>
      <div className={styles.wrap}>
        <div className={styles.brand}>
          <Link href="/">
            <h2 className="header">SpotASpot</h2>
          </Link>
        </div>

        <div className={styles.links}>
          <Link
            href="/"
            className={`${router.pathname === "/" ? "active" : ""} `}
          >
            Home
          </Link>
          <Link
            href="/events"
            className={`${router.pathname === "/events" ? "active" : ""} `}
          >
            Events
          </Link>
          <Link
            href="/myevents"
            className={`${router.pathname === "/myevents" ? "active" : ""} `}
          >
            My Events
          </Link>
          <Link
            href="/about"
            className={`${router.pathname === "/about" ? "active" : ""} `}
          >
            About Us
          </Link>
          {!loggedIn ? (
            <Link
              href="/login"
              className={`${router.pathname === "/login" ? "active" : ""} `}
            >
              Login
            </Link>
          ) : (
            <div>
              <Button
                onClick={() => setProfileOpen(!profileOpen)}
                className={styles.icon}
              >
                <CgProfile />
              </Button>
              {profileOpen && (
                <Paper
                  
                  sx={{
                    position:"absolute",
                    textAlign:"center",
                    paddingRight:'2rem',
                    zIndex:"99",
                    backgroundColor:"#121212",
                    color:"#fff"
                  }}
                >
                  <MenuList >
                    <MenuItem >
                      <ListItemText inset >
                        <Link href="profile">Profile</Link>
                      </ListItemText>
                    </MenuItem>
                    <MenuItem>
                      <ListItemText inset>
                        <Link href="/" onClick={handleLogout}>Logout</Link>
                      </ListItemText>
                    </MenuItem>
                  </MenuList>
                </Paper>
              )}
            </div>
          )}
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
            {!loggedIn ? (
              <MenuItem>
                <ListItemText inset>
                  <Link href="/login">Login</Link>
                </ListItemText>
              </MenuItem>
            ) : (
              <MenuItem onClick={handleLogout}>
                <ListItemText inset>
                  <button>Logout</button>
                </ListItemText>
              </MenuItem>
            )}
            {/* <div className={styles.profile}>
            <MenuItem>
            
            <Link className={styles.icon}>
              
              <CgProfile />
              
            </Link>
            
          </MenuItem>
            </div> */}
          </MenuList>
        </Paper>
      )}
    </nav>
  );
};

export default Navbar;
