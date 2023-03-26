
import { AiOutlineSearch } from "react-icons/ai";
import styles from "./EventsNavbar.module.css";
import { TextField, IconButton, Typography } from "@mui/material";
import {BsPersonWorkspace} from 'react-icons/bs'
import { BsFillGrid3X3GapFill } from "react-icons/bs";
import {AiFillQuestionCircle} from "react-icons/ai";
import {BsFillMicFill} from "react-icons/bs";
import {BsMusicNoteBeamed} from "react-icons/bs";
import {FaRunning} from 'react-icons/fa';
import {BiPlusCircle} from 'react-icons/bi'
import {CgGames} from 'react-icons/cg'
import {AiFillTrophy} from 'react-icons/ai'
import { useState } from "react";

const EventsNavbar = ({setGenre,search,setSearch}) => {
  const [index,setIndex] = useState(0);
  
  const handleButton = (num,genre) =>{
    setIndex(num);
    setGenre(genre);

  }
  return (
    <div className={styles.navbar}>
      <div className={styles.search}>
        <TextField
          id="filled-basic"
          label="Search"
          variant="filled"
          inputProps={{
            style: {
              color: "white",
              border: "2px solid white",
              borderRadius: "4px",
            },
          }}
          InputLabelProps={{
            style: { color: "#fff" },
          }}
          value={search}
          onChange={(e) => setSearch(e.target.value)}
        />
        <AiOutlineSearch className={styles.searchIcon}/>
      </div>
      <div className={styles.categories}>
        <IconButton onClick={() => handleButton(0,"")} className={`${styles.icon} ${index === 0 ? styles.active : ""}`}>
          All
          <BsFillGrid3X3GapFill />
        </IconButton>

        <IconButton onClick={() => handleButton(1,"Workshop")} className={`${styles.icon} ${index === 1 ? styles.active : ""}`}>
          Workshop
          <BsPersonWorkspace />
        </IconButton>

        <IconButton onClick={() => handleButton(2,"Quiz")} className={`${styles.icon} ${index === 2 ? styles.active : ""}`}>
          Quiz
          <AiFillQuestionCircle />
        </IconButton>

        <IconButton onClick={() => handleButton(3,"Stand-up")} className={`${styles.icon} ${index === 3 ? styles.active : ""}`}>
          Stand-up
          <BsFillMicFill />
        </IconButton>

        <IconButton onClick={() => handleButton(4,"Music")} className={`${styles.icon} ${index === 4 ? styles.active : ""}`}>
          Music
          <BsMusicNoteBeamed />
        </IconButton>

        <IconButton onClick={() => handleButton(5,"Sport")} className={`${styles.icon} ${index === 5 ? styles.active : ""}`}>
          Sport
          <FaRunning />
        </IconButton>

        <IconButton onClick={() => handleButton(6,"Charity")} className={`${styles.icon} ${index === 6 ? styles.active : ""}`}>
          Charity
          <BiPlusCircle />
        </IconButton>

        <IconButton onClick={() => handleButton(7,"Games")} className={`${styles.icon} ${index === 7 ? styles.active : ""}`}>
          Games
          <CgGames />
        </IconButton>

        <IconButton onClick={() => handleButton(8,"Competition")} className={`${styles.icon} ${index === 8 ? styles.active : ""}`}>
          Competition
          <AiFillTrophy />
        </IconButton>

        
      </div>
    </div>
  );
};

export default EventsNavbar;
