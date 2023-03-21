import React from "react";
import Image from "next/image";
import { TiLocation } from "react-icons/ti";
import { RxCalendar } from "react-icons/rx";
import { Button } from "@mui/material";
import styles from "@/styles/CarouselItem.module.css";

const CarouselItem = ({ name, picture, location, date }) => {
  return (
    <div className={styles.item}>
      <div className={styles.itemImage}>
        <Image src={picture} alt="" fill quality={100} />
      </div>
      <div className={styles.itemDescription}>
        <h1 className={styles.itemHeader}>{name}</h1>
        <div className={styles.itemLocation}>
          <TiLocation />
          <p>{location}</p>
        </div>
        <div className={styles.itemCalendar}>
          <RxCalendar />
          <p>{date}</p>
        </div>

        <Button className={styles.itemButton}>DETAILS</Button>
      </div>
    </div>
  );
};

export default CarouselItem;
